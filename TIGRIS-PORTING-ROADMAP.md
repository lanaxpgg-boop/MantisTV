# Tigris porting roadmap and decision gates

Latest checkpoint: **API25 build,226 JVM tests and both lint variants passed; APK checksum/signing/metadata verified. Hardware execution untested.** See the additional authorized verification section below. [Artifact](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-verified-42894116665).

Earlier audit and bounded-run evidence are retained below as history.

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

## Final bounded cloud result — 2026-11-10

[Run42888758342](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42888758342) compiled and assembled the min25 Mantis variant. **226 JVM tests passed, zero failures;75 Python tests and the mocked shell installation contracts passed. Full lint failed:9errors/79warnings.** The failed result is preserved, not replaced with a green compilation claim.

Cloud runtime source: `06f9a10afc5df45971c7d4a49ad547be4c7bdcd9`. Evidence/checksum commit: `cf5f4072f44be19b979b9fc412828b61ffb7b57f`. [Separate source branch](https://github.com/lanaxpgg-boop/MantisTV/tree/experiment/tigris-api25-42888758342). [Diagnostic prerelease](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-api25-42888758342). The release deliberately contains **no experimental APK** because the full gate failed; its signing/integrity and actual ARMv7 execution have not been verified.

The21 resolved AAR manifests all declare minSDK14–21 and contain zero `jni/*.so` entries; no AndroidX downgrade was needed. The complete runtime dependency graph is saved. Kotlin resolves2.2.10, collection1.4.2, core/core-ktx1.13.0, lifecycle2.6.2, coroutines1.6.4, profileinstaller1.3.1, annotation1.8.1, Guava33.3.1-android and Okio2.10.0 alongside the pinned direct dependencies. JVM-only artifacts have no Android minSdk declaration; desugaring/DEX compilation passed, but reflection/vendor linkage still needs runtime tests.

Three API findings remained: MovingGlass's ColorMatrixColorFilter.getColorMatrix(API26), UpdateGlass's Canvas.clipOutPath(API26), and the unversioned defaultFocusHighlightEnabled theme item(API26). Six manifest lint findings concern explicit TV banner/features/exported metadata and protected-permission **removal markers**. Fixes for all nine are saved locally and in `TIGRIS-POST-BUILD-FIXES.patch`, **not rebuilt or verified**. The API25 lens fallback retains tint/single-pass geometry while disabling chromatic channel decomposition; clipping uses the old difference-path operation; the theme item moves to values-v26. ProtectedPermissions ignores apply only to removal markers, never NewApi.

Two actual cloud attempts were used; one earlier recipe validation rejection did not start a runner. No further cloud build is started in this bounded experiment. Preserve the checkpoint and resume verification in a later resource window. No device install, remote input, grant, root, firmware, bootloader or default-HOME operation occurred. No local Gradle/SDK/cache build was performed. Exact tvOS27 fidelity, physical performance, Apple ecosystem integration and all-firmware compatibility remain unverified or unimplemented as specified in the comparison.

## Additional authorized verification — 2026-11-10

**The nine lint fixes now pass.** [Free cloud run42894116665](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42894116665) completed successfully:226 JVM tests/zero failures;75 Python host tests and mocked shell contracts passed. Both debug and release lint report **0errors,0NewApi issues,79warnings each**. Warnings remain open; this is not physical or visual acceptance.

[APK and evidence prerelease](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-verified-42894116665) · [Verified experimental source](https://github.com/lanaxpgg-boop/MantisTV/tree/experiment/tigris-verified-42894116665). Runtime source commit `2ae8bcd8099246fcdbe5e7c19b85cca06d7b198c`; evidence commit `d9fc2a371383573ae514a89869ce7ef6f6cc8834`. The cloud checked237 source/resource/license hashes before compilation. Independent download checks reproduced release SHA256, API25 signature, alignment, ZIP integrity, metadata and DEX results using only tools already present on the Mac; no local build or new SDK/cache download occurred.

| Artifact check | Observed result |
|---|---|
| Package/version |dev.mantistv.tigris.experimental /2.0.27-mantis-api25-experiment /code46|
| SDK |min25,target31,compile36|
| APK size |1,577,541bytes (~1.50MiB)|
| APK SHA256 |`435b7b1434a467ce801ea58af0b9db4fc6557c81e921d9f80fc65391ed14e4ba`|
| Certificate SHA256 |`d103a704bfa582e2bb54196ed3756a6a2fabc932d10b9316f33cb26d8b1fae37`|
| Signature on API25 |APK Signature Scheme v2 verified; one self-signed Android Debug certificate|
| Alignment/archive |4byte ZIP alignment and all ZIP entry CRCs passed|
| DEX |classes.dex/classes2.dex use037 headers compatible with the declared API25 floor|
| Native code |No .so entries; no observed64-bit-only native dependency|
| HOME/system components |No HOME category; selected boot/guard/revival/keeper/dream/update components and privileged permissions absent in packaged manifest|
| Device execution |Not performed; no ADB/device commands or installation|

**Signing continuity is unresolved:** the valid certificate comes from a temporary cloud test key. The private key is not retained; this artifact does not establish a safe future in-place update path. Never uninstall an existing application or clear its data to bypass a signature mismatch. This separate package does not replace Mantis0.4/0.4.1/0.5. All three Mantis APK hashes and Mantis app-source hashes were checked unchanged.

The earlier published follow-up patch omitted a newly added values-v26 theme resource. A6second source preflight correctly stopped before Gradle, SDK setup or APK generation. The complete committed patch (`TIGRIS-NINE-FIXES-COMPLETE.patch`) includes that file; source-only replay reproduced all237 expected files before the successful cloud build. Record both workflow executions honestly: one preparation-only failure and one additional actual verification build. No further build is authorized in this window.

The new result supersedes the previous nine-error/withheld-APK checkpoint **only for static build and artifact gates**. AFTMM startup, remote interaction, frame timing/RAM/thermal performance, all firmware variants, exact tvOS27 screen fidelity and Apple ecosystem integrations remain unverified or unfinished. Keep OptionA conditional; preserve Mantis as the physical fallback. Next: address unavailable maintenance controls and durable signing, then obtain a separately authorized physical test window. The Fire Stick remains untouched.
