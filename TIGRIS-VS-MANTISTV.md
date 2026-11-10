# Tigris2.0.27 versus MantisTV0.4.0

Latest checkpoint: **API25 build,226 JVM tests and both lint variants passed; APK checksum/signing/metadata verified. Hardware execution untested.** See the additional authorized verification section below. [Artifact](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-verified-42894116665).

Earlier audit and bounded-run evidence are retained below as history.

Comparison pins: Tigris `c6d258f467e0a8f68f5a112bb5aef9710e81392d`; Mantis0.4 `ab022168a5d8fff173ff0169fd328adb2040be89`. Later0.4.1/0.5 progress is preserved but is not silently substituted into the requested baseline. Status evaluates the complete requested feature on AFTMM; a useful subset does not make the whole feature completed.

A=reuse Tigris; B=retain Mantis; C=improve/rewrite the indicated seam; D=defer restricted capability. These letters are engineering decisions, not completion statuses.

| Planned feature | Mantis0.4 evidence | Tigris2.0.27 actual code | Tigris on AFTMM status | Decision |
|---|---|---|---|---|
| Home layout |Greeting/tab navigation/app rows, real installed apps; physical smoke passed, tvOS acceptance failed |HomeActivity/DockLayout grid + lower dock,5/6 columns and upper content |Implemented but not physically tested. |A; compare exact27 reference geometry before acceptance |
| Liquid Glass Dock |Simple custom painted glass-like background, not actual background refraction |Cached CPU glass, pre31 fallback and optional extreme renderer |Implemented but not physically tested. |A; preserve shape while bounding sample cost |
| Top Shelf |Recently launched app list labelled Top Shelf, no media recommendations |Bilibili/Douyu/custom JSON cards and open actions; no universal providers |Partially implemented. |A+C provider adapters for user's installed apps |
| Focus animations/parallax |Scale1.045/140ms and focus state; physical navigation smoke |Scale1.12/230ms, shadow/labels; current release scale-only, no actual directional parallax |Partially implemented. |A+C measured remote/focus/parallax behavior |
| Control Center |Working subset of audio/settings/screensaver; old page UI |Floating glass or low-end panel; media sessions/vendor controls/ADB power |Partially implemented. |A+C safe capability presentation; D privileged power/unsupported TV controls |
| App Switcher |Launcher-owned recent-app list, not task windows |Stacked cards; API30 screenshot capture; history and optional UsageStats |Partially implemented. |A+C non-screenshot25 fallback; D screenshot/task-process closure |
| Screensaver |Gradient/clock, real5minute timer verified; clock defect in0.4 later fixed separately |Photos, local idle policy, DreamService and streamed aerials |Partially implemented. |A+B safe home-only timer/clock regressions; D system takeover until validated |
| Wallpapers |Local accent/background styling |Photo sources/custom URLs/local upload/video via ExoPlayer |Implemented but not physically tested. |A;1080p profile and bounded network/storage |
| App management |Favorite/hide/app details actions, local persistence |Order/hide/folders/banner selection; optional privileged uninstall/cleanup |Partially implemented. |A+B persistence rules; C disable dangerous/unsupported controls |
| Application launching |Real resolved intents and return navigation physically checked |AppRepo real components/leanback/launch fallback and return cue |Implemented but not physically tested. |A+B Fire OS intent-failure/return regression evidence |
| Profiles |Four local profiles with scoped favorites/recent/watchlist, isolation physically checked; not Apple accounts |Single shared Prefs/order; avatar file only |Not implemented. |B+C integrate profile-aware store without losing existing keys; no Apple-account claim |
| Search |Real local app-label filtering, not universal media/Siri |Weather-city query exists; no global app/content search surface found |Not implemented. |B+C local index/UI then provider search; D Siri/private services |
| Settings |Local profile/reduced motion/accent/diagnostics and native intent links |Extensive native settings scene,appearance/media/guard/update/device subpages |Partially implemented. |A+C27 layout and capability availability; B reduced-motion policy |
| Remote navigation |Dpad/Back/Menu/escape/return smoke subset verified |Focus grid, edit/TopShelf/ControlCenter shortcuts, optional doubleHOME/guard |Partially implemented. |A+B safety/escape conventions; C physical AFTMM checks; D universal HOME interception |
| Apple ecosystem |No implemented receiver/HomeKit/Apple account |Phone browser upload page + optional external Miracast app installer, not Apple ecosystem protocols |Not implemented. |C separate AirPlay-compatible optional receiver investigation; retain GPL boundary review |
| Full OS replacement |Normal application only |Normal Android launcher plus optional services, not tvOS kernel/framework |Not implemented. |D under current no-root/no-firmware constraints |

Mantis0.4 had narrow physical evidence; Tigris's larger implementation and upstream TV screenshots do not transfer that evidence to this device. Conversely, Mantis's physical app launch does not verify Tigris launch or claim all firmware support. No invented percentage of finished tvOS experience is assigned.

## Recommendation

**Option A after gates**, with selective Mantis data/QA reuse. This minimizes duplicated development and starts from the closer existing Home/glass/settings/media foundation. Mantis0.4 and0.4.1 remain recovery references;0.5 Home preview remains a saved experiment. Option B would require transplanting several tightly interacting17k-line Kotlin UI/runtime subsystems into the much smaller Java activity, increasing integration work. Option C would introduce a third architecture without current evidence that the intact Tigris renderer is inadequate. Revisit B/C only if API25 startup or measured GE8300 performance fails after a bounded attempt.

Neither codebase currently delivers exact tvOS27, Apple service entitlements or replacement firmware. Every visible future control must either work with observable feedback or clearly explain unavailability. A portrait, glass shape or compilation pass cannot satisfy profile, ecosystem or hardware acceptance.

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
