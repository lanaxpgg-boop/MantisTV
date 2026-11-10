# MantisTV physical test report — November 10, 2026

## Result and scope

**Installed and basic physical smoke tests passed, with defects and acceptance gaps. This is not tvOS fidelity acceptance or a production release.** Tested the unchanged 0.4.0-preview APK on one AFTMM/mantis, Fire OS **6.7.1.1 (NS6711/5908)**, Android 7.1.2/API 25, armeabi-v7a. Original compiled source: ab022168a5d8fff173ff0169fd328adb2040be89. Test window: first launch 14:25:22 UTC through final evidence around 14:49 UTC (about 24 minutes, including idle, inspection and interaction; not continuous load).

The user explicitly approved a temporary 15 GiB minimum free-space floor, at most 100 MiB QA output, no SDK downloads and no local builds. Existing ADB/JBR/build-tools were used. Final evidence occupied about 18.6 MiB; the Mac retained about 28.3 GiB free. The original 15% reserve is not silently abolished for future development.

## Artifact and installation

APK: MantisTV-0.4.0-preview.apk, 29,235 bytes; package dev.mantistv.home, versionCode 4, min/target25, not debuggable. SHA256: `6cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4`.

Certificate SHA256: `84c0078a6f74ddf42cc3a3b2705906bf302cbc6478665649af52a8211995dbd1`. Independently verified the actual signature at API 25 floor, checksum, published digest, ZIP CRC/DEX-only packaging, manifest and alignment before installation. See APK-VALIDATION.json/md. The app was absent at baseline. A normal serial-scoped install returned Success in 1.349 seconds; package inspection confirmed the installed version. No replace/uninstall/data-clear operation was needed.

## Executed checks

| Check | Actual observation | Evidence |
|---|---|---|
| Initial launch | Status ok; ThisTime/TotalTime 840 ms, WaitTime 871 ms; Home rendered | LAUNCH-RESULT.json; screenshot001 |
| Landscape layout | Home, Library, Search and Watchlist rendered in landscape; no initial layout crash | screenshots001,005,007,012; UI XML |
| Four D-pad directions and OK | Focus moved from filter to app cards and between adjacent cards; navigation opened pages | QA-EVENTS; library-dpad-navigation XML; screenshot006 |
| Search | No-match state for Settings; Appstore query produced matching button | search-no-settings-match and search-appstore-match XML; screenshots008–009 |
| External app launch/return | Selected official Appstore card; foreground became com.amazon.venezia/.grid.AppsGridLauncherActivity; Back returned MantisTV | QA events and foreground observations; screenshot011 after return |
| Recent history | Appstore appeared in Recent after a process restart | recent-after-restart XML |
| Idle screensaver | Home visible at ~295.6 seconds; landscape screensaver visible at ~327.5 seconds, without key input between launch and those observations | screenshots002–003; QA-EVENTS |
| Screensaver dismissal | Back returned Home | screenshot004 |
| Manual screensaver | Controls → Screensaver opened landscape | manual-screensaver-focus XML; screenshot014 |
| Watchlist save | QA fixture MantisTV_QA_20261110 saved and rendered | watchlist-saved XML; screenshot012 |
| Profile isolation | Profile 2 watchlist empty; switch back to Profile 1 restored fixture | profile-two-watchlist and profile-one-watchlist-restored XML; screenshot013 |
| Process persistence | Force-stopped only MantisTV, relaunched; Profile 1 watchlist fixture remained | post-restart-watchlist XML; screenshot015; QA events |
| Menu | Opened foreground Control Center, with WIFI and 1920×1080/59.94Hz reported | menu-controls XML |
| Motion preference | Toggled Reduce motion on, verified label; restored off afterward | reduce-motion-on/restored XML |
| Home and Back escape | Home returned Amazon Home. Back twice from MantisTV Home also exited to Amazon Home. Explicit relaunch succeeded | QA events; final-home screenshot018 |
| Safety comparison | Only MantisTV added; no packages removed; zero disabled packages; original Amazon default HOME retained | FINAL-SAFETY-CHECK.json; private baseline/final inventory |

The QA fixture remains in MantisTV Profile 1. Profiles3/4, rename, watchlist deletion, appearance palette changes, volume effects, privileged/unavailable settings intents, app removal, real media playback, reboot/update/rollback and real-remote long-press acceptance were not verified. No claims of Apple integration or complete tvOS navigation follow from these checks.

## Defects and limitations

1. **Confirmed stale Home/header clock.** Home retained 15:25 while the real five-minute screensaver showed 15:30. Source creates the header clock during page construction.
2. **Confirmed frozen reduced-motion screensaver clock.** Device time crossed from 15:46:39 to 15:47:05; screenshots 016/017 were byte-identical and showed15:46. Source schedules no periodic redraw in reduced-motion mode. Restored Reduce motion off.
3. **Long-press automation failed.** Existing ADB input keyevent --longpress DPAD_CENTER launched Appstore rather than opening MantisTV actions. Returned immediately; no store actions/purchases. This does not prove a held physical remote button fails. Favorites/app-info remain unverified physically.
4. **Vendor icon fallback.** Initial current-session warnings reported an Amazon miniTV resource failure; the default Android icon appeared. No associated fatal startup error observed.
5. **Visual fidelity fails the definitive product requirement.** The seven-button page navigation, procedural hero, generic grid and full-page Controls are a development scaffold, not a faithful tvOS reconstruction. See documentation/TVOS-DESIGN-SPEC.md.

Captured crash buffer and relevant current-session app logs contained no observed MantisTV FATAL EXCEPTION, ANR or OutOfMemoryError. Log capture is bounded and buffers may wrap; this is not proof that no failure can occur. Historical PID-reuse messages were excluded from startup conclusions. One deliberate force-stop is the persistence test, not a spontaneous crash. No thermal or long-term stability claim is made.

## Preserved state and recovery

Stock Fire OS, default Amazon launcher and existing packages remain. No root, flash, reset, DNS/OTA/launcher mapping change, package disabling, user-data clearing or uninstall occurred. Small uniquely named QA UI XML files were created under /data/local/tmp for built-in diagnostic capture. Local private logs/screenshots/inventory are excluded from public publication; their hashes are indexed in LOCAL-EVIDENCE-INDEX.json.

The installed preview remains available. Its ephemeral signing key is not a safe upgrade basis. Do not uninstall it to bypass a future certificate mismatch. Existing published installation/rollback guides are preserved; uninstall/rollback was not exercised. New app builds remain cloud-only, free standard public runners, with no local SDK/cache installation.
