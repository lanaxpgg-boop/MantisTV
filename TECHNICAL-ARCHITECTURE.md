# MantisTV technical architecture

Recovery baseline: 2026-11-11. See PROJECT-RECOVERY-REPORT.md for pinned comparisons and feature evidence.

## Current applications and proposed foundation

Preserved MantisTV app is a small native Java Activity/View implementation with AppCatalog, StoreRules and HomeScene.0.4/0.4.1/0.5 have separate retained artifacts and narrow physical evidence. No source overwrite or migration has happened.

Existing Tigris-Mantis-Experiment worktree, mantis/api25-experiment, is the leading conditional successor. HomeActivity owns native View/RecyclerView navigation; DockLayout/TileAdapter own grid and focus; Shelf owns provider presentation; AppRepo owns actual package discovery; Prefs owns durable settings. ControlCenter and SettingsActivity use existing service adapters. Glass/FrostGlass/MovingGlass/DockGlassFallback own material rendering; keep cached processing and bounded compat quality until hardware data supports change. Media3/PhotoFrame/Aerials/VideoWallpaper own media lifecycle, not a second UI engine. PhoneServer/NanoHTTPD plus existing HTML/i18n own tokenized local upload/config, not native Apple Remote.

Runtime flow: shared feature → existing capability/service owner → TvControls/TvAdapter or Api25Compat → supported Android/Fire OS behavior. Probe capabilities at use time; an unsupported result must stay unavailable. No host installation commands in adapters. SDK/desugaring/manifest flavor are build concerns; host tools stay separate from runtime.

Mantis flavor min25, isolated dev.mantistv.tigris.experimental package, no HOME takeover/guard/maintenance components, first-run lightweight profile. Standard/guard variants are retained and regression-built. Dependencies remain RecyclerView1.4, Media3 1.5.1, NanoHTTPD2.3.1, ZXing3.5.3, dadb2.0 and desugar2.1.5. Static build/lint success is not hardware support.

## Reuse policy and ownership

Discover → verify source/license/API/test evidence → reuse → adapt → measure → optimize. Custom code only for a documented incompatibility or absent suitable component.

- Continue Tigris owners; no new Home/glass framework or background daemon.
- Hume/tvOS-Launcher profiles/search are donor candidates, not yet copied. Keep Apache2 LICENSE/NOTICE alongside TigrisMIT/third-party notices if adapted; configure actual providers and review API26 WatchNext fallback.
- DumbTV is a read-only FireOS capability/settings knowledge source; no DNS/debloat/OTA behavior.
- PhairPlay is a separate optional receiver experiment with independent native-license, API25/ARMv7, signing, security, lifecycle and resource gates. Do not couple receiver startup to every Home render.
- tvHome code reuse blocked until a usable license is established; screenshots are local comparison references only.

## Specific custom work and justification

Api25Compat and desugaring adapt newer calls without deleting upstream behavior. Flavor isolation and safety gates implement the user's explicit no-system-modification constraint. Capability presentation is necessary because upstream UI otherwise offers functions excluded by this flavor. Reuse existing localization/page and denial responses instead of a second endpoint/framework. No protocol receiver, profile framework or search engine should be written from scratch before reviewing the donors above.

## Validation and promotion

Current verified artifact identifies c9671be, cloud runtime a03d9546e63244641e97c6385235270e4e0406d3; later source-only edits do not inherit it. Required gates: reproducible free-cloud tests/lint/metadata/signature → certificate continuity → authorized foreground AFTMM smoke → exact-tvOS comparison → controlled hardware/soak → exact-firmware coverage. Preserve old fallback until successor passes. FullOS replacement, private Apple services and native Apple Remote are not delivered by this architecture.
