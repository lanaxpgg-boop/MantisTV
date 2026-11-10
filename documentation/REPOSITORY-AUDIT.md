# Repository audit

## Selected implementation

Original native Java/View code for a narrow, permission-minimal API 25 preview, on stock Fire OS. No MantisTV runtime dependency or upstream source reuse. Exact MantisTV source commit is recorded in each actual build report.

## Baseline reference

https://github.com/techgenius1/tvOS-Launcher at 463fa6c2a9b7322c4407f1d00db935ef09d20f4f. The untouched source compiled using cloud-installed SDK tools; the original build.sh was inspected but not executed. Root license Apache-2.0, third-party Inter OFL and QR MIT notices retained in the baseline archive. Upstream declares min SDK 24 and target 34 in its build script. Its broad manifest requests storage, secure settings, accessibility and notification services; these increase integration and verification scope on stock Fire OS. No physical performance comparison exists.

## Other candidates, preliminary review only

- humblemuzzu/dumbtv: advertises reversible Fire TV cleanup over ADB plus a launcher. Device cleanup/package disabling is outside this overnight scope; no code reused.
- petrobot-admin/hume-launcher: advertises tvOS-style launcher and signage. Source/license/API/runtime review incomplete; no code reused.
- hamdalaa/tvhome: Kotlin/Compose launcher and TV features. Added dependency/build footprint requires actual low-RAM measurements; no assumption that Compose is inherently incompatible; no code reused.
- mazer666/PhairPlay: receiver integration research candidate. ARMv7/API25, native dependencies, protocol security and licenses unverified; no inclusion or receiver claim.
- LOSMantis/android_device_amazon_mantis: device tree research candidate, not a ready verified safe firmware replacement. Flashing and bootloader modification are prohibited here.

Maintenance claims and comparative RAM benchmarks have not been established. This preliminary assessment must be expanded before selecting any additional reuse.

## Additional source checks (November 10, 2026)

The public dumbtv root license is MIT and explicitly covers its launcher. Hume's root license is Apache-2.0. tvhome's app/build.gradle.kts declares minSdk 26 and therefore needs real adaptation before API25 use. This is a specific current incompatibility, not a general claim about Kotlin/Compose.

PhairPlay's app/build.gradle.kts declares a Fire TV minSdk25 flavor and armeabi-v7a among its native targets. Its root license is Apache-2.0, but its CMake path and comments explicitly include a native playfair component. That protected-protocol path is not included in MantisTV and must not be used to bypass content protection. Native dependency and individual component license review remains incomplete. No certification, interoperability or functional receiver claim follows from the manifest/ABI settings.

Sources: https://raw.githubusercontent.com/humblemuzzu/dumbtv/main/LICENSE ; https://raw.githubusercontent.com/petrobot-admin/hume-launcher/main/LICENSE ; https://raw.githubusercontent.com/hamdalaa/tvhome/main/app/build.gradle.kts ; https://raw.githubusercontent.com/mazer666/PhairPlay/main/app/build.gradle.kts ; https://raw.githubusercontent.com/mazer666/PhairPlay/main/LICENSE . These are execution-time references, not pinned reuse: no code from these projects is embedded.
