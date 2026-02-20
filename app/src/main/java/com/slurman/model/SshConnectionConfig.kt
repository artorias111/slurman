package com.slurman.model

/**
 * SSH connection parameters for the cluster.
 */
data class SshConnectionConfig(
    val host: String,
    val port: Int,
    val username: String,
    val password: String
) {
    companion object {
        const val DEFAULT_PORT = 22
    }
}
