# Definitive tvOS recreation design specification

Updated2026-11-10 during the existing physical QA run. This specification supersedes any interpretation that a generic Android launcher with glass styling is the final product. Preserve the installed0.4 APK, existing commits/artifacts and completed tests. **Target: maximum feasible1:1 recreation of the latest publicly documented Apple tvOS experience, with working behavior behind every visible action.** Current 0.4 fails final visual and behavioral acceptance.

## Reference baseline and provenance

Latest version verified in Apple's [software update summary](https://support.apple.com/en-ie/106336):tvOS 27.2. Retrieved2026-11-10; version-specific features remain in the119-row inventory. Versioned reference evidence must name OS/version, device, region, display dimensions, focus state and capture date. Never describe an older screenshot as a verified27.2 screen.

Actual Apple images visually inspected this run:

- [Home/Top Shelf official Newsroom image](https://www.apple.com/newsroom/images/2025/06/apple-tv-brings-a-beautiful-redesign-and-enhanced-home-entertainment-experience/article/Apple-WWDC25-tvOS-26-hero-250609_big.jpg.large.jpg), from [Apple's June9,2025 tvOS 26 announcement](https://www.apple.com/newsroom/2025/06/apple-tv-brings-a-beautiful-redesign-and-enhanced-home-entertainment-experience/). The TV image shows content artwork dominating the upper surface, a lower app row, a visible continuation below, and small status/profile elements. This is an inherited26 reference, not proof of unchanged27.2 geometry.
- [Official App Switcher image](https://help.apple.com/assets/699FDE58256A5007AE03F37E/699FDE5CCBA706658D03FBAF/en_US/566edba5db029d047bb1667bed0cf13a.png), linked by [Apple's Switch between apps guide](https://support.apple.com/guide/tv/switch-between-apps-atvbbcec4f4e/tvos). Visually inspected: overlapping horizontal windows, centered preview and neighboring cards with app identity. The retrieved guide identifies tvOS 26; do not assign27.2 pixel accuracy to this asset.

Other primary references: [Apple TV guide](https://support.apple.com/guide/tv/welcome/tvos), [layout HIG](https://developer.apple.com/design/human-interface-guidelines/layout), [layered images/parallax](https://developer.apple.com/design/human-interface-guidelines/images), [Liquid Glass platform guidance](https://developer.apple.com/documentation/TechnologyOverviews/adopting-liquid-glass), and [remote guide](https://cdsassets.apple.com/live/6GJYWVAV/user/apple-tv-remote.pdf). Apple Newsroom also links official Controls, player and TV-app poster videos; these were located, not downloaded or frame-measured. Several detailed guide fetches returned429. Full27.2 screen-state/animation reference coverage therefore remains open. No inferred pixel coordinates, animation curves or font equivalence are presented as measured facts.

Reference media is for comparison; it has not been bundled as redistributable application artwork. Record license/source for each shipping font, icon, poster, video and candidate component. Use legally reusable assets and metrically close typography where exact fonts/assets are unavailable; record each fidelity deviation explicitly.

## Screen-by-screen acceptance and present gap

| Surface | Required acceptance | Actual0.4 evidence/gap | Next implementation slice |
|---|---|---|---|
| Home | Match reference content hierarchy, Top Shelf/app-row proportions, margins, type scale, icon shapes and directional navigation across focused/unfocused/scrolled states | Device screenshots show seven page-navigation buttons, procedural hero and generic grid. Visual acceptance fails | Replace page-tab scaffold with a reference-measured Home scene; preserve app catalog/launch/store logic behind adapters |
| Top Shelf | Focus-dependent genuine content/artwork, valid launch/resume behavior and consistent shelf-to-app navigation | Recent app list only; no content feed or viewing progress | Define provider adapter and permitted content metadata/deep links; no invented Continue Watching cards |
| App Library | Match the observed Apple app organization/ordering/focus states; every shown installed app opens its real target | Discovery/launch tested; generic six-column catalog, incomplete semantic filters, icon fallback | Capture current version's app arrangement states; bounded original/installed icons; model reordering/folders only when real persistence exists |
| Control Center | Reference overlay placement, tabs/tiles, background treatment, selection and subpanel transitions; each setting invokes a supported real capability | Full-page button list; network/display info tested, settings/volume effects unverified; no global access | Foreground overlay with capability-backed tiles and return-to-context. Global access remains a separate unresolved platform requirement |
| App Switcher | Reference horizontal preview composition, card focus, navigation, switch/close semantics | Recent icon list; no real task previews or system task control | First implement recent-launch carousel truthfully labelled. Do not claim task previews/force-close until authorized public platform capability exists |
| Settings | Match reference category/row hierarchy, detail panels, typography, focus restoration and truthful current values | Flat button list with limited local preferences and external intents | Typed settings model with real capability probes, local persistence and safe public system links; disclose unsupported platform controls |
| Search | Reference search layout/keyboard/result hierarchy; actual app/content results and working destinations | App text search passes; no unified content, voice or Siri | Keep working matcher; separate installed apps from provider results; implement remote text-entry state before rich search styling |
| Screensaver | Reference full-frame media, category selection/transition/idle/dismiss behavior; correct clock with reduce motion | Procedural landscape and5min idle pass; stale reduced clock; no aerial library | Fix redraw/lifecycle clock first, then bounded legally reusable media playlist with real categories and storage limits |
| Media | Reference browsing/details/player/subtitle/audio/transport states, valid play/resume/seek/error paths | No MantisTV player/content integration | Public provider/deep-link integration first; own legal local/sample playback only with real decoder/transport state. Do not imitate Apple services with inert posters |

## Interaction and visual verification contract

For each screen capture a versioned reference and exact AFTMM screenshot at matching aspect ratio, theme, text size and focus state. Normalize content viewport before comparison: the tested app reports1920×1080 while screencap emits3840×2160. Measure normalized rectangles for margins, card sizes, gutters, baselines, radii, title/body hierarchy and focus extents; keep reference/candidate overlays and a deviation list. No screen passes on a resemblance judgment or a single static screenshot alone.

Record directional focus graphs and movement sequences, return targets and state restoration. Test repeated input, key hold/release, edge scrolling, dialogs, app launch/return and Back/Menu/Home boundaries. Use scale, depth/parallax, highlights and transition curves measured from reference motion. Current 140 ms/1.045scale source values are implementation facts, not Apple-matched timings. Fire remote lacks the Siri Remote touch surface; implement the closest usable D-pad behavior, document the input limitation and never intercept Fire OS HOME through unapproved system changes.

Visible action contract: real implementation, observable effect, success/failure feedback, permission/capability fallback and test evidence. Unavailable capabilities belong in a clearly explained status/detail view; do not display a decorative operational control or fake network/media/profile state. Every inventory row keeps exactly one permitted project status. Functional smoke passes do not promote visual fidelity or Apple protocol status.

## AFTMM performance and firmware acceptance

Retain API 25 and ARMv7 compatibility. Probe services, intent resolution, permissions, audio/display/network behavior and failure returns at runtime; firmware strings alone cannot select safe behavior. Maintain per-build test records and C/unknown rows until supported by exact evidence. All documented revisions remain a product requirement, not an achieved claim.

Current measured baseline is in PERFORMANCE-REPORT:840 ms first launch,37.58 MiB initial PSS,72.20 MiB post-interaction PSS and3.21% cumulative gfxinfo jank in a mixed session. Establish repeatable scenarios before setting budgets. Proposed targets: responsive focus feedback within 100 ms input-to-visible atp95,60Hz presentation where supported withp95 frame time within16.7 ms, and no sustained PSS growth across repeated app-return cycles. These are engineering targets, not measured passes or promises of native4K60. Separate idle, animation, media-return, thermal and long-session samples. Use bounded bitmap/media work, cancel stale work, retain view state and reduce blur cost without changing reference composition silently. No Mac SDK/emulator/cache downloads or local build is allowed under the temporary resource exception.

## Delivery gates

Keep0.4 installed as the baseline. Publish only explicit development prereleases with exact source/APK/certificate/checksum identity and honest known defects. Persistent signing/certificate continuity must be solved before an in-place upgrade; never uninstall user data to bypass mismatch. Use free standard public GitHub Actions runners, no caches or paid services. This run changes evidence/specification/backlog only; no unverified redesign replaces the tested binary. Next source work should repair the two clocks and add reference-measured Home without discarding working app/profile/search logic. Apple ecosystem and full OS replacement gaps remain visible in PROJECT-PROGRESS.

## Subsequent physical evidence

Both clock fixes passed real-minute AFTMM regressions in separate0.4.1 debug, source b6f7a673a01619aca100adbcc4abcf97cbf3ab80. Original0.4 is preserved and retains its known defects. See CLOCK-PHYSICAL-QA.md. These narrow passes do not accept the current UI as a faithful tvOS recreation or establish signing continuity.

## Confirmed MacBook/iPhone acceptance

The user explicitly requires an optional Apple ecosystem connection alongside the faithful latest-tvOS design. Implement genuine AirPlay-compatible sharing where feasible and distinguish a paired custom companion remote from native Apple Remote/HomeKit/Siri/account integration. Initial API25/ARMv7 receiver source candidate and separate physical gates are recorded in APPLE-ECOSYSTEM-PLAN.md; no ecosystem feature has been promoted on research alone.
