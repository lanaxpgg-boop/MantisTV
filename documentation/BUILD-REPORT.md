# Build evidence — corrected by progress audit

Latest actual application build is 0.4.0-preview, code4, package dev.mantistv.home plus separate debuggable dev.mantistv.home.debug. Minimum/target/compile SDK25; DEX-only and no native library payload. Compiled source: ab022168a5d8fff173ff0169fd328adb2040be89. Original release target/source archive: 54619013a18b723559520e6d32241dc56909d7c7, a subsequent documentation checkpoint. Successful original build/lint run: 42747779541. Audited pre-report HEAD: a1a51fe520363c2512eb14a79e4c17998c8ad0ad; its app source is unchanged.

Compilation, narrow host data-rule tests, manifest/package/ZIP/ABI policy, alignment and development signatures passed. Seven selected Android lint checks had zero errors/warnings; no full lint sweep or Android/hardware instrumentation ran. Physical installation/testing and performance measurements remain absent.

The progress audit release is documentation-only: it republishes the identical, reverified 0.4 APKs and includes PROJECT-PROGRESS, STATUS, source ZIP and machine-readable provenance/verification. It does not build a new version, generate a new signing key or alter old release assets. Fresh signatures/manifests and rerun host rules are separate audit evidence; original build reports are retained unchanged.

No persistent private production keystore is configured. Ephemeral development signing is not update-stable. Untouched upstream baseline techgenius1/tvOS-Launcher at 463fa6c2a9b7322c4407f1d00db935ef09d20f4f is a separate Apache-2.0 reference release, not code embedded in the original MIT-licensed MantisTV app. See ../PROJECT-PROGRESS.md for the full evidence audit.
