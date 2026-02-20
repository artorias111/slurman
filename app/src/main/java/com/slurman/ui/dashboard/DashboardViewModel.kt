package com.slurman.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slurman.model.SlurmJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _jobs = MutableStateFlow<List<SlurmJob>>(emptyList())
    val jobs: StateFlow<List<SlurmJob>> = _jobs.asStateFlow()

    fun loadJobs() {
        viewModelScope.launch {
            // TODO: SSH to cluster, run squeue --me, parse with SlurmParser, then _jobs.value = result
            _jobs.value = emptyList()
        }
    }

    fun updateJobs(newJobs: List<SlurmJob>) {
        _jobs.value = newJobs
    }
}
