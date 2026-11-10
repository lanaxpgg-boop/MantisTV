# Tigris2.0.27 versus MantisTV0.4.0

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
