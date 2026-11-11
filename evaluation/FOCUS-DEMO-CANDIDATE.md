# TV focus demonstration candidate — source checkpoint

2026-11-11. Next candidate changes only the existing TileAdapter.kt, preserving the prior verified APK and Mantis fallback.

Reuse: restores Tigris MIT directional tile focus/sheen from upstream1badf10 using the existing Tween, Motion, shadow cache and View owners. Keeps current banner artwork, TileSkin dimensions/radius/scale/shadow strengths and current230/170ms durations. These constants are not measured tvOS27 timings. Existing simple style remains scale/shadow; tvOS style uses travel direction for transient tilt and sheen. No new library, service, bitmap cache, continuous idle loop or Apple asset.

Adaptation: cancel the existing edge-bump callback before focus transition; settle translation/tilt; respect zero system animation scale for this tile effect (instant visible focus, no sheen/jiggle). Cancel/reset focus/bump/jiggle on detach/recycle and restore actual focused pose on attach. Guard null moving-package state to avoid idle jiggle on invalid positions.

Source formatting reviewed. No Android compilation or new physical check yet. Existing previous tests do not verify this changed renderer. Required next gates: one explicitly authorized free cloud build of this exact source and all three flavor regressions, lint/signature/manifest/integrity; then separately authorized physical foreground test without replacing existing Mantis or stock HOME. Check directional transitions, simple/tvOS distinction, rapid repeat, interruption/scroll/recycle/return, system animation-off, tile sharpness and frame time on AFTMM. Do not present this as exact tvOS27 parallax or a validated client demo from source alone.
