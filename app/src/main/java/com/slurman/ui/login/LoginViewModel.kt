package com.slurman.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slurman.data.ConnectionStore
import com.slurman.model.SshConnectionConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import java.io.IOException

data class LoginUiState(
    val host: String = "",
    val port: String = SshConnectionConfig.DEFAULT_PORT.toString(),
    val username: String = "",
    val password: String = "",
    val isConnecting: Boolean = false,
    val errorMessage: String? = null
)

sealed class LoginEvent {
    data object NavigateToDashboard : LoginEvent()
}

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events: SharedFlow<LoginEvent> = _events.asSharedFlow()

    fun updateHost(value: String) {
        _uiState.update { it.copy(host = value, errorMessage = null) }
    }

    fun updatePort(value: String) {
        _uiState.update { it.copy(port = value, errorMessage = null) }
    }

    fun updateUsername(value: String) {
        _uiState.update { it.copy(username = value, errorMessage = null) }
    }

    fun updatePassword(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null) }
    }

    fun connect() {
        val state = _uiState.value
        val host = state.host.trim()
        val portStr = state.port.trim().ifEmpty { SshConnectionConfig.DEFAULT_PORT.toString() }
        val username = state.username.trim()
        val password = state.password

        if (host.isBlank() || username.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Host and username are required") }
            return
        }

        val port = portStr.toIntOrNull() ?: SshConnectionConfig.DEFAULT_PORT

        viewModelScope.launch {
            _uiState.update { it.copy(isConnecting = true, errorMessage = null) }

            val result = withContext(Dispatchers.IO) {
                try {
                    val client = SSHClient()
                    client.addHostKeyVerifier(PromiscuousVerifier())
                    client.connect(host, port)
                    try {
                        client.authPassword(username, password)
                        ConnectionStore.setConnection(
                            SshConnectionConfig(
                                host = host,
                                port = port,
                                username = username,
                                password = password
                            )
                        )
                        true
                    } finally {
                        client.disconnect()
                    }
                } catch (e: IOException) {
                    e.message ?: "Connection failed"
                }
            }

            _uiState.update {
                it.copy(
                    isConnecting = false,
                    errorMessage = if (result is String) result else null
                )
            }

            if (result == true) {
                _events.emit(LoginEvent.NavigateToDashboard)
            }
        }
    }
}
