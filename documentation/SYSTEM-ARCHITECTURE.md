# Architecture

Stock Fire OS preserves vendor decoding, streaming apps, DRM, HDMI and audio. MantisTV is an ordinary foreground Android Activity; it does not replace protected system components. No root, privileged service, accessibility interceptor, boot receiver or HOME intent filter is present.

MainActivity implements native View-based TV navigation, explicit app launch and guarded settings shortcuts. AppCatalog independently discovers public PackageManager launch intents; LandscapeView independently renders original artwork and screensaver scenes. StoreRules holds pure history/search logic, independently exercised on the host JVM. SharedPreferences persists four local profiles with bounded recent history, favorites and local watchlists. Application icons are normalized to 96px on one worker thread and cached under a 2 MiB budget. Original landscapes draw with Canvas; screensaver updates stop outside the foreground. No network server or externally accessible control endpoint exists.

Native resources and SDK tools compile Java to DEX without Gradle, Compose or native libraries. No emulator is needed. Workflows use one standard Ubuntu cloud job, resource gates, timeouts, no dependency caches and GitHub Release delivery.

Next modules: split UI screens and profile persistence further; add Android lint alongside the existing direct API25 compilation gate; instrumentation tests on supervised hardware; optional receiver app chosen only after license/ABI/security review. Keep protocol integrations out of the UI process.
