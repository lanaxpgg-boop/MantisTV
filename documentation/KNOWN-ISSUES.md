# Current scope clarification — 2026-11-11

Original0.4 clock bugs below remain in that preserved artifact; both passed narrow physical regressions in isolated0.4.1. They are not automatically current Tigris bugs. Tigris runtime remains physically untested. Latest build42899148489 has0lint errors and79warnings per Mantis build type, not the earlier nine-error failure. Ephemeral signing changes certificate between builds; no automatic uninstall.0.5 remote/focus acceptance remains open and prior concurrent user input prevents attributing changing focus to a source defect. See PROJECT-RECOVERY-REPORT.md before treating historical findings as current.

## Historical exact-build issues

# Known issues after physical QA

- Confirmed: Home/header time does not refresh while the page remains visible.
- Confirmed: Reduce-motion screensaver clock freezes across minute changes; restored off after test.
- Failed automated check: ADB simulated long-press opens the app rather than actions. Physical held-remote behavior still unverified; favorites/info acceptance outstanding.
- Observed nonfatal Amazon miniTV icon-resource fallback.
- Source findings still open: empty favorites become default apps, duplicate package focus tags, lost filter/query/scroll state, synchronous duplicate discovery and unbounded stale icon work.
- 0.4 visual/navigation structure fails current 1:1 tvOS product acceptance; it is a tested scaffold.
- Persistent private signing/update continuity unresolved. No automatic uninstall to bypass signature mismatch.
- No Apple protocols, genuine content Top Shelf/Continue Watching, rich media interface or OS replacement.
- Only one exact firmware smoke-tested. Thermal, long soak, native4K quality, real media/DRM/audio and comprehensive accessibility remain unverified.

See device-test/2026-11-10/DEVICE-TEST-REPORT.md for observed versus inferred evidence. Existing APK/artifacts/commits are preserved.

## Subsequent physical evidence

Both clock fixes passed real-minute AFTMM regressions in separate0.4.1 debug, source b6f7a673a01619aca100adbcc4abcf97cbf3ab80. Original0.4 is preserved and retains its known defects. See CLOCK-PHYSICAL-QA.md. These narrow passes do not accept the current UI as a faithful tvOS recreation or establish signing continuity.
