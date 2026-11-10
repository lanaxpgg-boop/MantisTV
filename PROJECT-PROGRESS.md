# MantisTV project progress audit

Audit date: November 10, 2026 (Europe/Sarajevo). Initial source audit:2026-11-10T09:25:40.547570+00:00; physical update:2026-11-10T14:49UTC.

## 1. Overall development milestone

**Native foreground TV application preview; Priority 0 build checkpoint reached, basic exact-build physical smoke exercised; full acceptance outstanding.** MantisTV is not a complete tvOS recreation, an Apple TV substitute with Apple services, a system launcher replacement, or a replacement operating system. Priority 1 has basic local UI implementations; Priority 2 has no implemented Apple protocols and only generic installed-app launching; Priority 3 is preliminary documentary research.

The original mission requires a coherent entertainment operating environment on AFTMM, complete firmware-family support, hardware validation and Apple ecosystem capabilities. The source currently implements a small Android Activity application on stock Fire OS. A signed APK and successful compilation do not close that gap.

No overall percentage is assigned: build tooling, a renderer, a physical benchmark and an AirPlay receiver are not equally sized units of work. Counts below describe audit rows only, not engineering effort or feature completeness.

## 2. Scope, evidence and status rules

The matrix covers every original mission feature group and bullet, its build/QA/compatibility obligations, the existing TVOS-FEATURE-MATRIX, Apple's documented tvOS 27/27.2 update-summary features, and inherited design/ecosystem features relevant to the mission. It is not an inventory of undocumented Apple capabilities. Shared requirements (for example favorites/recent apps across screens, network/display information in Settings and Controls) have one authoritative feature row rather than duplicate completion credit.

Apple reference is tvOS 27.2, with a previously verified official release date of October 26, 2026: [update summary](https://support.apple.com/en-us/106336), [security release](https://support.apple.com/en-us/149524). These pages were retrieved earlier in this same November 10 execution. The initial refresh returned HTTP429. The Irish official update-summary page was successfully retrieved during the subsequent design update and still identifies27.2; some detailed guides remain unavailable. Screen references carry their own version provenance.

Each feature below has exactly one status:

- **Completed and verified.** Narrow acceptance behavior is directly covered by test/artifact evidence and source inspection. For UI, device and ecosystem behavior, compilation alone never qualifies. Narrow basic UI behaviors now have device evidence; this does not establish tvOS fidelity or full integration acceptance.
- **Implemented but not physically tested.** A concrete runtime implementation exists; Android/UI/physical behavior has not been established.
- **Partially implemented.** A substantive subset exists, with a specific functional or evidence gap. Architecture prose alone is not a runtime implementation.
- **Not implemented.** No relevant runtime implementation/test capability was found. A shortcut to another application is not an implementation of its services.
- **Blocked.** A named prerequisite or current mission constraint prevents the milestone: secure persistent signing setup or forbidden protected-system modification. This is not evidence the feature works.

Audited pre-publication HEAD: `a1a51fe520363c2512eb14a79e4c17998c8ad0ad`. The release APK's compiled source commit is `ab022168a5d8fff173ff0169fd328adb2040be89`. GitHub comparison shows only STATUS, LATEST-VERIFIED-BUILD and the routine workflow changed between them; runtime source did not change. The audit release will identify its documentation commit separately in AUDIT-PUBLICATION.json; it must not imply a newly compiled application.

Evidence aliases (all immutable source at the audited HEAD):

- **M**: [MainActivity.java](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/app/src/dev/mantistv/home/MainActivity.java), 602 lines: lifecycle, home, navigation, controls, profiles and watchlist.
- **A**: [AppCatalog.java](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/app/src/dev/mantistv/home/AppCatalog.java), discovery and public launch intents.
- **L**: [LandscapeView.java](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/app/src/dev/mantistv/home/LandscapeView.java), procedural renderer and clock.
- **R**: [StoreRules.java](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/app/src/dev/mantistv/home/StoreRules.java), pure data rules.
- **T**: [StoreRulesTest.java](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/tests/StoreRulesTest.java), the entire host rule test suite.
- **B**: [build.sh](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/scripts/build.sh), compilation, debug packaging and development signing.
- **I**: [inspect-apk.py](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/scripts/inspect-apk.py), package/manifest/ZIP/native-library policy checks.
- **G**: [lint.sh](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/scripts/lint.sh), the explicit seven-rule lint gate.
- [Manifest](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/app/AndroidManifest.xml), [complete tree](https://github.com/lanaxpgg-boop/MantisTV/tree/a1a51fe520363c2512eb14a79e4c17998c8ad0ad), [firmware data](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/device/firmware-catalog.json), [feature matrix](https://github.com/lanaxpgg-boop/MantisTV/blob/a1a51fe520363c2512eb14a79e4c17998c8ad0ad/documentation/TVOS-FEATURE-MATRIX.md).
- [Verified 0.4 release](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/quality-42747779541), [successful quality run](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42747779541), its actual unit, lint, signature, manifest and checksum assets. At the initial source audit no Android/device test run or screenshot was found. Subsequent physical evidence is linked below.

## 3. Feature inventory


| Status | Feature rows |
|---|---:|
| Completed and verified. | 22 |
| Implemented but not physically tested. | 4 |
| Partially implemented. | 45 |
| Not implemented. | 46 |
| Blocked. | 2 |

Total: **119 distinct audited feature/engineering rows**. These counts are not a progress percentage.

### Home and navigation

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| H01 | Foreground Home screen | Completed and verified. | M.home, M.show; physical 2026-11-10 DEVICE-TEST-REPORT: Physical Home rendering and explicit launch/reopen verified on exact NS6711/5908; screenshot001/018 and LAUNCH-RESULT. Basic foreground Home acceptance only; fidelity separately fails. |
| H02 | Cinematic hero artwork and poster presentation | Partially implemented. | M.home; app/res: Procedural mountains and static text only; no cinematic title posters, licensed hero feed or video artwork. |
| H03 | Top Shelf | Partially implemented. | M.home:240-245: At most six local recent app launches; no provider content shelf, previews or recommendations. |
| H04 | Application dock | Partially implemented. | M.home, M.section: A six-column app grid inside the scrolling page, not a persistent tvOS-style dock. |
| H05 | Favorites throughout Home and Library | Partially implemented. | M.home:236-239; M.appActions: Add/remove and per-profile storage exist, but an empty saved list repopulates Home with default apps. |
| H06 | Recent applications opened through MantisTV | Completed and verified. | M.launch; R.recent; physical 2026-11-10 DEVICE-TEST-REPORT: Official Appstore launch appeared in Recent after own-app process restart; recent-after-restart XML. Only launches through MantisTV are observed. |
| H07 | Continue Watching | Not implemented. | M.home:243-245; source tree: Only an explicit unavailable message; no provider/session/progress implementation. |
| H08 | Dynamic Home backgrounds | Partially implemented. | M.show; L.onDraw: Two selected palettes and static Home landscape; periodic landscape motion is fullscreen-screensaver-only. |
| H09 | Focus transitions and reduced motion | Partially implemented. | M.focus:118-130; physical 2026-11-10 DEVICE-TEST-REPORT: D-pad focus changes and Reduce motion toggle exercised; reduced-motion clock defect reproduced, parallax/timing/fidelity not accepted. |
| H10 | tvOS-like focus engine and restoration | Partially implemented. | M.show:144-222; M.section: Android focus plus string tags; duplicate app tags and rebuilt pages limit restoration fidelity. |
| H11 | Parallax animations | Partially implemented. | L.onDraw:54-61; M.focus: Small horizontal landscape layer drift in screensaver; no app-card tilt/depth or Home parallax engine. |
| H12 | Liquid Glass-inspired rendering | Partially implemented. | M.glass:103-108: Rounded translucent backgrounds; no blur, optical refraction or material sampling. |
| H13 | D-pad and OK navigation | Completed and verified. | M.focus; M.section; M.dispatchKeyEvent; physical 2026-11-10 DEVICE-TEST-REPORT: Physical D-pad four directions and OK moved actual focus/opened pages; QA-EVENTS and library/search/watchlist XML. Held OK is a separate unverified path. |
| H14 | Back and Menu behavior | Completed and verified. | M.dispatchKeyEvent:570-580; M.onBackPressed:583-595; physical 2026-11-10 DEVICE-TEST-REPORT: Back dismissed screensaver/returned Home; Menu opened Controls; double Back exited to original Amazon Home; QA-EVENTS and foreground observations. |

### App Library and management

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| A01 | Installed application discovery | Completed and verified. | A.discover:17-51; physical 2026-11-10 DEVICE-TEST-REPORT: Installed launch targets displayed in physical Library and official Appstore launched. Discovery uses public APIs; not all package metadata or firmware variants accepted. |
| A02 | Application categories | Partially implemented. | M.library:355-369: All/Favorites/Recently opened filters; no semantic media/game categories or editable taxonomy. |
| A03 | Application icons | Partially implemented. | M.loadIcon:299-322; physical 2026-11-10 DEVICE-TEST-REPORT: Physical icon grid rendered but Amazon miniTV resource warning/default-icon fallback observed. All-app icon correctness not verified. |
| A04 | Application metadata | Partially implemented. | A.AppItem; M.search: Package, label and launch intent only; no descriptions, ratings, content metadata or rich catalog. |
| A05 | Application launching | Completed and verified. | A.discover; M.launch:323-333; physical 2026-11-10 DEVICE-TEST-REPORT: Focused Appstore card launched com.amazon.venezia/.grid.AppsGridLauncherActivity and Back returned MantisTV. General launch-intent behavior only, not media integration. |
| A06 | Application management | Partially implemented. | M.appActions; M.settings: Favorites, refresh and guarded external app-info link; no install/update/uninstall manager or package lifecycle integration. |
| A07 | Application list refresh | Implemented but not physically tested. | M.settings:482-486: Manual rediscovery and icon-cache eviction; no package-change receiver or background subscription. |

### Control Center

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| C01 | Control Center interface | Partially implemented. | M.controls:402-426: A foreground app page; no global overlay over streaming applications. |
| C02 | Audio controls | Partially implemented. | M.volume:427-437: Music stream +/- with fixed-volume/security fallback; HDMI/receiver behavior and route control unverified. |
| C03 | Network status and information | Completed and verified. | M.controls:404-408; physical 2026-11-10 DEVICE-TEST-REPORT: Physical Menu Controls displayed Network · WIFI using active network API. Network configuration and disconnection transitions not covered. |
| C04 | Display information | Completed and verified. | M.controls:409-413; physical 2026-11-10 DEVICE-TEST-REPORT: Physical Controls displayed1920×1080/59.94Hz matching logical baseline. Native4K quality/HDR/audio mode validation not covered. |
| C05 | Compatible network/audio/display/system shortcuts | Partially implemented. | M.controls; M.safe:438-448: ResolveActivity and exceptions provide fallback; actual exported targets on each firmware are unknown. |
| C06 | Sleep control | Not implemented. | M.controls:423-425: Text delegates sleep to the physical Fire TV remote; no app sleep action. |
| C07 | Manual screensaver shortcut | Completed and verified. | M.controls:422; M.screensaver; physical 2026-11-10 DEVICE-TEST-REPORT: Controls → Screensaver selected with D-pad/OK; screenshot014 rendered landscape; Back dismissed. |
| C08 | Control Center application shortcuts | Not implemented. | M.controls: No dedicated app shortcut controls; generic launching lives in Library/Home. |
| C09 | System-wide Control Center access | Not implemented. | Manifest; M.dispatchKeyEvent: No accessibility service, overlay, global remote listener or background service. |

### App Switcher

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| S01 | App Switcher overview | Partially implemented. | M.show:193-198: A local recent-launch list; no OS task snapshots, live cards or complete task enumeration. |
| S02 | Switch existing application tasks | Partially implemented. | M.launch; A.discover: Starts public launch intents; does not explicitly select/resume a task by task ID. |
| S03 | Recent-list remote navigation | Implemented but not physically tested. | M.show; M.section: Native focusable cards exist; end-to-end navigation not tested. |
| S04 | Application lifecycle management | Partially implemented. | M.onPause/onDestroy; source tree: Own handler/executor cleanup exists; no lifecycle control, force-stop or task management for other apps. |

### Settings

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| SE01 | Appearance settings | Implemented but not physically tested. | M.settings:472-481: Two palettes and reduced motion, scoped to the selected local profile. |
| SE02 | Wallpaper selection and management | Not implemented. | M.settings; source tree: No wallpaper picker, import, persistent custom media or wallpaper library. |
| SE03 | Applications settings | Partially implemented. | M.settings:482-486; M.appActions: Refresh and external information links only; no dedicated comprehensive application settings module. |
| SE04 | Device information | Implemented but not physically tested. | M.settings:491-497: Model/device/display build/API/ABIs are shown from runtime getters; no device snapshot was collected. |
| SE05 | Audio format, route and capability information | Not implemented. | M.controls; M.settings: No codec, passthrough, HDMI sink, supported format or active audio route inspection. |
| SE06 | Accessibility settings | Partially implemented. | M.focus; M.settings:477-489: Reduce motion, basic descriptions and external accessibility shortcut; no full text/contrast/screen-reader/focus audit. |
| SE07 | Recovery settings | Partially implemented. | M.settings:490; rollback guide: Finish/return-to-Fire-OS and documented uninstall; no tested recovery, backup or restore module. |

### Screensaver

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| SC01 | Original landscape screensaver | Partially implemented. | L; M.screensaver:558-568; physical 2026-11-10 DEVICE-TEST-REPORT: Landscape rendered under real idle/manual activation, but reduced-motion clock freezes; original procedural scenery remains unlike licensed aerial tvOS screensaver. |
| SC02 | High-quality licensed aerial images/video | Not implemented. | app/res; source tree: Only two vector icon/banner resources and procedural artwork; no aerial media assets or player. |
| SC03 | Automatic idle behavior | Completed and verified. | M.armIdle:76-80; lifecycle methods; physical 2026-11-10 DEVICE-TEST-REPORT: Real no-input idle: Home at~295.6s, screensaver at~327.5s; screenshots002/003. Back dismissal verified; exact trigger instant and all lifecycle cases not measured. |
| SC04 | Smooth transitions and scene sequencing | Partially implemented. | L.onDraw: Layer translation at nominal 100 ms invalidations; no scene crossfades, media sequencing or hardware smoothness evidence. |
| SC05 | System screensaver replacement | Not implemented. | Manifest: No DreamService or system idle integration; does not replace Fire OS screensaver/sleep. |

### Search

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| Q01 | Installed application search UI | Completed and verified. | M.search:370-401; physical 2026-11-10 DEVICE-TEST-REPORT: Physical Settings query no-match then Appstore matching result; search XML/screenshots008/009. Installed-app search only, no content/Siri search. |
| Q02 | Content discovery | Not implemented. | M.search; source tree: No film/show index, metadata provider or provider search integration. |
| Q03 | Local metadata search beyond app label/package | Partially implemented. | R.matches; A.AppItem: Only installed application names and package strings; no local media index or richer fields. |
| Q04 | Compatible legal remote content sources | Not implemented. | Manifest; source tree: No source adapters, HTTP client or network permission for remote discovery. |
| Q05 | Voice or Siri search | Not implemented. | Manifest; source tree: No microphone, speech recognition or Siri integration. |

### Profiles

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| P01 | Multiple local profiles | Partially implemented. | M.profiles:499-514; physical 2026-11-10 DEVICE-TEST-REPORT: Profile 1→2→1 switching and watchlist isolation verified physically. Profiles3/4, rename and all data classes not yet accepted. |
| P02 | Per-profile preferences | Partially implemented. | M.key/list/save/settings; physical 2026-11-10 DEVICE-TEST-REPORT: Watchlist isolation/persistence and Reduce motion toggle verified on device; all preference/favorite/recent cross-profile cases unverified. |
| P03 | Local watchlist | Partially implemented. | M.watchlist:515-557; physical 2026-11-10 DEVICE-TEST-REPORT: QA title save, rendering, profile isolation and process-restart persistence verified. Deletion and all edge/limit cases not exercised physically. |
| P04 | Personalized Home experience | Partially implemented. | M.home; M.profiles: Profile name, favorites and recent launches only; no content recommendations, viewing progress or account personalization. |
| P05 | Streaming/Apple account profile switching | Not implemented. | M.profiles; ecosystem doc: Local profile selection does not switch accounts inside streaming apps. |

### Apple ecosystem

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| E01 | Official Apple TV application integration | Not implemented. | A.discover; M.launch: No Apple-specific adapter or verified Apple TV compatibility/playback. Generic installed-app launching is covered by A05 and earns no Apple-integration completion credit. |
| E02 | AirPlay-compatible receiver | Not implemented. | Manifest; complete source tree: No receiver, protocol stack, discovery, pairing or media transport. |
| E03 | iPhone Remote control | Not implemented. | Manifest; complete source tree: No Apple Remote pairing/protocol or authenticated custom remote endpoint. |
| E04 | Mac connectivity | Not implemented. | Complete source tree; ecosystem doc: Proposed only; no implemented Mac sharing/control/continuity module. |
| E05 | Photos sharing | Not implemented. | Manifest; complete source tree: No share receiver/importer, photo protocol or Apple Photos integration. |
| E06 | Music integration | Not implemented. | Complete source tree: No player, library, audio streaming or Apple Music integration. |
| E07 | Siri Shortcuts integration | Not implemented. | Ecosystem doc; complete source tree: Architecture prose only; no implemented authenticated API, actions or shortcut bridge. |
| E08 | Home Assistant integration | Not implemented. | Complete source tree: No adapter, pairing, discovery or automation endpoint. |
| E09 | Homebridge / HomeKit integration | Not implemented. | Complete source tree: No bridge, accessory service, pairing or certification. |

### Apple tvOS 27 / 27.2 features

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| T01 | Apple Music Hi-Res Lossless | Not implemented. | Complete source tree; Apple reference: No MantisTV music pipeline; launching another app does not implement this feature. |
| T02 | Apple Music AutoMix | Not implemented. | Complete source tree; Apple reference: No track analysis, mixing, transitions or Apple Music service integration. |
| T03 | Video podcasts and redesigned Podcasts experience | Not implemented. | Complete source tree; Apple reference: No podcast catalog/player or redesigned media UI. |
| T04 | AirPlay performance enhancements | Not implemented. | Complete source tree; Apple reference: No AirPlay implementation exists to optimize or test. |
| T05 | Transcribed audio captions | Not implemented. | Complete source tree; Apple reference: No media player, transcription or caption renderer. |
| T06 | Dynamic Text accessibility behavior | Not implemented. | M.text; Apple reference: Fixed app-selected text sizes using Android TextViews; no tvOS Dynamic Text feature implementation/validation. |
| T07 | Blocking individual apps / parental access controls | Not implemented. | M.profiles; complete source tree: Profiles explicitly are not access controls; no app restriction policy or authentication. |
| T08 | FaceTime links | Not implemented. | Complete source tree; Apple reference: No FaceTime, calling client, links or continuity integration. |
| T09 | FaceTime third-party interpreter/caption services | Not implemented. | Complete source tree; Apple reference: No video-calling/caption-service implementation. |
| T10 | iPhone-selected specific Photos for screensaver | Not implemented. | Complete source tree; Apple reference: No iPhone pairing or photo selection/import pipeline. |
| T11 | Screensaver category shuffle | Not implemented. | L; Apple reference: One procedural scene; no selectable categories or shuffle scheduler. |
| T12 | Find My for supported Siri Remotes | Not implemented. | Complete source tree; Apple reference: No Find My network, remote accessory or Apple entitlement integration. |

### Inherited tvOS design/ecosystem scope

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| T13 | Apple Music Sing using iPhone microphone | Not implemented. | Complete source tree; Apple reference: No microphone transport, karaoke player or Apple Music Sing integration. |
| T14 | Collaborative music queue and reactions | Not implemented. | Complete source tree; Apple reference: No shared sessions, queue or reaction service. |
| T15 | Lyrics Translation | Not implemented. | Complete source tree; Apple reference: No lyric data, translation or synchronized lyric UI. |
| T16 | Lyrics Pronunciation | Not implemented. | Complete source tree; Apple reference: No pronunciation/transliteration pipeline or lyric UI. |
| T17 | FaceTime Contact Posters | Not implemented. | Complete source tree; Apple reference: No call/contact integration. |
| T18 | FaceTime Live Captions | Not implemented. | Complete source tree; Apple reference: No calling, caption processing or language support. |
| T19 | Incoming call notifications via compatible HomePods | Not implemented. | Manifest; complete source tree: No notification listener, call continuity or HomePod service. |
| T20 | Profile chooser when waking | Not implemented. | M.onCreate/onResume/profiles: Restores last local profile; no device-wake profile picker or wake integration. |
| T21 | Aerial landscape category selection / India library | Not implemented. | app/res; L: No aerial assets, geographic library or category browser. |
| T22 | Default AirPlay speaker selection | Not implemented. | M.volume; complete source tree: Local music-stream volume only; no AirPlay audio routing or speaker discovery. |

### Foundation, compatibility and delivery

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| F01 | Stock Fire OS foundation preserving hardware media support | Partially implemented. | Manifest; architecture doc: Ordinary app leaves vendor foundation in place; decoding, DRM, HDCP and audio preservation not physically tested. |
| F02 | Direct API 25 compilation | Completed and verified. | B:7-17; successful run 42747779541: Official platform-25 android.jar used for compile/link/DEX; verifies source API surface, not Fire OS behavior. |
| F03 | 32-bit-compatible packaging inspection | Completed and verified. | I:10-27; APK ZIP inspection: No native libraries and DEX version 037; packaging evidence only, not an ARM32 execution test. |
| F04 | Complete AFTMM firmware inventory/matrix | Partially implemented. | device/firmware-catalog.json: 41 full plus 35 incremental catalog records, all C; not 76 distinct proven compatible releases or exhaustive official history. |
| F05 | Per-firmware package/permission/service/launcher analysis | Not implemented. | firmware-catalog.json: Fields explicitly unknown/unverified; no package extraction, firmware binary diff or device evidence. |
| F06 | Runtime capability detection across firmware | Partially implemented. | M.safe; M.volume; M.settings: Some intent resolution, fixed-volume and exception handling; not a complete service/permission/capability abstraction. |
| F07 | All-firmware AFTMM compatibility validation | Partially implemented. | Device audit; no hardware run: Device-family API 25 compilation/catalog evidence exists, but no per-build B/API-source verification or A/physical acceptance. Catalog records all remain C. |
| F08 | Physical AFTMM installation and functional acceptance | Partially implemented. | Build report: Installed or physically tested NO; physical 2026-11-10 DEVICE-TEST-REPORT: Verified normal install, launch and bounded physical smoke suite on exact AFTMM NS6711/5908. Two clock defects; full functional/fidelity/media/rollback acceptance incomplete. |
| F09 | Hardware startup, RAM, GPU, 4K and storage benchmarks | Partially implemented. | Performance budget; no measurements; physical 2026-11-10 DEVICE-TEST-REPORT: Actual launch timings, PSS snapshots, cumulative gfxinfo and cpuinfo collected; PERFORMANCE-REPORT. No repeated distributions, GPU/thermal/native4K or peak validation. |
| F10 | Long-term stability, thermal and soak validation | Partially implemented. | QA report; no soak artifacts; physical 2026-11-10 DEVICE-TEST-REPORT: About24min bounded mixed QA, real idle and one deliberate restart; no observed captured fatal/ANR/OOM. Long/thermal soak remains unperformed. |
| F11 | Full OS replacement / protected system UI | Blocked. | Manifest; original mission safety constraints: Not implemented; current authorized stock-app strategy and no-root/no-flash/no-system-modification constraints prevent claiming this milestone. |
| F12 | Custom ROM / stock OS foundation comparison | Partially implemented. | OS-FOUNDATION-COMPARISON.md: Preliminary documentary comparison; no ROM build, vendor-blob/DRM/audio audit or installed comparison. |
| F13 | Bootloader research | Not implemented. | Complete tree; repository audit: Device-tree reference is not a completed bootloader analysis; no exploitation or unlock performed. |
| F14 | Deeper Fire OS/system service integration | Not implemented. | Manifest; complete source tree: No privileged/system UI integration, HOME replacement, boot start or global event service. |
| F15 | Modular maintainable implementation | Partially implemented. | Four Java classes; M:602 lines: Catalog/scene/rules separated, but most screens, profile persistence and control logic remain in one Activity. |
| F16 | Complete candidate repository/source/license comparison | Partially implemented. | REPOSITORY-AUDIT.md; LICENSE-AUDIT.md: One baseline built; other candidates reviewed preliminarily, without complete maintenance/RAM/reproducibility/asset provenance audits. |
| F17 | MantisTV license and runtime dependency declaration | Completed and verified. | LICENSE; source/tree/APK: MIT license present; four native Java classes use framework APIs; no third-party runtime jars/fonts/native assets in this source tree. |
| F18 | Untouched upstream baseline build/artifact delivery | Completed and verified. | Run 42744518215; baseline release: Existing baseline APK/source/license/NOTICE assets and successful build record verified; not a device compatibility claim. |
| F19 | Cloud preview/debug compilation and packaging | Completed and verified. | B; successful release/run: Two separately packaged APKs were built; debug flag/package policy checked. Completion applies only to build outputs. |
| F20 | Development APK signature verification | Completed and verified. | signature.txt; debug-signature.txt; B: Existing CI verification passed with SDK25 floor; ephemeral development certificate, not production signing. |
| F21 | Source ZIP and SHA-256 deliverables | Completed and verified. | Actual release bytes and checksum file: Preview/debug/source hashes and ZIP integrity independently rechecked in memory; selected source ZIP files match audited source. |
| F22 | Pure history/search/profile-ID rule tests | Completed and verified. | T:8-27; unit-tests.txt: One host-JVM main with nine check calls and one expected-exception scenario; only these narrow rules are verified. |
| F23 | Seven selected Android lint checks | Completed and verified. | G:10-18; lint.txt/xml: Zero findings for NewApi, InlinedApi, MissingPermission, WrongThread, HandlerLeak, DrawAllocation, StaticFieldLeak. |
| F24 | Comprehensive static/dependency analysis | Partially implemented. | G; B; lint-pom.xml: Selected lint and native source compile exist; not a full lint sweep, dependency vulnerability/license/SBOM or vendor API audit. |
| F25 | Android UI/instrumentation/persistence tests | Partially implemented. | tests tree; physical 2026-11-10 DEVICE-TEST-REPORT: Existing ADB physical UI/persistence checks with XML/screenshots and assertions now exist; no comprehensive Android instrumentation or regression suite. |
| F26 | Installation, rollback and recovery validation | Partially implemented. | Installation/rollback guides; physical 2026-11-10 DEVICE-TEST-REPORT: Verified artifact checks, normal installation and safe Amazon Home exit performed. Guides preserved; update/removal/rollback/recovery not exercised. |
| F27 | Update-stable production signing/release | Blocked. | B:20-23; release notes: No persistent protected keystore is provisioned; each clean build generates an ephemeral key. Needs secure signing setup. |
| F28 | Free cloud workflow route | Completed and verified. | Public CI workflow/run records: Standard ubuntu-24.04 public jobs and GitHub Releases; no Actions cache/upload-artifact or paid service configured in the inspected workflows. |
| F29 | Resource monitoring and low-impact builds | Partially implemented. | Resource script; CI; JAVA_TOOL_OPTIONS: Disk reserve gates and JVM cap exist; not continuous memory-pressure/usage/device-resource monitoring. |
| F30 | Measured performance optimization | Partially implemented. | M.icon cache; L; prior/final lint reports: Bounded icon cache and draw preallocation exist; source-level warning reduction is verified, actual hardware improvement is unmeasured. |
| F31 | Public-API, no-DRM-bypass app design | Completed and verified. | Complete original source and manifest: No private API calls, receiver/DRM implementation, root/flash/device-control code in MantisTV runtime; not Apple certification. |
| F32 | Complete accurate project documentation | Partially implemented. | Audited documentation/history: Substantial docs exist, but prior version/lint/backlog claims drifted and firmware/hardware sections remain incomplete. |

## 4. Implemented and missing functionality

The physical smoke suite now verifies narrow foreground Home, D-pad/OK/Back/Menu, app discovery/launch-return, installed-app search, recent history after restart, manual/idle screensaver and local watchlist save/isolation/persistence behaviors. See [exact test report](device-test/2026-11-10/DEVICE-TEST-REPORT.md). No full feature is credited from compilation alone. Some partially tested composite features remain Partially implemented.

Current 0.4 fails the definitive visual target: seven page tabs, generic grid, procedural hero and full-page Controls do not recreate actual tvOS layouts. [TVOS-DESIGN-SPEC.md](documentation/TVOS-DESIGN-SPEC.md) defines screen-by-screen reference, geometry, focus/motion, functional and performance gates. Latest Apple update documentation was refreshed and identifies27.2; actual inspected Home and Switcher images have26 provenance and cannot establish27.2 pixel accuracy.

Confirmed defects: stale Home/header clock; frozen reduced-motion screensaver clock. ADB simulated long-press opened Appstore rather than actions; real held-remote favorites acceptance remains unverified. Vendor miniTV icon fallback observed. Source-only issues remain empty favorites defaulting, duplicate focus identity/lost query/filter/scroll state and duplicate synchronous discovery/stale icon queue risk. These source issues are not all physically reproduced.

Missing layers include real content Top Shelf/Continue Watching, rich media interfaces, faithful Controls/Settings/App Switcher, licensed aerial library, robust full settings/recovery, update-stable signing and all Apple protocols listed in the matrix. A shortcut to an installed app does not implement its services.

## 5. Physical AFTMM, API 25 and firmware compatibility

Tested exact target:AFTMM/mantis, Fire OS 6.7.1.1(NS6711/5908), Android7.1.2/API 25, armeabi-v7a. The unchanged0.4 preview installed normally and ran the recorded smoke suite. This supplies actual API 25/32-bit device execution evidence in addition to compilation/DEX packaging evidence. Full functional/fidelity/media/rollback acceptance is incomplete. The original Amazon default HOME remains; only MantisTV was added, none removed/disabled.

The catalog's 41full and 35incremental source records are not 76unique physically verified firmware releases. Their historical C labels are preserved pending exact identifier reconciliation; the new exact-device report must not promote neighboring increments or the whole family. No firmware update/downgrade was performed. All documented AFTMM revisions remain mandatory support scope, with per-build runtime capability/fallback verification outstanding. See PHYSICAL-COMPATIBILITY.md in the device-test directory.

## 6. Hardware performance validation

Single observed launch 840 ms activity/871 ms wait; existing-task return175/203 ms; restart737/758 ms. PSS snapshots:38,480 KiB initially,73,933 KiB after interaction,47,921 KiB after restart/more interaction. Cumulative gfxinfo:2,556 frames,82janky(3.21%);p50 5 ms,p90 6 ms,p95 9 ms,p99 38 ms. App cpuinfo reported3.9% in one7.5second system-report window. These mixed samples are not sustained FPS, peak RAM, a leak diagnosis or a demonstrated optimization.

Logical display1920×1080/59.94Hz versus3840×2160captures does not prove native4K rendering. About 24 minutes mixed QA included a true idle timeout and deliberate restart, with no observed MantisTV fatal/ANR/OOM in bounded captured relevant logs. No long/thermal soak, GPU utilization, repeated startup/latency distribution or media/DRM/audio validation. See [PERFORMANCE-REPORT](device-test/2026-11-10/PERFORMANCE-REPORT.md).

## 7. Apple ecosystem and full OS replacement

No AirPlay receiver, iPhone Remote/Find My/FaceTime/Continuity/Siri/Photos/Music/HomeKit/Home Assistant/Homebridge protocol exists. Generic app launch is not Apple service integration. Existing runtime is four Java classes and two vectors, without INTERNET permission or a protocol service/native stack.

Stock Fire OS remains the OS. MantisTV has no default HOME replacement, boot receiver, system overlay/task/power service, kernel/vendor image or OS account management. No root/flash/system-package/launcher/DNS/OTA changes occurred. System/private-service limitations are explicit unresolved requirements, not permission to substitute visually similar placeholders.

## 8. Latest APK, Git history and artifacts

Installed/tested application:0.4.0-preview, code4, dev.mantistv.home, min/target/compile25, DEX-only,29,235bytes. SHA256:`6cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4`. Certificate:`84c0078a6f74ddf42cc3a3b2705906bf302cbc6478665649af52a8211995dbd1`. Original compiled source:`ab022168a5d8fff173ff0169fd328adb2040be89`. Original quality release:[quality-42747779541](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/quality-42747779541). Latest prior documentation release:[progress-20261110-42792462036](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/progress-20261110-42792462036), publication commit0b1dbeda5a3358744bafb4a952878bc133f6bfd9; report commit09f74db120de78cc1f6a1012ef7125c1d717ece7. New publication identity belongs in PHYSICAL-QA-PUBLICATION.json, generated only on actual publication.

History verified in initial audit: public readiness → untouched upstream463fa6c2 baseline → original native app27e2665 → directAPI 25/focus/catalog bb93385 → module/debug eee7ce0 → lint3c53960 → drawing-allocation fix/lint gate ab02216 → documentation/audit release. Original source ZIP SHA256:8a8516b03b15ffa338acc77e85243d56da5506b41674caab620d45ada0be8cfa. No runtime change or app rebuild in this physical/specification update. Existing APK and signing identity are preserved.

Cloud tests remain one host rules main(nine direct checks plus expected-exception case), package/manifest/ZIP/alignment/signature checks and seven selected lint rules. They are not a full Android test suite. This run adds actual ADB/UI XML/screenshot/persistence assertions and bounded device diagnostics; full instrumentation and regression coverage remain partial.

## 9. Documentation, resources and publication policy

Updated current feature inventory/status, exact test/compatibility/performance/known-issues reports, design specification and backlog. Initial paused resource checkpoints are historical; latest user authorization temporarily permits 15 GiB free-space floor,100 MiB QA output, no SDK downloads/local builds. Final Mac free~28.3 GiB; evidence~18.6 MiB. Existing local tools only. No paid services or new local build/cache installs.

Private raw package inventory, connection endpoint, screenshots and logs stay local unless specifically reviewed for publication. Public reports contain sanitized summaries and evidence hashes, not personal app/account inventory. A documentation prerelease may rehost unchanged verified APK bytes with fresh source/report ZIP and checksums; it must explicitly identify this as evidence/specification work rather than a redesigned app. Do not regenerate signing keys, overwrite existing release evidence or claim unexecuted tests. Certificate continuity remains blocked; never automatically uninstall to bypass mismatch.

## 10. Next ten prioritized engineering tasks

1. **Repair both clock defects and establish safe signing continuity:** add lifecycle-aware minute updates, including reduced motion; cloud-build and verify API 25/signature/package policy. Preserve installed0.4; do not bypass a certificate mismatch by uninstalling.
2. **Complete a versioned tvOS 27.2 reference pack:** Home/Top Shelf, Library, Controls, Switcher, Settings, Search, Screensaver and media focused/unfocused/dialog states; measure geometry/type/motion and record source/version/rights. Existing26 images are inherited references only.
3. **Implement the measured Home/Top Shelf shell:** replace seven page tabs with reference composition and spatial focus navigation; preserve catalog/store/launch logic. Genuine provider content only; keep missing Continue Watching explicit.
4. **Build the focus/transition system and regressions:** distinct view identity, row/scroll/query/filter restoration, actual hold/repeat/release behavior, measured scaling/parallax/curves and reduced-motion support. Retest favorites long-press with a physical remote.
5. **Reconstruct functional Controls and Settings:** measured overlays/category/detail layouts; real runtime service/permission/intent probes, truthful state and unavailable-capability feedback; retain Amazon Home escape.
6. **Implement Library/Search/Switcher faithfully within platform capability:** real app ordering/filter persistence/search destinations; carousel design without claiming inaccessible OS task previews/kill controls; mark unresolved system integration explicitly.
7. **Establish repeatable AFTMM performance and stability gates:** cold/warm distributions, input latency, frame pacing/GC/PSS trends, idle CPU, legal media return and thermal/long soak. Compare changes against current measured baseline.
8. **Implement real media/shelf/screensaver adapters:** lawfully reusable artwork/media, bounded decoding/caching, working play/resume/seek/error states and categories. Investigate API 25/ARMv7 reusable candidates and licenses before integration.
9. **Validate every documented firmware revision incrementally:** reconcile catalog IDs/history, test capability fallbacks and service/permission/launcher differences; exact-build evidence only, no family-wide promotion from one device.
10. **Progress genuine Apple integration and release acceptance:** evaluate legally reusable AirPlay/companion/media/smart-home implementations, security and licenses; implement and test protocols independently from visuals. Maintain explicit OS-replacement/private-service limits, export/recovery and non-destructive upgrade acceptance.

The definitive product target remains maximum feasible1:1 latest tvOS. A narrow completed smoke row is not a claim of final design, Apple ecosystem, all-firmware or full OS parity.
