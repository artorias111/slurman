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

Then build or run the app on a device/emulator.


Sign in page<img width="108" height="234" alt="Screenshot_20260220_142532" src="https://github.com/user-attachments/assets/ac744a0a-a5ff-4a6f-a026-2d97094e17db" />
: 
