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

See DEVICE-TEST-REPORT.md for observed versus inferred evidence. Existing APK/artifacts/commits are preserved.
