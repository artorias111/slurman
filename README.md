# Slurman

Android app to SSH into a remote cluster and run `squeue --me`. Built with Jetpack Compose and Material 3.

## Structure (MVVM)

- **model/** – `SlurmJob` data class (jobId, partition, name, user, state, time, nodes, reason)
- **data/remote/** – `SlurmParser` (placeholder to parse squeue output)
- **ui/dashboard/** – `DashboardViewModel` (StateFlow of jobs), `DashboardScreen` (Compose UI)
- **ui/theme/** – Material 3 theme and typography

## Dependencies

- Jetpack Compose + Material 3
- Lifecycle ViewModel (StateFlow)
- [sshj](https://github.com/hierynomus/sshj) 0.39.0 for SSH

## Build

Open in Android Studio or run from the project root:

```bash
./gradlew assembleDebug
```

If the Gradle wrapper is missing, generate it with:

```bash
gradle wrapper
```

Then build or run the app on a device/emulator.
