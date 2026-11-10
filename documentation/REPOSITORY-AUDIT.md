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
