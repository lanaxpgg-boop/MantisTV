# Supervised installation

Do not install unattended. This project has not been physically tested on AFTMM. The baseline reference APK is not the customized MantisTV preview; choose MantisTV-0.1.0-preview.apk from a successful native release.

1. Verify release checksums against SHA256SUMS.txt on a machine with adequate free storage. Review BUILD-REPORT.txt, signature.txt and manifest.txt.
2. Confirm the device is your AFTMM / Fire OS 6 API 25. Preserve normal Amazon launcher access and record the installed firmware.
3. When you explicitly authorize a supervised device session, enable developer installation/debugging through normal Fire OS controls and use a trusted local network only. Do not expose ADB publicly.
4. From an already provisioned authorized test machine, manually run: adb install MantisTV-0.1.0-preview.apk. No -g, package disabling, HOME remapping or system grants are needed.
5. Launch the MantisTV icon from Amazon Apps, or manually use adb shell am start -n dev.mantistv.home/.MainActivity.
6. Test D-pad reachability and scrolling, app launching/return, favorites, Back exit, Menu controls, profile isolation, search keyboard, unavailable shortcuts and screensaver. Keep the Amazon remote Home button working normally.
7. Observe memory, focus frame consistency, long-session stability and thermal behavior. Stop if the device becomes unstable. Record exact firmware and evidence before classifying compatibility A.

The preview uses an ephemeral signing key. A later cloud build may not install over it. Never automatically uninstall to fix a signature mismatch: local profiles, favorites and watchlists would be lost.
