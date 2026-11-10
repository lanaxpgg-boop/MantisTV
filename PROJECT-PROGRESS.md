# MantisTV project progress audit

Audit date: November 10, 2026 (Europe/Sarajevo). Evidence capture: 2026-11-10T09:25:40.547570+00:00.

## 1. Overall development milestone

**Native foreground TV application preview; Priority 0 build checkpoint reached, hardware acceptance outstanding.** MantisTV is not a complete tvOS recreation, an Apple TV substitute with Apple services, a system launcher replacement, or a replacement operating system. Priority 1 has basic local UI implementations; Priority 2 has no implemented Apple protocols and only generic installed-app launching; Priority 3 is preliminary documentary research.

The original mission requires a coherent entertainment operating environment on AFTMM, complete firmware-family support, hardware validation and Apple ecosystem capabilities. The source currently implements a small Android Activity application on stock Fire OS. A signed APK and successful compilation do not close that gap.

No overall percentage is assigned: build tooling, a renderer, a physical benchmark and an AirPlay receiver are not equally sized units of work. Counts below describe audit rows only, not engineering effort or feature completeness.

## 2. Scope, evidence and status rules

The matrix covers every original mission feature group and bullet, its build/QA/compatibility obligations, the existing TVOS-FEATURE-MATRIX, Apple's documented tvOS 27/27.2 update-summary features, and inherited design/ecosystem features relevant to the mission. It is not an inventory of undocumented Apple capabilities. Shared requirements (for example favorites/recent apps across screens, network/display information in Settings and Controls) have one authoritative feature row rather than duplicate completion credit.

Apple reference is tvOS 27.2, with a previously verified official release date of October 26, 2026: [update summary](https://support.apple.com/en-us/106336), [security release](https://support.apple.com/en-us/149524). These pages were retrieved earlier in this same November 10 execution. A refresh during this audit returned HTTP 429; no retry/bypass was used. Newer or undocumented Apple features are not asserted from that failed refresh.

Each feature below has exactly one status:

- **Completed and verified.** Narrow acceptance behavior is directly covered by test/artifact evidence and source inspection. For UI, device and ecosystem behavior, compilation alone never qualifies. No user-facing TV feature is classified this way in this audit.
- **Implemented but not physically tested.** A concrete runtime implementation exists; Android/UI/physical behavior has not been established.
- **Partially implemented.** A substantive subset exists, with a specific functional or evidence gap. Architecture prose alone is not a runtime implementation.
- **Not implemented.** No relevant runtime implementation/test capability was found. A shortcut to another application is not an implementation of its services.
- **Blocked.** A named prerequisite or current mission constraint prevents the milestone: supervised hardware, secure persistent signing setup, or forbidden protected-system modification. This is not evidence the feature works.

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
- [Verified 0.4 release](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/quality-42747779541), [successful quality run](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42747779541), its actual unit, lint, signature, manifest and checksum assets. No Android/device test run or screenshot was found.

## 3. Feature inventory


| Status | Feature rows |
|---|---:|
| Completed and verified. | 11 |
| Implemented but not physically tested. | 21 |
| Partially implemented. | 35 |
| Not implemented. | 47 |
| Blocked. | 5 |

Total: **119 distinct audited feature/engineering rows**. These counts are not a progress percentage.

### Home and navigation

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| H01 | Foreground Home screen | Implemented but not physically tested. | M.home, M.show: Native Activity and original landscape/caption are present; no rendered or remote-driven device evidence. |
| H02 | Cinematic hero artwork and poster presentation | Partially implemented. | M.home; app/res: Procedural mountains and static text only; no cinematic title posters, licensed hero feed or video artwork. |
| H03 | Top Shelf | Partially implemented. | M.home:240-245: At most six local recent app launches; no provider content shelf, previews or recommendations. |
| H04 | Application dock | Partially implemented. | M.home, M.section: A six-column app grid inside the scrolling page, not a persistent tvOS-style dock. |
| H05 | Favorites throughout Home and Library | Partially implemented. | M.home:236-239; M.appActions: Add/remove and per-profile storage exist, but an empty saved list repopulates Home with default apps. |
| H06 | Recent applications opened through MantisTV | Implemented but not physically tested. | M.launch; R.recent: Local bounded launch history; does not observe launches from other applications or Fire OS. |
| H07 | Continue Watching | Not implemented. | M.home:243-245; source tree: Only an explicit unavailable message; no provider/session/progress implementation. |
| H08 | Dynamic Home backgrounds | Partially implemented. | M.show; L.onDraw: Two selected palettes and static Home landscape; periodic landscape motion is fullscreen-screensaver-only. |
| H09 | Focus transitions and reduced motion | Implemented but not physically tested. | M.focus:118-130: 140 ms scale/elevation and reduced-motion branch; smoothness and remote reachability untested. |
| H10 | tvOS-like focus engine and restoration | Partially implemented. | M.show:144-222; M.section: Android focus plus string tags; duplicate app tags and rebuilt pages limit restoration fidelity. |
| H11 | Parallax animations | Partially implemented. | L.onDraw:54-61; M.focus: Small horizontal landscape layer drift in screensaver; no app-card tilt/depth or Home parallax engine. |
| H12 | Liquid Glass-inspired rendering | Partially implemented. | M.glass:103-108: Rounded translucent backgrounds; no blur, optical refraction or material sampling. |
| H13 | D-pad and OK navigation | Implemented but not physically tested. | M.focus; M.section; M.dispatchKeyEvent: Focusable native controls/click handlers exist; no Android navigation or physical remote test. |
| H14 | Back and Menu behavior | Implemented but not physically tested. | M.dispatchKeyEvent:570-580; M.onBackPressed:583-595: Menu switches foreground Controls; Back returns Home then requires two presses to finish. |

### App Library and management

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| A01 | Installed application discovery | Implemented but not physically tested. | A.discover:17-51: Queries exported/enabled Leanback and ordinary launcher activities and deduplicates by package. |
| A02 | Application categories | Partially implemented. | M.library:355-369: All/Favorites/Recently opened filters; no semantic media/game categories or editable taxonomy. |
| A03 | Application icons | Implemented but not physically tested. | M.loadIcon:299-322: One worker, normalized 96 px bitmaps and LruCache; actual icon coverage/appearance untested. |
| A04 | Application metadata | Partially implemented. | A.AppItem; M.search: Package, label and launch intent only; no descriptions, ratings, content metadata or rich catalog. |
| A05 | Application launching | Implemented but not physically tested. | A.discover; M.launch:323-333: Explicit public launch intents with exception handling; no launch/return hardware evidence. |
| A06 | Application management | Partially implemented. | M.appActions; M.settings: Favorites, refresh and guarded external app-info link; no install/update/uninstall manager or package lifecycle integration. |
| A07 | Application list refresh | Implemented but not physically tested. | M.settings:482-486: Manual rediscovery and icon-cache eviction; no package-change receiver or background subscription. |

### Control Center

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| C01 | Control Center interface | Partially implemented. | M.controls:402-426: A foreground app page; no global overlay over streaming applications. |
| C02 | Audio controls | Partially implemented. | M.volume:427-437: Music stream +/- with fixed-volume/security fallback; HDMI/receiver behavior and route control unverified. |
| C03 | Network status and information | Implemented but not physically tested. | M.controls:404-408: Connected/disconnected and type only; no detailed network diagnostic suite. |
| C04 | Display information | Implemented but not physically tested. | M.controls:409-413: Real display metrics and refresh rate getters; not a tested HDMI/HDCP/capability audit. |
| C05 | Compatible network/audio/display/system shortcuts | Partially implemented. | M.controls; M.safe:438-448: ResolveActivity and exceptions provide fallback; actual exported targets on each firmware are unknown. |
| C06 | Sleep control | Not implemented. | M.controls:423-425: Text delegates sleep to the physical Fire TV remote; no app sleep action. |
| C07 | Manual screensaver shortcut | Implemented but not physically tested. | M.controls:422; M.screensaver: Opens the app's own foreground landscape view. |
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
| SC01 | Original landscape screensaver | Implemented but not physically tested. | L; M.screensaver:558-568: Procedural vector landscape and clock, not an aerial image/video library. |
| SC02 | High-quality licensed aerial images/video | Not implemented. | app/res; source tree: Only two vector icon/banner resources and procedural artwork; no aerial media assets or player. |
| SC03 | Automatic idle behavior | Implemented but not physically tested. | M.armIdle:76-80; lifecycle methods: Five-minute foreground timer; pauses when the app loses foreground. |
| SC04 | Smooth transitions and scene sequencing | Partially implemented. | L.onDraw: Layer translation at nominal 100 ms invalidations; no scene crossfades, media sequencing or hardware smoothness evidence. |
| SC05 | System screensaver replacement | Not implemented. | Manifest: No DreamService or system idle integration; does not replace Fire OS screensaver/sleep. |

### Search

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| Q01 | Installed application search UI | Implemented but not physically tested. | M.search:370-401: Text input filters app label/package and launches matching apps; keyboard/remote usability untested. |
| Q02 | Content discovery | Not implemented. | M.search; source tree: No film/show index, metadata provider or provider search integration. |
| Q03 | Local metadata search beyond app label/package | Partially implemented. | R.matches; A.AppItem: Only installed application names and package strings; no local media index or richer fields. |
| Q04 | Compatible legal remote content sources | Not implemented. | Manifest; source tree: No source adapters, HTTP client or network permission for remote discovery. |
| Q05 | Voice or Siri search | Not implemented. | Manifest; source tree: No microphone, speech recognition or Siri integration. |

### Profiles

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| P01 | Multiple local profiles | Implemented but not physically tested. | M.profiles:499-514: Four fixed local profiles with rename/select; no Android persistence or switching tests. |
| P02 | Per-profile preferences | Implemented but not physically tested. | M.key/list/save/settings: Appearance/reduced motion and lists are keyed by profile; isolation is source-reviewed, not integration-tested. |
| P03 | Local watchlist | Implemented but not physically tested. | M.watchlist:515-557: Manual title add/remove, up to 100 entries; no streaming/provider synchronization. |
| P04 | Personalized Home experience | Partially implemented. | M.home; M.profiles: Profile name, favorites and recent launches only; no content recommendations, viewing progress or account personalization. |
| P05 | Streaming/Apple account profile switching | Not implemented. | M.profiles; ecosystem doc: Local profile selection does not switch accounts inside streaming apps. |

### Apple ecosystem

| ID | Planned feature | Status | Source/test evidence and actual gap |
|---|---|---|---|
| E01 | Official Apple TV application integration | Not implemented. | A.discover; M.launch: No Apple-specific adapter or verified compatibility/playback. Generic launching is covered by A05 and earns no Apple-integration completion credit. |
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
| F07 | All-firmware AFTMM compatibility validation | Partially implemented. | Device audit; firmware data: Device-family API25 compilation/catalog evidence only; no per-build B/source verification or A/physical acceptance. All catalog records remain C. |
| F08 | Physical AFTMM installation and functional acceptance | Blocked. | Build report: Installed or physically tested NO: No authorized supervised device session; no device was connected or modified. |
| F09 | Hardware startup, RAM, GPU, 4K and storage benchmarks | Blocked. | Performance budget; no measurements: AFTMM test access is missing; source constraints and APK size do not establish performance. |
| F10 | Long-term stability, thermal and soak validation | Blocked. | QA report; no soak artifacts: Requires supervised hardware; no session duration, crash/thermal trace or soak results. |
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
| F25 | Android UI/instrumentation/persistence tests | Not implemented. | tests tree: Only StoreRulesTest.java and lint tooling POM; no UI, lifecycle, profile-isolation or persistence instrumentation suite. |
| F26 | Installation, rollback and recovery validation | Partially implemented. | Installation/rollback guides: Written procedures exist; installation, return-to-Amazon and uninstall/recovery paths not rehearsed on hardware. |
| F27 | Update-stable production signing/release | Blocked. | B:20-23; release notes: No persistent protected keystore is provisioned; each clean build generates an ephemeral key. Needs secure signing setup. |
| F28 | Free cloud workflow route | Completed and verified. | Public CI workflow/run records: Standard ubuntu-24.04 public jobs and GitHub Releases; no Actions cache/upload-artifact or paid service configured in the inspected workflows. |
| F29 | Resource monitoring and low-impact builds | Partially implemented. | Resource script; CI; JAVA_TOOL_OPTIONS: Disk reserve gates and JVM cap exist; not continuous memory-pressure/usage/device-resource monitoring. |
| F30 | Measured performance optimization | Partially implemented. | M.icon cache; L; prior/final lint reports: Bounded icon cache and draw preallocation exist; source-level warning reduction is verified, actual hardware improvement is unmeasured. |
| F31 | Public-API, no-DRM-bypass app design | Completed and verified. | Complete original source and manifest: No private API calls, receiver/DRM implementation, root/flash/device-control code in MantisTV runtime; not Apple certification. |
| F32 | Complete accurate project documentation | Partially implemented. | Audited documentation/history: Substantial docs exist, but prior version/lint/backlog claims drifted and firmware/hardware sections remain incomplete. |

## 4. Implemented and missing functionality

The concrete source implements a foreground native Home, app discovery and explicit launches, icon loading/cache, local recent launches, app-name/package search, profile-local lists/preferences, manual title watchlists, basic guarded controls, and an original foreground landscape screensaver. Those screens have not been exercised by Android instrumentation or on a TV. The pure matcher, recent-list rules and profile-ID normalization have host tests; this does not test the Android screen or persistence that uses them.

The major missing product layers are real content Top Shelf/Continue Watching, rich media/catalog interfaces, semantic app categories and full management, comprehensive settings and recovery, system-wide controls/task switching/sleep/screensaver, aerial media and scene transitions, production signing/update safety, and every Apple/network ecosystem protocol listed in the matrix. Existing Fire OS or third-party application features are not credited to MantisTV merely because MantisTV can start another app.

Source-review findings, not physically reproduced defects:

1. **Empty favorites revert to defaults:** M.home checks only whether the resolved favorite list is empty (lines 236-239), so deliberately clearing favorites still displays the first six apps on Home.
2. **Reduced-motion screensaver clock has no periodic redraw:** L.onDraw updates the displayed clock only on drawing, but schedules the next draw only when reduced motion is off (lines 63-77). Without another invalidation, a static scene can retain an old clock. The normal Home header clock is also created during show() rather than maintained by a clock timer.
3. **Focus and screen state restoration are incomplete:** app tags are only package-based and can occur both in Dock and recent Top Shelf; findViewWithTag selects the first match. show() reconstructs the page, library returns to All apps, and search input is re-created empty. There is no stored row/scroll/filter/query state.
4. **Startup/performance risks remain unmeasured:** discovery runs synchronously in both onCreate and onResume; full pages are reconstructed on return. The single-thread executor has no explicit queue bound or stale-request cancellation. The 2 MiB cache cap does not cap all view-retained bitmaps or total PSS.
5. **Firmware fallback coverage is limited:** guarded settings intents and fixed-volume checks do not establish every expected service, permission or settings action on each Fire OS revision. These paths need actual capability/service tests.

This audit changes documentation and release provenance only. It does not silently fix these application behaviors or raise the application version.

## 5. Physical AFTMM, API 25 and firmware compatibility

**Physical AFTMM compatibility remains unknown.** No installed package record, device log, screenshot, D-pad trace, playback test or hardware benchmark exists. No device was connected or modified during development or this audit. The existing report explicitly says installed/physically tested: NO. Supervised testing requires a separate authorized session; this audit does not authorize installation or launcher changes.

The official device-family scope remains AFTMM / Fire OS 6 / Android API25 / 32-bit ARM. No official Fire OS 7/8 support is claimed. Compilation against the real platform-25 android.jar and absence of native libraries establish useful **source/packaging evidence**, not per-firmware compatibility, actual ARM execution or preserved HDMI/DRM/audio behavior. The source manifest declares min/target SDK25, one ACCESS_NETWORK_STATE permission, launcher categories, and no HOME replacement, service or boot receiver. A fresh artifact check in the audit-publication workflow verifies the actual packaged manifest and signatures without installing it.

The JSON catalog contains **41 full and 35 incremental source records** captured at 2026-11-10T03:11:25.240077+00:00. All 76 evidence labels are C. Incremental rows repeat targets and describe from/target edges; **76 records does not mean 76 unique tested firmwares**. Dates are catalog source dates, not confirmed Amazon release dates. Package differences, permission grants, services and launcher behavior are stored as unknown/unverified. Launch-era omissions and complete official-history reconciliation remain open. There are zero A/physically tested rows and zero per-build B/verified-source compatibility rows.

## 6. Hardware performance validation

No startup time, idle PSS, peak RAM, GPU/frame timing, GC, 4K render quality, thermal state, storage growth, media-return latency or soak result was collected. APK size is not a memory or performance benchmark.

Verified source constraints include one icon worker, normalized 96px icons, a 2 MiB LruCache size budget, no network background service, a reduced-motion branch and scene geometry precomputation. The 0.3 lint report had six DrawAllocation warnings; 0.4's same seven-rule gate reports zero findings. This verifies removal of those static findings only; it does not establish a measured hardware speedup or long-session stability. The screensaver nominally requests redraw every 100 ms with motion enabled, which is not proof of any achieved frame rate.

## 7. Apple ecosystem and full OS replacement

No AirPlay receiver, iPhone Remote/Find My/FaceTime/Continuity/Siri/Photos/Music/HomeKit/Home Assistant/Homebridge protocol module exists. The entire runtime comprises four Java classes and two vector drawables, with no native protocol libraries, networking service or INTERNET permission. The Apple TV app can only be treated as another generic installed launch target; availability, account login, region and playback are untested. The only Apple-named test fixture exercises string search and is not integration evidence.

Stock Fire OS remains the operating system and default system experience. MantisTV has no HOME/LEANBACK_HOME intent, system-UI replacement, boot receiver, accessibility service, global overlay, task manager, privileged power service, kernel/vendor stack or firmware image. App profiles are not OS accounts or parental restrictions. The current permitted foreground-app strategy cannot be described as full OS replacement. Rooting/flashing/downgrading/system-package disabling remain outside authorization; this audit makes no change to those constraints.

## 8. Latest APK, history and release artifacts

At audit start, the newest published application prerelease was [quality-42747779541](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/quality-42747779541), published November 10, 2026 at 04:12:01 Europe/Sarajevo. All current releases are prereleases; no production/stable signed release exists. GitHub's stable-release notion of latest should not be confused with the newest prerelease by publication date.

| Identity | Verified value |
|---|---|
| App version | 0.4.0-preview, versionCode 4 |
| Preview package | dev.mantistv.home; not debuggable |
| Debug package | dev.mantistv.home.debug; separately debuggable |
| Min / target / compile SDK | 25 / 25 / 25 |
| Native ABI payload | None; DEX-only. Actual armeabi-v7a/AFTMM execution untested |
| Audited HEAD before this report | a1a51fe520363c2512eb14a79e4c17998c8ad0ad |
| Actually compiled app source | ab022168a5d8fff173ff0169fd328adb2040be89 |
| Original source/archive release target | 54619013a18b723559520e6d32241dc56909d7c7; post-build documentation change |
| Successful quality run | 42747779541; workflow head 2bcce396896da93d24113eceb94946dbe7af2462 |
| Preview APK | 29,235 bytes; SHA256 6cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4 |
| Debug APK | 29,235 bytes; SHA256 447a8a7e72a3fa1965c99f41b3d0e3f8f1e591831266259198ede41b1a360c1e |
| Original source ZIP | 98,789 bytes; SHA256 8a8516b03b15ffa338acc77e85243d56da5506b41674caab620d45ada0be8cfa |

Both APKs and the original source ZIP were streamed into memory for hash/ZIP checks without saving them or installing SDKs on the Mac. ZIP CRCs passed; APKs contain classes.dex and no lib/ payload, with DEX header 037 and 41 class definitions. Selected source ZIP files (manifest, MainActivity, LandscapeView and StoreRulesTest) matched the audited source; the GitHub compare additionally showed no runtime source edits since the compiled commit. This is artifact integrity/provenance evidence, not proof of behavior. Fresh cryptographic/packaged-manifest verification and all uploaded-asset hash results are recorded by the audit-publication workflow in AUDIT-VERIFICATION.json.

The original quality release has **18 uploaded assets**, plus GitHub's two automatically generated source archives shown in the UI. It includes APKs/signature sidecars, source ZIP, SHA256SUMS, license, install/rollback guides, unit output, lint text/XML, javac diagnostics, signature and manifest reports. The signature report records SDK25-floor verification with APK v2/v3 schemes and an RSA-2048 ephemeral development certificate. Do not claim production certification, reproducible identical APK bytes or update-stable signing: new clean builds generate new keys, and future uninstall/reinstall may erase local lists.

History was inspected, not inferred from the current README:

| Stage | Source or workflow commit | Evidence |
|---|---|---|
| Initial repository/readiness | fb1a1f8 / c7199ba | Public repository and resource run 42744226302 |
| Untouched upstream baseline | upstream 463fa6c2a9b7322c4407f1d00db935ef09d20f4f | Run 42744518215; separate baseline APK/source/license assets |
| Original native app | 27e2665f9105d67119bc3e3423e6ef62feec275e | 0.1 release; compile SDK34, min/target25 |
| Direct API25/focus/library/catalog | bb933851938e4cc33369c93985427593e6cae363 | 0.2 quality run 42746145356 |
| Module split/debug packaging | eee7ce019dc20f7ed8c2d0bf1e5970165d90ba6f | 0.3 run 42746849977 |
| Initial lint audit | 3c5396040c3fe156d22e84915f75177ee18684ee | Run 42747404342; 0 errors, 6 drawing warnings |
| Scene allocation fix/lint gate | ab022168a5d8fff173ff0169fd328adb2040be89 | 0.4 run 42747779541; zero findings for seven selected rules |
| Routine workflow update | a1a51fe520363c2512eb14a79e4c17998c8ad0ad | Manual workflow now includes lint; no separate run of this final maintenance revision existed at audit start |

Seven public CI runs were found; all have success conclusions. They are resource, build, catalog and selected-static-analysis jobs, **not seven hardware/UI test runs**. The source test suite is one host-JVM main, nine direct check calls plus one expected-invalid-limit exception scenario. It does not test Android persistence, profile isolation, focus, app launching, idle timing, settings intents, playback or hardware. Lint's artifact reports itself as lint 8.5.2, supplied by Maven coordinate com.android.tools.lint:lint:31.5.2; these are tool version naming differences, not a second independent analysis.

## 9. Documentation discrepancies and publication policy

Prior documentation was not uniformly current. BUILD-REPORT.md still named 0.3; KNOWN-ISSUES had an old statement that Android lint was outstanding; IMPLEMENTATION-BACKLOG still listed direct API25 compilation and catalog module separation as undone; the old matrix merged whole feature families without individual statuses. Those were treated as stale claims, not source evidence. This audit corrects the summary documents and expands the matrix to 119 unique rows. The captured pre-audit source/evidence identities remain immutable above.

The next release is a **documentation/audit prerelease of the same 0.4.0 application**, not 0.5 or a new runtime build. It republishes the exact existing APK bytes after hash and SDK25-floor signature/manifest checks, includes this report, STATUS, machine-readable feature inventory and verification metadata, and creates a fresh source ZIP containing the audit documents. Its new ZIP hash necessarily differs from the old 0.4 source ZIP. It does not regenerate keys, silently modify old release assets, claim new tests or require an update on a device.

AUDIT-PUBLICATION.json identifies the audited HEAD, original compiled source, original artifact release and the new report/source archive commit. AUDIT-VERIFICATION.json records successful asset checks and fresh verification; a failed gate prevents publication. The audit-release workflow alone is new CI code; MantisTV app/test/build/lint runtime inputs must remain unchanged from the audited HEAD. Existing production-signing, physical testing and integration gaps remain open.

No Fire Stick operation, SDK/toolchain/cache installation on the Mac, paid runner, metered Actions artifact upload, account access expansion or unrelated-file deletion is part of this audit. The Mac remains below its storage reserve; lightweight reports are saved locally while build/verification tooling and persisted APK/source ZIP outputs remain in free public cloud runners.

## 10. Next ten prioritized engineering tasks

1. **Authorize and run supervised AFTMM smoke acceptance:** record exact hardware/firmware; install the preview manually; test launch, D-pad/OK/Menu/Back, Home-button escape, app return and removal. Preserve Amazon defaults and user data. This stays blocked until a separately authorized device session.
2. **Add Android functional/persistence/lifecycle tests:** exercise profile isolation, JSON persistence, favorites/watchlist, focus/scroll/query restoration, idle/pause/resume and unavailable settings targets. Use separately authorized hardware automation; no local emulator or unattended device changes.
3. **Fix the source-level UX/lifecycle defects with regression evidence:** intentionally empty favorites, reduced-motion clock scheduling, unique focus keys and preserved library/search state; avoid duplicate startup discovery and unbounded stale icon work. Keep this audit release documentation-only.
4. **Provision persistent private signing and safe upgrade/data recovery:** use a secure authorized secret store, document certificate continuity, add user-controlled profile export/import, and test upgrade/rollback without automatic destructive uninstall. Do not publish a private keystore or call an ephemeral-key build production-ready.
5. **Establish an AFTMM performance/soak baseline:** startup, PSS/peak RAM, frame consistency, 4K behavior, GPU/GC, app-return latency, temperature/storage and long-session reliability. Set acceptance budgets from observations, not fabricated figures.
6. **Complete firmware-family evidence:** reconcile launch-era/official history and incremental identifiers; inspect package/permission/service/launcher differences; add real capability probes and per-build source tests. Upgrade C to B/A only with linked evidence.
7. **Finish the core TV experience:** semantic categories/rich metadata, proper persistent dock and shelf behaviors, focused remote interaction, accessible typography/contrast, full settings/recovery UX and licensed/original media with bounded decoding/caching. Validate each increment on hardware.
8. **Design lawful content and Continue Watching adapters:** first establish which provider/public APIs actually work on API25/Fire OS6; add explicit permissions/data contracts, mockable adapters and user-consented data flows. No private Apple APIs, scraping credentials or DRM bypass.
9. **Run an API25/ARMv7 ecosystem feasibility spike:** audit receiver candidates and all native/component licenses and protected-protocol paths; assess a secure opt-in custom iPhone/Mac/Shortcuts bridge separately from Apple's Remote certification; then optional Photos/Music/Home Assistant/Homebridge adapters. Proposals alone are not implemented features.
10. **Define the permitted system-integration boundary and release acceptance:** finish stock-versus-ROM/vendor support research without rooting/flashing or disabling packages; document unattainable/private-service features and a realistic milestone contract. Consolidate CI/docs so old bootstrap workflows and stale static summaries cannot imply broader OS completion.

The next product milestone is a reproducible, safely updatable, physically accepted AFTMM native-app release with measured performance. Full tvOS/Apple ecosystem parity and full OS replacement remain separate, much larger goals; this audit does not mark them complete.
