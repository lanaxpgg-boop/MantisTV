# Tigris 2.0.27 source audit

Date: 2026-11-10. Scope: source audit and isolated port experiment, not device certification.

## Decision

**Option A, conditional:** use a successfully validated API25 Tigris port as the proposed primary UI/runtime foundation. Keep MantisTV as the tested fallback until the gates in TIGRIS-PORTING-ROADMAP.md pass. Do not switch primary development today merely because Tigris has more screens. Retain Mantis's profile/search/data rules selectively rather than maintaining two complete renderers or starting a third architecture.

Tigris already contains substantially more of the requested composition, glass, Top Shelf, app-window presentation, settings and wallpaper implementation. Recreating those from Mantis 0.4's small single-activity UI would duplicate significant work. This is an engineering inference from the modules below; it is not a measured tvOS27 similarity score or a Fire Stick performance result.

## Reproducible input and isolation

- Full upstream checkout: `../Tigris-Upstream`, unchanged; experimental Git worktree `../Tigris-Mantis-Experiment`, branch `mantis/api25-experiment`.
- Repository/tag: https://github.com/lesnolie/Tigris/tree/v2.0.27
- Upstream commit: `c6d258f467e0a8f68f5a112bb5aef9710e81392d` (tag and observed HEAD matched).
- Release API records publication `2026-11-10T00:28:59Z`; archive in `evaluation/upstream-release.json`.
- Official standard APK: 2,393,807 bytes, SHA256 `7b2b73bcbd6c80e034519e9d1bad01a9f946b00da0f490e5de78ddcb1b8c5950`.
- APK metadata independently read: package `com.leslie.tvhome`, code46, version2.0.27, min28, target31. ZIP contains no `lib/` native libraries. Signature verified with existing local tools; signer SHA256 `7d2b6be8a86ce8981e368dde4920869b90c8206137934a6d1fc89a2d1d1b6114`. This proves artifact identity, not API25 compatibility.
- Baseline comparison: MantisTV0.4.0 source `ab022168a5d8fff173ff0169fd328adb2040be89`; its original source files were read separately into `evaluation/mantis-0.4-baseline`. Existing0.4 APK/source,0.4.1 clock fix and0.5 Home preview are preserved.

## What the code actually does

Paths below are under `app/src/main/java/com/leslie/tvhome/` unless stated otherwise.

| Area | Implementation and limits |
|---|---|
| Home/Dock | `HomeActivity`, `DockLayout`, `TileAdapter`, `TileSkin`: native Views and RecyclerView; five default Dock columns, optional six; 16:9 banners, lower dock/grid, folders, rearrangement and return-focus state. Not an Apple binary or complete tvOS shell. |
| App catalogue | `AppRepo`: queries leanback, launcher and vendor entries; resolves real activities; uses installed TV banners, icon fallback and a byte-counted LRU. App labels/art are not substitute content recommendations. |
| Glass | `Glass`, `GlassMaterial`, `DockGlassFallback`, `FrostGlass`, `MovingGlass`, `GlassInk`: cached CPU bitmap preparation, rounded masks, tint/edge/refraction models, optional heavier extreme mode and modern/pre31 paths. Pre31 glass is substantial reusable code, not a reason to discard the entire design on API25. |
| Focus/motion | `TileAdapter.TileCell`: scale1.12 from TileSkin, 230ms focus/170ms blur, animated shadow and label, optional rearrangement jiggle. Current release deliberately uses scale-only app focus; fromDx/fromDy/tvos parameters do not restore actual directional parallax. Motion timings are implementation values, not Apple measurements. |
| Top Shelf | `Shelf`, `ShelfJson`, `ShelfLimits`, HomeActivity: built-in Bilibili/Douyu feeds and per-app custom JSON;15min response cache, bounded downloads and image cache. Not universal Prime/Netflix/Apple TV recommendations, no Jellyfin/Emby adapter automatically exists. |
| Control Center/media | `ControlCenter`, `NowPlaying`, `tv/TvControls`: real session controls where access exists; Android audio/display/settings intents and vendor adapters. Power, force-stop, secure settings and some cleanup rely on local ADB. An HDMI stick cannot safely expose television-panel brightness/input controls by assuming Sony's APIs. |
| App Switcher | `TvOSFeatures`, `AppSwitcherRouter`, `ShotCapture`, `AppCloser`: stored recent launches/optional UsageStats, stacked cards; API30+ accessibility screenshots, not live system task previews. API25 must use a clear non-screenshot fallback. Dismissing a card is not proof of force-stopping its process. |
| Screensaver/media | `PhotoFrame`, `FrameIdle`, `FrameIdleProbe`, `FrameIdlePolicy`, `Aerials`, `VideoWallpaper`, `MediaQuality`: in-app photo timer and optional DreamService, playback checks, Media3 streams and hardware decoder checks. Apple-hosted film catalogue does not confer film redistribution rights. |
| Wallpaper/phone | `Wallpapers`, `WallpaperActivity`, `PhoneServer`, `MediaFiles`: downloaded/local/custom photos, ExoPlayer video, QR page for local uploads; token-protected HTTP service while wallpaper UI is active and a short grace period. This works through a browser in principle; it is not AirPlay, HomeKit, iCloud or native iPhone Remote pairing. |
| Settings | `SettingsActivity`, `Prefs`, `TvOSFeatures.TvOSDeviceSettingsActivity`: extensive appearance/remote/content/maintenance settings, consistent saved preference keys. Avatar upload is a portrait; preferences explicitly use one shared desktop, not isolated user profiles. Search in settings is weather-city lookup, not global app/media search. |
| Guard/maintenance | `Guard`, `HomeGuardService`, `KeeperService`, `RevivalSync`, `Cleaner.LocalAdb`: accessibility events, foreground service, notification-listener/sync revival and self-ADB grants/commands. They require separate security and firmware validation, and are excluded/blocked in the Mantis experiment. |
| Updates | `Updates`, `UpdateActivity`, signer/download/recovery policies: GitHub release discovery, size/hash/package/version/certificate checks, package installer sessions, pinned optional apps and recovery. Stronger than a naked APK link, but upstream self-package/channel/signature are unsuitable for an independently signed fork; self-update must remain blocked until fork identity and durable signing are solved. |
| Installation | `install.sh`, `tools/install-plan.py`, registry/checker, maintenance and vendor helpers: host-side planning/execution; upstream API28 gate and grants do not authorize running these on AFTMM. No installation script was used against the Fire Stick. |

## Build, tests and history

AGP9.4.1, Gradle9.8.2, JDK17 CI, compile36/target31/min28, Java17 bytecode, R8/resource shrinking, standard and guard flavors. Guard removes HOME declaration, not the maintenance runtime. Official Maven/Gradle metadata confirms these toolchain versions exist; the experiment removes mirror repositories and pins distribution checksum in cloud. No local Gradle/SDK/dependency build cache is created.

Direct dependencies: RecyclerView1.4.0, dadb2.0.0, Media3 ExoPlayer1.5.1, NanoHTTPD2.3.1, ZXing3.5.3; test-only JUnit4.13.2 and JSON20240303. Dadb's POM includes Okio2.10.0 and Kotlin1.9.20 runtime. The resolved graph and API lint, rather than a POM alone, are required for transitive assurance.

History read includes releasePR98, banner/frost groundworkPR94, earlier glass performancePR92 and idle cancellationPR91. Their existence is not AFTMM evidence.44 JVM test source files cover ordering, shelf JSON/navigation, glass recipes, media transfer, guard policies, signing/download/install recovery, idle ownership and other pure policies. This run's75 Python host tests passed. Local shell contracts were attempted but failed because their mock wrapper invokes absent `python`; this is a host harness issue, not a TV failure. Rerun those on the cloud runner. Cloud results and remaining blockers are appended separately; no compilation-only feature is labelled physically verified.

## Licensing and distribution

Preserve `LICENSE` (MIT, copyright2026lesnolie), `THIRD_PARTY_NOTICES.md`, Inter's OFL and all source notices. Material Icons/dadb/Media3/ZXing are attributed as Apache2; NanoHTTPD BSD3; Aerial/open-tv MIT; public Apple root certificate has a restricted trust purpose. Source MIT does not license Apple logos, videos, screenshots or media services.

The notices retain a historical bundled-assets section. **At this tag and in the inspected release APK the Apple startup video/default settings artwork are absent.** `OptionalAssets.kt` downloads them only after explicit selection from an older immutable revision. The experimental download is blocked. Earlier commentary describing current bundled assets was corrected. Website footage/comparison artwork is not republished with the experimental distribution. No Apple videos are downloaded for the audit.

The full source tree was cloned; Android modules, manifests, build/dependency definitions, notices, installation boundaries, relevant tests and current release were inspected. This is not a formal security proof or exhaustive protocol review. API lint/runtime gaps remain explicit in the compatibility report.
