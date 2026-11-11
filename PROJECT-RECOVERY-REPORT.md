# MantisTV project recovery report

Evidence cut: 2026-11-11, Europe/Sarajevo. Explicit continuation supersedes the previous user-requested usage pause. Existing Mantis applications and Fire Stick are preserved. No device commands, new cloud builds, local Android builds, SDK downloads or paid services were used in this recovery.

## Outcome and milestone

**API 25 experimental application built and statically verified; replacement-foundation decision remains conditional on actual AFTMM execution and visual acceptance.** This is a foreground Android application on stock Fire OS, not a full Apple TV, certified AirPlay receiver, system HOME replacement or replacement OS. Maximum feasible latest-tvOS fidelity remains the goal. Generic glass styling is insufficient.

Continue the existing Tigris experimental branch as the leading UI/runtime foundation, with selective legally reusable Hume/tvOS-Launcher modules and a separately evaluated receiver. Preserve the Mantis physical fallback. This is a reuse-first hybrid **within the existing experiment**, not a third rewrite or an unconditional migration. No alternative has yet demonstrated lower RAM, better frame timing or better exact-tvOS fidelity on this AFTMM. Physical comparison must decide those questions.

## Exact recovered state and stop point

| Question | Verified answer |
|---|---|
| Last completed task | c9671be source follow-up saved; one authorized free build dispatched. That same build finished successfully during the usage pause. Its evidence and APK have now been independently recovered and inspected. |
| Last active task | Collect run 42899148489, verify tests/lint/APK and reconcile status. It was not a new feature or a second pending build. |
| Files under modification | c9671be changed SettingsActivity.kt, ControlCenter.kt, UpdateActivity.kt, HomeActivity.kt, MantisExperiment.kt, TvOSFeatures.kt (AppCloser) and English/Chinese mantis_availability.xml resources. Last checkpoint 93da3b7 recorded the pause, not another compiled runtime change. Use git show c9671be --stat in the experimental worktree for the authoritative file inventory. |
| Failed/incomplete work | Previous experimental build had nine lint errors; later verification resolved them. One earlier preparation-only failure used an incomplete patch. Physical 0.5 navigation remains unfinished after a failed connection; user remote input confounded an earlier session. No new physical conclusion follows. Stable signing and phone-page capability presentation remained unfinished. |
| Latest build | Run 42899148489: success. No duplicate run was started. |
| APK existence | evaluation/c9671be-build/Tigris-Mantis-c9671be-API25.apk exists, 1,557,205 bytes, matches downloaded checksum and independent inspection. |
| Tests | 678 JVM executions: 226 each for Mantis, standard and guard; zero failures. 75 Python host tests passed; mocked installer shell contracts passed. Mantis debug/release lint each 0 errors, 79 warnings, 0 NewApi issues. These are not physical tests or 678 distinct tests. |
| Latest verified feature | Static build packaging of c9671be availability restrictions: all nine new localized availability resources exist in the signed APK; all 239 pinned runtime/build/license files match cloud receipt. Actual Settings/Controls focus and feedback remain physically untested. Latest narrow physical feature evidence remains the isolated Mantis 0.4.1 minute-clock regression. |
| Unfinished | Tigris runtime AFTMM smoke/performance, truthful phone download controls, durable signing, exact tvOS screen comparison, genuine search/profiles/media/Apple integration, firmware-family tests. |
| Stop reason | User explicitly reached usage limit and requested a stop. Prior Work turn completed normally with a saved pause. No evidence of a lost source edit or crashed build. |

At recovery start MantisTV main was clean at b1cb92b; Tigris worktree mantis/api25-experiment was at 93da3b7 (parent c9671bef6baccb58199c879c8ba2d5561d5097fa). Tigris-Upstream is retained separately. Source follow-ups made after this audit are explicitly separated below and do not inherit APK verification.

Public default main observed at 0931b7839cd2e3fce868b359692d411e0bb4248a. Public/default and local checkpoint histories differ; neither is silently reset. The public releases endpoint returned the older verification prerelease first; /releases/latest returned 404 because these are prereleases. Therefore this report identifies the latest **verified run by run ID and exact tag**, not by an assumed GitHub Latest badge.

## Latest verified build and preserved artifacts

[Existing build](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42899148489) · [APK and evidence release](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-c9671be-42899148489).

- Requested local source: c9671bef6baccb58199c879c8ba2d5561d5097fa.
- Cloud runtime commit: a03d9546e63244641e97c6385235270e4e0406d3.
- Cloud evidence commit: 31eacb35dc32dbe7e36bc97309ecb0ef04ea31fd.
- APK SHA-256: 224fb39b58ee117a4d8a526ad1e892b94b06b278a3e579db890684d742d9d973.
- Certificate SHA-256: ee25dbd38fe6ffd9cf3444ba0f24a9ab803e355f6920a538cf8110141a01ba71.
- Package dev.mantistv.tigris.experimental; version 2.0.27-mantis-api25-experiment, code 46; minSDK25, target31, compile36. DEX037, no native .so, ZIP CRC/alignment and API25 v2 signature passed. Manifest excludes experimental HOME/privileged maintenance components.
- Signing is valid but uses an ephemeral cloud certificate, different from the preceding experiment. This does not establish safe in-place upgrades. Never uninstall to bypass a mismatch.

Recomputed preservation checks: all nine existing Mantis app files and all four baseline APKs match their saved hashes. APKs remain: Mantis0.4 SHA6cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4; 0.4.1 SHA f9ee4f4641b8127163134a6c0bbfb254cfbecdfdd07515955042ee8707f7d594; 0.5 SHA79174eee05449df61d2a5ac4faa590ad8c4f221bf4d806478ce0baed674d3985; previous Tigris SHA435b7b1434a467ce801ea58af0b9db4fc6557c81e921d9f80fc65391ed14e4ba. Local verification receipts are in the separate experimental worktree's evaluation/c9671be-build directory.

## Repository comparison: pinned source, not marketing

Inspected complete existing Tigris checkout plus pinned metadata/tree/build/license and selected relevant source modules from five alternatives. Alternatives were not fully built or executed. Exact source-file hashes and pinned revisions are in documentation/RECOVERY-SOURCE-INDEX.json; local raw code and reference artwork are excluded from publication. No copied Apple assets are added.

| Project / revision | Implementation and API evidence | Reuse decision and limitations |
|---|---|---|
| [Tigris](https://github.com/lesnolie/Tigris/tree/c6d258f467e0a8f68f5a112bb5aef9710e81392d), 2.0.27, MIT | Kotlin native Views/RecyclerView; DockLayout, TileAdapter, Shelf, ControlCenter, SettingsActivity, AppRepo, Glass/FrostGlass/MovingGlass, Media3, PhoneServer, Prefs, TvControls. Upstream min28; our guarded min25 variant actually built with desugaring, API wrappers and restricted manifest. 44 JVM test-source files. | Leading base: broad existing features, named owners, tests, working cloud candidate. Not exact tvOS27 acceptance. Profiles/avatar are not multi-user isolation; Shelf is not automatic rich content for every installed app. |
| [tvOS-Launcher](https://github.com/techgenius1/tvOS-Launcher/tree/463fa6c2a9b7322c4407f1d00db935ef09d20f4f), 2.1.5, Apache-2.0 + NOTICE | Flat native Java; build.sh targets min24 using D8, Android34/JDK17. Profiles.java, Search.java, ControlPanelOverlay, RecentSwitcher and Motion. No test paths found in tree. Large MainActivity and manual build ownership increase integration risk. | Reuse profile/search/navigation ideas or modules with notices, after API25 call review. Screenshot has prominent dashboard clock and square dock; it is a distinct design, not evidence of exact latest tvOS. Exclude stock-launcher disabler, debloating and local-ADB actions. Claimed ~38MB PSS/zero idle CPU unverified here. |
| [Hume](https://github.com/petrobot-admin/hume-launcher/tree/19bd8b1fa2d535929c768e6e197a9f464e89f953), 7.8, Apache-2.0 + NOTICE | Native Java, min24 build; Profiles.java isolates three preference namespaces and first-profile legacy data. Search.java combines local app ranking and external catalog/deep links; Motion.java has reduced-motion handling. No test paths found in tree. | Best bounded profile/search donor before custom implementation. Search has India/provider assumptions requiring configuration; API26 WatchNext path needs API25 fallback. AirPlayGuard manages an external receiver; it is not built-in Apple integration. Hero/glass-dock screenshot is a useful comparison, not measured Apple geometry. |
| [DumbTV](https://github.com/humblemuzzu/dumbtv/tree/28828203b9f35796d0c8fb86f0ddf1b0a55f99cc), launcher1.9 | Launcher MIT scope is explicit; build defaults min25 and compiles against android25. Native Java Home/Grid/FireSettings/Catalog; eight launcher test sources. Device catalog includes an AFTMM community entry. Other downloaded tools/blocklists have separate licenses. | Reuse read-only Fire OS capability/settings intent knowledge and test patterns. Community AFTMM claim is not our hardware evidence. Visual/minimal scope cannot replace full tvOS shell. Do not execute debloat/DNS/OTA scripts. |
| [tvHome](https://github.com/hamdalaa/tvhome/tree/eac48f2a04e4895c69b986fd3f2aafcaadeb92a3) | Kotlin/Compose, min26/target34/compile36, recent Compose/Core/Activity/Coil3/Media3 dependencies; actual TMDB hero/search/detail source, accessibility overlays. About13 unit-test source files; lint abortOnError=false. | Visually strong content hierarchy in inspected screenshot, but no LICENSE found: code redistribution/integration blocked pending rights. min26 alone would not reject it; dependency/call backport and GPU/memory budget also require investigation. External benchmark claims cannot establish AFTMM performance. No published release found via latest endpoint. |
| [PhairPlay](https://github.com/mazer666/PhairPlay/tree/bdc51c8e25a0fe4658ed61669c89e907765cdcaf) | Standalone receiver, not Home shell. FireTV flavor min25, GoogleTV min29; ARMv7 in ABI filters, CMake/NDK libplayfair and ALAC, MediaCodec/network/audio paths and protocol test sources. Root Apache-2.0; native subtree provenance still requires full review. Source version alpha.8; latest-release endpoint404, so no released verified APK is asserted. | Prioritize isolated cloud/license/API25/ARMv7 evaluation before writing a receiver. README admits protected Apple Music and buffered type103 playback gaps; Cast/Miracast control plane is incomplete. Do not call its AirPlay2 label proof. No iPhone/Mac interoperability or AFTMM test was performed. |

No measured winner for lowest memory, GPU cost or input latency exists. Source architecture favors Tigris for current validation and Hume for small reusable missing capabilities; actual 1:1 acceptance remains open for every candidate. Static screenshots demonstrate composition only, not focus, animation, navigation or genuine behind-control behavior.

## Full product vision matrix

Exactly one status is assigned to each feature group below for the **Tigris-based target**, independent of the preserved 119-row Mantis audit. “Partially implemented” includes missing exact-tvOS behavior/acceptance; it never grants completion for compilation. Source names are under app/src/main/java/com/leslie/tvhome unless stated otherwise.

| Feature | Status | Existing implementation / source; compatibility, evidence and remaining work |
|---|---|---|
| Home layout | Partially implemented. | Tigris HomeActivity/DockLayout/TileAdapter compiled min25. Retain real catalog/launch logic; measure latest Apple proportions/type/artwork hierarchy and compare physical screenshots. |
| Top Shelf | Partially implemented. | Shelf/ShelfJson/ShelfNavigation, provider-specific data. Not universal Continue Watching. API25 fallback and actual media/deep-link acceptance pending. |
| Dock | Partially implemented. | DockLayout/Glass/DockGlassFallback. Keep existing renderer; compare dock geometry/material states to versioned Apple references on AFTMM. |
| Focus engine | Partially implemented. | TileAdapter/Home focus navigation/scaling; inherited Mantis focus restoration issues are version-specific. Physical repeat/hold/return/scroll/parallax pending. |
| Animations | Partially implemented. | Existing View animation/glass transitions, extreme glass off in Mantis defaults. Match measured timing and reduced motion; no AFTMM frame data yet. |
| Control Center | Partially implemented. | Tigris ControlCenter + TvControls, c967 availability gates compiled. Real volume/output/settings capabilities and actual feedback require firmware tests; no fake privileged toggles. |
| App Switcher | Partially implemented. | AppSwitcherRouter/recent ordering/local history. Task screenshots/OS force-stop are not provided by this experiment; runtime carousel/navigation pending. |
| App Library | Partially implemented. | AppRepo/Folders/TileAdapter discovers/launches real apps. Exact library categorization/layout and physical persistence/navigation unverified. |
| Search | Partially implemented. | Preserved Mantis local app search only; Tigris lacks full target unified search. Adapt Hume/tvOS-Launcher ranking and destinations, configurable providers and API25 fallbacks. No Siri search. |
| Settings | Partially implemented. | SettingsActivity + existing bilingual resources and 18 restricted-control explanations compiled. Align category/detail geometry and test capability results and focus. |
| Screensaver | Partially implemented. | PhotoFrame/Aerials/Media3 exist; foreground fallback. System dream takeover excluded. Decode/network/codec fallback, remote return and rights tests pending on API25. |
| Wallpapers | Implemented but not physically tested. | Wallpapers/VideoWallpaper/MediaFiles/Wallhaven and bounded compat quality exist in candidate. Not a claim of exact Apple rendering or all codecs; hardware decode/soak remains. |
| Profiles | Partially implemented. | Tigris avatar/preferences are not isolated users. Mantis has local profile logic; audit/adapt Hume three-profile namespace model and deletion/migration guards. Apple account profiles absent. |
| Media interfaces | Partially implemented. | NowPlaying/Shelf/Media3/launch intents. Genuine resume/seek/error/output acceptance and tvOS composition missing; DRM stays with installed authorized applications. |
| Remote interactions | Partially implemented. | D-pad/Back/actions exist, double-HOME/guard excluded in Mantis. Physical remote input, hold/repeat/return and accessibility regressions pending. |
| Apple TV application compatibility | Partially implemented. | Generic installed-app launching is implemented; no verified Apple TV login/playback/DRM on this build/device. Availability and service compatibility must be tested, never fabricated. |
| AirPlay | Not implemented. | No receiver in either Mantis runtime. PhairPlay is a candidate, not integrated/tested; native licenses, encrypted sessions, API25 audio/video and real senders pending. |
| iPhone Remote | Not implemented. | PhoneServer is a tokenized upload/config page, not native Apple Remote pairing/control. Audit reusable secure companion approach separately. |
| Mac connectivity | Partially implemented. | Tokenized browser upload/config can be used by a Mac browser in principle; not physically validated. No Mac mirroring/AirPlay integration. |
| Apple Music alternatives | Partially implemented. | Real installed-app shortcuts only. No integrated Apple Music service/protected audio receiver; evaluate authorized existing apps/protocol restrictions. |
| Photos alternatives | Partially implemented. | PhoneServer photo upload and PhotoFrame exist, untested on candidate. No iCloud Photos/account sync; evaluate legal upload/casting paths. |
| Accessibility | Partially implemented. | Focus and motion/contrast options in UI; broad screen-reader, reduced-motion and remote acceptance missing. Guard/accessibility takeover excluded. |
| System integration | Partially implemented. | Existing intents/TvControls/Api25Compat; foreground app isolation. Full FireOS6 family, HOME, boot/wake, brightness/HDMI and permissions not certified. |
| Full OS replacement | Not implemented. | No kernel/drivers/firmware/bootloader replacement. Excluded firmware/root/exploit operations remain excluded; UI recreation does not change OS. |

## API25, firmware and performance truth

Our experiment is an actual min25/desugared build with API wrappers, not merely an edited minSdk. Media3/RecyclerView/NanoHTTPD/ZXing/dadb build paths and dependency lint were checked in cloud. Zero NewApi findings is static evidence, not proof of every runtime path on FireOS. No native library in this APK removes an ABI packaging risk; it does not validate the graphics driver, decoder, services or permissions.

Historical exact-device evidence: AFTMM, Android7.1.2 API25 ARMv7, FireOS6.7.1.1(NS6711/5908), MemTotal1,316,676KiB. Mantis0.4 launch ~840ms, initial PSS37.58MiB/after-interactions72.20MiB and mixed-session gfx jank3.21%; Mantis0.5 launches1193/1250ms. These are narrow historical observations, not controlled Tigris comparisons or family-wide certification. All other documented FireOS6 revisions remain unverified. Use capability probes/fallbacks, never a brand string or OS-update assumption.

Tigris light profile already selects graphite/static first run, compat wall/aerial quality, existing glass/focus, extreme glass off and no guard/idle-maintenance. Do not prematurely replace cached glass with flat styling. Measure before enabling video/dynamic capture or new overlays. Required same-device gates: cold/warm distributions; idle and active CPU; PSS/heap/GC peaks; bitmap/cache bounds; frame-time distribution and focus input-to-visible latency; GPU load/thermal trends; media return; network/cache storage growth; foreground/background lifecycle and long soak. No new performance improvement is claimed. Native process/receiver addition needs a separate RAM/idle budget.

## Architecture problems and avoidable effort

The earlier minimal Mantis custom Home renderer and incomplete focus handling are useful fallback/regression evidence but should not be independently expanded while a richer reusable candidate is being validated. Stop parallel custom Home/glass/search/profile rewrites. Existing Tigris ownership and capability adapters are a better starting point. Existing settings callbacks that cannot work in the restricted flavor need truthful capability UI, not substitute success messages.

Custom code remains justified for the separate min25 flavor, Api25Compat, isolated manifest/safety gates and capability presentation: upstream lacks the user's constrained FireOS6 experiment contract. Keep these changes small and shared; no per-brand duplicated feature engine, new daemon or plugin loader. Profiles/search should begin with donor source/license and migration tests, not a new hardcoded screen. Hume/launcher provider constants must become configuration only where the existing design requires it. Exclude unlicensed tvHome code pending rights.

## Resource policy and next ten engineering tasks

Observed free disk ~44.08GiB, above normal34.241GiB reserve; system memory free43%. Existing APK inspection tools only. Prior one-run authorization was consumed by run42899148489; this resume recovered it and started zero builds. No unlimited cloud retries or local toolchain/cache installs. If a limit is reached, save and display **PAUSED — WAITING FOR USER AUTHORIZATION**; do not auto-resume.

1. Finish existing PhoneServer/page capability state: blocked presentation-asset downloads must be unavailable before click; preserve user uploads and removal. Reuse current API/page/localization, no new server.
2. Establish durable signing/update policy and migration checks before distributing an installable successor. Never overwrite/uninstall another certificate's package automatically.
3. One explicitly bounded next verification run when warranted by saved source changes; all three flavor tests, both Mantis lints, integrity and API25 signing. No repeated build of already verified c9671be.
4. Under a later device-test authorization, isolated foreground AFTMM launch/crash/remote/escape smoke with original apps and HOME retained. Current task keeps device untouched.
5. Versioned official latest-tvOS reference pack and measured Home/Dock/TopShelf/focus states. Older tvOS26 and third-party screenshots remain labeled references; never assert exact27 comparison from them.
6. Controlled Tigris/Mantis hardware performance comparison; tune existing caching/quality/frame scheduling from measurements before renderer changes.
7. Adapt legally cleared Hume Profiles storage/migration model into existing Prefs boundaries; preserve legacy preferences, verify isolation/deletion/restore.
8. Adapt existing local Search ranking/provider destinations, remove regional hardcoding through configuration, verify actual launch/failure states and API25 fallback.
9. Audit/build isolated PhairPlay FireTV/ARMv7 receiver in free cloud only after complete native license/API/security review; then separately validate lawful iPhone/Mac senders. Do not equate protocol labels to Apple ecosystem completion.
10. Complete media/shelf/screensaver/accessibility regressions and exact-firmware capability coverage, then promote a replacement only after measured functional/fidelity/performance gates.

## Continuation record

Audit complete; next bounded source task is item1. Its final source/test checkpoint is recorded in STATUS.md and the experimental evaluation/PHONE-CAPABILITY-FOLLOWUP.md. It is not part of the recovered APK unless a later build explicitly identifies it. No release is silently replaced; this report is prepared for the next publication and remains a local saved checkpoint until a report-bearing release is actually published.

Completed continuation: the existing phone page/server now express optional-download availability and enforce a localized HTTP403 restriction while preserving uploads/deletion. Existing localization coverage and executable host tests for blocked/deletion/legacy/busy transitions passed. New source has no Android build or physical verification yet. Existing APK and Mantis hashes remain unchanged.

Local saved experimental checkpoint: `0ce75c4` (phone capability follow-up + recovered evidence). This commit is not yet cloud-built or publicly published; APK runtime remains a03d9546e63244641e97c6385235270e4e0406d3. Source formatting checks passed; the unchanged downloaded build log contains one original trailing space and is retained byte-for-byte for checksum evidence.
