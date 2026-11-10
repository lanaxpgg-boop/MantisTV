# Apple ecosystem integration acceptance plan

User direction confirmed November10,2026: the whole MantisTV design must recreate the latest Apple TV/tvOS experience as faithfully as possible, and the user must have an option to connect their MacBook and iPhone. Preserve the tested0.4/0.4.1 apps, current artifacts, free cloud-only builds and existing device safety constraints. No integration is implemented or physically accepted by this document.

## Product contract

- Match actual versioned tvOS Home/Top Shelf, app organization, Control Center, App Switcher, Settings, Search, screensaver and media interfaces, including geometry, typography, focus, parallax, motion and navigation. Existing generic seven-tab design is a scaffold and fails final acceptance.
- Add an optional Apple devices setup flow with real capability status, receiver/companion start-stop, pairing and revocation. Keep network receiving off until explicitly enabled. No decorative connected/paired/synced state.
- Target iPhone and Mac screen mirroring, permitted media/audio/photo sharing and playback controls through genuine AirPlay-compatible receiving where feasible. Test each sender/transport/media path independently.
- Target iPhone and Mac remote navigation/text entry plus selected local actions using a paired companion interface if native Apple Remote compatibility is unavailable. A browser companion is custom MantisTV control, not proof of Apple's native Control Center Remote, Siri or HomeKit compatibility.
- Track Apple TV app/account/DRM playback, HomeKit/Home hub, Siri, iCloud Photos/Music/account syncing, AirPlay multiroom and native iPhone Remote independently. Do not imply these arrive automatically with an AirPlay receiver or similar-looking UI.

## Initial source feasibility evidence

Primary candidate: [jqssun/android-airplay-server](https://github.com/jqssun/android-airplay-server), pinned commit `a46cca0a6a1274e9e33c0e750f0f1c1eb1d881ea`, inspected2026-11-10. Upstream implementation is based on [UxPlay](https://github.com/FDH2/UxPlay).

Actual [app build configuration](https://github.com/jqssun/android-airplay-server/blob/a46cca0a6a1274e9e33c0e750f0f1c1eb1d881ea/app/build.gradle.kts) declares minSdk23, compile/target37 and normal build ABIs arm64-v8a/armeabi-v7a/x86_64. This makes API25/ARMv7 a plausible investigation target; declaration alone does not verify dependency/API guards, native loader compatibility, Fire OS behavior or performance. No source/build-tool checkout or app installation from this candidate has occurred.

[LICENSE](https://github.com/jqssun/android-airplay-server/blob/a46cca0a6a1274e9e33c0e750f0f1c1eb1d881ea/LICENSE) is GPLv3. Review all submodules/native/media dependencies and distribution/source obligations before deciding whether to keep it as a separate companion or integrate code. No GPL code has been copied into MantisTV and no license compatibility claim is made.

[Manifest](https://github.com/jqssun/android-airplay-server/blob/a46cca0a6a1274e9e33c0e750f0f1c1eb1d881ea/app/src/main/AndroidManifest.xml) includes network/Wi-Fi/multicast/wake-lock permissions, foreground services, boot receiver permission and overlay permission. Evaluate the minimal opt-in foreground receiver route first; do not enable boot behavior or grant overlay privileges as part of research. Modern permissions and services require actual API25/Fire OS fallback inspection.

Upstream README describes mirroring/audio/video and optional PIN authentication, but explicitly excludes DRM-protected content. Those are upstream claims, not measured MantisTV capabilities. [Apple's Remote documentation](https://support.apple.com/en-us/108778) describes Apple TV and AirPlay-compatible smart-TV setup, including Home app enrollment for smart TVs. It does not establish this Android receiver as a compatible native Remote target.

## Next engineering and test gates

1. Audit pinned native submodules/dependency licenses, build resource needs and API25 guards; establish repeatable free-cloud baseline builds without modifying MantisTV runtime or the Fire Stick.
2. Inspect resulting APK manifest, ARMv7 native dependencies, minSDK, certificate, startup/receiver controls and packaged source/license completeness. Keep unsafe/unneeded system integration disabled.
3. After build/artifact gates, run isolated AFTMM discovery/pairing/stop/revocation/resource tests without replacing either installed MantisTV app. Preserve Amazon Home and existing data.
4. Perform actual MacBook and iPhone discovery, PIN pairing, mirroring, audio/photo/legal video, interruption/reconnect, rotation/aspect ratio, latency, thermal and long-session checks. Sender interaction may require the user; never infer a pass from the receiver merely starting.
5. Implement MantisTV Settings integration only for physically supported functions. Add a paired custom companion remote/text interface separately and test authentication, session revocation and failure states. Do not access Apple credentials, expose an unauthenticated internet listener or claim certification.
6. Keep native iPhone Remote/HomeKit/Apple services as explicit feasibility milestones. Update existing E01–E09/T rows only when runtime code and test evidence warrant their exact status; this research changes no feature-status counts.

Stable private signing and a non-destructive migration path remain prerequisites for reliable future app updates. No new credentials, account permissions, network settings or paid services were created by this source review.
