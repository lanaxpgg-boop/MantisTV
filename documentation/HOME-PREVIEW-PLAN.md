# 0.5.0 Home composition preview

## Scope and reference

This is the first visible Home redesign toward maximum feasible1:1 latest-tvOS acceptance. It preserves the0.4/0.4.1 installed baselines. It does not establish full tvOS fidelity or Apple ecosystem functionality.

Current primary visual reference inspected November10,2026: [Apple TV4K page](https://www.apple.com/apple-tv-4k/) and its [Home image](https://www.apple.com/v/apple-tv-4k/ap/images/overview/welcome/hero__fwxqo8gh62ye_large.jpg). The image shows a six-app row low on the screen with another row peeking below, large artwork above and focused-card emphasis. This is a marketing perspective image without an explicit tvOS patch-version label, so it cannot establish pixel-exact27.2 geometry. Earlier frontal tvOS26 reference remains separately versioned in TVOS-DESIGN-SPEC.md. Detailed Home guide fetch again returned429; no bypass was used.

## Implemented source slice, awaiting gates

- Full-bleed installed-app banner/gradient backdrop and spatial six-column app grid; old greeting hero and seven page tabs removed from Home.
- App focus updates real installed-app title/banner and an actual Open target. No invented movie posters, Continue Watching progress or provider Top Shelf feed.
- Bottom app row begins near72% viewport height, with another row partially visible; vertical scrolling reveals functional Library/Search/Recent/Watchlist/Controls/Settings tiles and remaining apps.
- Existing local favorites order the grid; unused/stale/duplicate app IDs are filtered. Explicitly saved empty favorites no longer apply default preferred ordering. Host tests cover default/saved/empty/stale/duplicate/immutability cases.
- Directional focus IDs, no row wrapping, focus scale/tilt/layer translation, reduced-motion branch, and Home focus restoration after app/page return.180ms/1.10scale are provisional implementation values, not measured Apple timings or achieved hardware latency.
- Original vector-style line glyphs, native live clock/profile entry and actual palette preference. No Apple artwork/fonts bundled.
- Initial duplicate discovery/page rebuild removed; debounce/cancel pending hero-art requests and reject stale banners. Existing icon cache shared; Home tiles rasterize installed resources, hero bitmap bounded768×432. Actual PSS/frame behavior must be measured.

Supporting pages retain their existing UI until later reconstruction. App launch, profile preferences and saved lists remain real existing functions. Native Apple Remote/AirPlay/HomeKit/media protocols are not implemented by this design change; see APPLE-ECOSYSTEM-PLAN.md.

## Delivery and physical gates

Version0.5.0-preview/code6. Package dev.mantistv.home.design is a separately debuggable Home Preview; preserve original dev.mantistv.home0.4 and dev.mantistv.home.debug0.4.1. Ephemeral signing does not establish future upgrade continuity. Never uninstall or clear an app to bypass a certificate mismatch.

Build only on free standard public cloud runners: API25 compilation, updated host rule tests, seven selected lint checks, manifest/version/SDK/ABI/ZIP/alignment/signature inspection. Verify exact source/APK/certificate/checksums before any isolated design APK installation. No local build/SDK/cache downloads, paid service, root/flash/default-launcher/system-package/network-setting change.

Actual AFTMM acceptance must inspect Home composition/focus bounds/screenshots, no Home page tabs, directional movement/edge behavior, Home-to-utility-to-Home restoration, explicit app launch-return, clock, reduced motion, profile/palette behavior, working Watchlist/Recent access, app/process return and Amazon Home escape. Capture PSS/frame/log observations without claiming sustained/native4K acceptance. Report observed failures and fidelity deviations; a successful build is not a UI pass.
