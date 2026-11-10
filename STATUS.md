# MantisTV status — physical QA and product direction, November10,2026

**Milestone:0.4 preview installed and smoke-tested on one exact AFTMM firmware; full tvOS recreation acceptance remains incomplete.** Definitive target is maximum feasible1:1 latest Apple tvOS, not a generic Android TV launcher with glass styling. Current layouts/focus/media integrations fail final fidelity acceptance. See documentation/TVOS-DESIGN-SPEC.md.

Exact target:Fire OS 6.7.1.1(NS6711/5908), Android7.1.2/API 25, armeabi-v7a. Verified normal install, Home, D-pad/OK, Search, official Appstore launch/Back return, recent history, Menu, Home/double-Back escape, local watchlist save/profile1↔2 isolation/process persistence, manual and real5min idle screensaver. Confirmed stale Home clock and frozen reduced-motion screensaver clock; ADB long-press actions failed, real-remote path outstanding. No observed MantisTV fatal/ANR/OOM in bounded captured relevant logs. About24min mixed QA is not long-term stability acceptance.

First launch 840 ms; PSS37.58 MiB initial/72.20 MiB post-interaction;2,556 frames/3.21% cumulative jank. These are snapshots/mixed counters, not sustained/native4K performance proof. Exact test/performance/safety evidence:device-test/2026-11-10/DEVICE-TEST-REPORT.md and PERFORMANCE-REPORT.md.

119unique feature rows, exactly one status each:22Completed and verified(narrow behavior/tool scope),4Implemented but not physically tested,45Partially implemented,46Not implemented,2Blocked. No functional pass credits tvOS visual or Apple-protocol fidelity. Full firmware-family support, media/Apple integrations and OS replacement remain unmet.

APK0.4.0-preview/code4/dev.mantistv.home,29,235bytes; SHA2566cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4; compiled source ab022168a5d8fff173ff0169fd328adb2040be89. Unchanged installed APK retained. Persistent signing/update continuity unresolved; no automatic uninstall/data clear.

User-approved temporary 15 GiB free-space floor/100 MiB QA cap/noSDKdownloads/no localbuilds applied; final~28.3 GiB free/~18.6 MiB evidence. Existing tools only. No paid service. Original Amazon default launcher retained; only MantisTV added; none removed/disabled. No firmware/root/reset/DNS/OTA/launcher mapping change.

Next:repair clocks/signing continuity; obtain versioned latest tvOS screen references; reconstruct measured Home/TopShelf and focus system while preserving working catalog/store/search; then capability-backed Controls/Settings, media and firmware/performance gates. PROJECT-PROGRESS.md contains all features and ten prioritized tasks. This run updates evidence/design/backlog, not runtime source or app version. Public release provenance is recorded separately after actual publication.

## Storage recovery

The latest user-requested recheck confirms 43.79 GiB free against the normal 34.24 GiB reserve (9.54 GiB headroom). The normal reserve is satisfied; the temporary 15 GiB exception is no longer needed. Cloud-only builds and no new local SDK/cache downloads remain in force. This recheck made no device changes. See RESOURCE-RECOVERY.json in the device-test directory.

## 0.4.1 source candidate — verification pending

The two reproduced clock defects have source fixes: native live header clock and minute-boundary reduced-motion screensaver redraw.0.4.1/code5 remains pending cloud and physical regression gates; do not credit it from source changes alone. Preserve installed0.4 and use only the separate debug candidate for new physical QA after artifact validation. Persistent signing remains unresolved. See documentation/CLOCK-FIX-CANDIDATE.md.
