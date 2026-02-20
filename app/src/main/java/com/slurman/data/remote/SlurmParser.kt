package com.slurman.data.remote

import com.slurman.model.SlurmJob

/**
 * Parses the raw output of `squeue --me` into a list of [SlurmJob].
 * Placeholder implementation; will parse actual squeue output (e.g. default or custom format).
 */
object SlurmParser {

    /**
     * Parses squeue stdout into a list of jobs.
     * @param rawOutput Raw text from running `squeue --me` on the cluster.
     * @return List of [SlurmJob], or empty list if parsing fails or output is empty.
     */
    fun parse(rawOutput: String): List<SlurmJob> {
        // TODO: Parse real squeue output (header line + data lines, handle column alignment)
        return emptyList()
    }
}
