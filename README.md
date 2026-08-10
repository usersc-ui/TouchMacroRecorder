# Touch Macro Recorder

Android Studio project with GitHub Actions APK build.

## Build
Push to GitHub. GitHub Actions will build a debug APK and attach it as an artifact.

## Important
This repository is a prototype. Android Accessibility APIs have restrictions on globally observing raw touch input from other apps. The playback/recording engine needs a supported implementation before it can reliably reproduce arbitrary HOLD/MOVE/RELEASE gestures in a game.
