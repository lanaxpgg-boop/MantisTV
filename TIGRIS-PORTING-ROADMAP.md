# Tigris porting roadmap and decision gates

## Current milestone

Full upstream clone and separate experimental worktree created. Official artifact identity/minSDK/no-JNI verified. Source feasibility audit and first compatibility/safety/profile implementation exist. Tigris is **not yet the primary validated codebase**, not installed on the Fire Stick, and not physically tested. Exact tvOS27 visual acceptance is outstanding.

Chosen direction: **Option A conditional on compatibility, visual and performance gates**. Preserve working Tigris components; selectively reuse Mantis profile/search rules and physical regression cases. Do not merge source into Mantis main or overwrite0.4/0.4.1/0.5 artifacts. A cloud workflow on Mantis main is build orchestration only; experimental runtime source belongs on its own branch/worktree.

## Resource boundary

At most two actual20minute standard public ubuntu24.04 build attempts in this experiment; no paid runner/services, caches or Actions artifact uploads. GitHub Releases hold bounded diagnostics/source/APK evidence. Rejected workflow syntax checks do not start a build runner and are recorded separately. All Gradle/SDK/dependency caches stay on the cloud runner. Local source/audit download budget512MiB and normal34.241GiB free-disk reserve apply; no local Gradle build. Save work and stop when a resource or user usage limit is reached. No unattended future automation is created.

## Next ten prioritized engineering tasks

1. Complete dependency-aware min25 lint and resolved runtime/native inventory. Fix genuine API26+ accesses; never suppress NewApi or override dependency minSdk conflicts to manufacture a pass.
2. Build separate `dev.mantistv.tigris.experimental` APK with all JVM/host tests. Verify min25, DEX, ARM/native payloads, source identity, alignment, SHA256 and certificate. Preserve failure logs if the bounded attempts fail; do not install a failed candidate.
3. Validate the added API26 playback-probe helper isolation, API28 volume gate and25 fail-closed idle fallback. Exercise every reachable modern helper on25; add meaningful regressions for files/signing/no-media interruption.
4. Make every excluded maintenance/privileged control explicitly unavailable in the experimental UI. Remove upstream self-update identity/channel assumptions. Secure persistent signing/update continuity before a user-facing release; never uninstall to bypass signature mismatch.
5. Resume existing Mantis0.5 physical navigation only after device availability is restored and remote input is coordinated. Preserve its pause/resume records and known visual gaps; no new physical certification without evidence.
6. After all artifact gates and safe normal-app UI checks, perform isolated AFTMM launch/navigation/escape tests. Keep Amazon Home and existing apps/settings intact; no upstream installer/grants/boot/guard configuration.
7. Measure cold/warm launch, RAM, idle CPU, frame timing and20 app-return cycles. Tune cached glass/sample resolution/cache ownership based on GE8300 traces; keep composition and typography fidelity wherever affordable.
8. Capture and compare actual public Apple27 references screen by screen. Previous official26 reference images remain labelled26; do not invent27 coordinates or declare Tigris's tvOS-inspired styling exact. Align Home/TopShelf/ControlCenter/Switcher/Settings/Search/Screensaver/media interfaces and motion with documented deviations.
9. Integrate Mantis's four local profile namespaces, safe app search/watchlist, intent failure handling and clock/reduced-motion regressions behind Tigris UI seams. Avatar is not a profile; local profile is not an Apple account. Preserve stored keys with explicit migration and isolation tests.
10. Prototype optional Apple-compatible sharing as a separate capability with license/protocol/security tests; distinguish browser uploads from AirPlay and native Remote/HomeKit/Siri. Add per-firmware capability results and all documented AFTMM revision tests before recommending Tigris as primary.

## Promotion / fallback

Promotion requires: min25 lint/test/build success, reproducible source and verified signing/metadata, safe separate app install, actual AFTMM launch/remote/recovery/media-idle tests, repeatable memory/frame results, and reference comparisons showing a materially closer tvOS composition. All missing features keep their exact project status; compilation cannot promote them to completed.

If the bounded build fails, preserve diagnostics and continue targeted source fixes in a later authorized resource window. If runtime/performance gates demonstrate fundamental Tigris coupling or unacceptable memory cost, reconsider Option B (selected renderer modules) before a clean third architecture. Do not prematurely rewrite the entire application or promise native tvOS/Apple service parity.

## Evidence and publication

Five reports are source-based findings; build attempts/results are recorded in `evaluation/` and the experimental prerelease. `STATUS.md` in the Mantis checkpoint points here without changing the original119-row audit counts. Full OS replacement remains not implemented under the no-firmware boundary. Mantis's already verified physical subsets remain preserved, not recertified by this experiment.
