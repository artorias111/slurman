package com.slurman.data

import com.slurman.model.SshConnectionConfig

/**
 * In-memory store for the current SSH connection after successful login.
 * Dashboard and SSH operations use this to run squeue etc.
 */
object ConnectionStore {
    @Volatile
    var currentConnection: SshConnectionConfig? = null
        private set

    fun setConnection(config: SshConnectionConfig) {
        currentConnection = config
    }

    fun clearConnection() {
        currentConnection = null
    }
}
