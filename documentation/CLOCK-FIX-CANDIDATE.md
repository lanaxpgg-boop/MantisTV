# 0.4.1 clock-fix candidate

Source changes: the header uses Android TextClock, preserving HH:mm, text size and padding; the screensaver schedules its next reduced-motion redraw at the next real minute boundary, refreshes the timezone on each changed minute, and exposes its clock text as an accessibility description. Normal motion retains its existing100ms redraw request. These changes do not reconstruct Home or establish tvOS fidelity.

Version0.4.1-preview/code5. Existing API25/ARMv7/no-services/non-HOME/permission policy retained. Cloud gates: actual API25 compilation, existing host rule tests, selected seven-rule zero-warning lint, packaged version/SDK/debug/ABI/ZIP/alignment and cryptographic signature checks. These gates alone do not verify clock behavior.

Physical regression plan on exact AFTMM NS6711/5908:

1. Validate downloaded debug APK hash/certificate/package/version against this release. Confirm dev.mantistv.home.debug is absent; normal install only. Preserve installed0.4 dev.mantistv.home and its profile/watchlist data. Never uninstall or clear either package to bypass a mismatch.
2. Launch candidate explicitly. Observe native TextClock before and after a real minute change with no page rebuild or input. Compare against device wall time. Confirm focus and navigation remain usable afterward.
3. Turn on candidate Reduce motion, enter manual screensaver, capture accessibility clock and screenshots before/after a real minute boundary; verify the clock advances while scenery remains still. Restore candidate motion off afterward.
4. Check Home/Back escape, pause/reopen and relevant logs/memory. Distinguish deliberate backgrounding from crashes. Preserve both installations/test evidence and original Amazon defaults.

Signing: the original0.4 development private key is unavailable. This build retains the established temporary development signing mechanism and separate debug package for isolated QA; it does not establish persistent signing or a data-preserving in-place update path. No persistent credential is created or published. Persistent private signing plus export/import migration remains required before a release can be called safely updatable.

No local SDK downloads, local builds, paid services, firmware/default-launcher/system-package changes or destructive cleanup. Mac resource reserve must remain satisfied. Current119-feature physical audit applies to unchanged0.4;0.4.1 behavior stays unverified until new physical evidence is recorded separately.
