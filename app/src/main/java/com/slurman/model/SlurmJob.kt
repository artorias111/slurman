package com.slurman.model

/**
 * Represents a Slurm job as returned by `squeue --me`.
 */
data class SlurmJob(
    val jobId: String,
    val partition: String,
    val name: String,
    val user: String,
    val state: String,
    val time: String,
    val nodes: String,
    val reason: String
)
