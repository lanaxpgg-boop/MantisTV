# Tigris API25 / AFTMM compatibility

## Conclusion and evidence level

**Source port is plausible; shipping compatibility is not established.** The stock2.0.27 APK declares min28 and is incompatible with API25. Lowering that number alone leaves actual missing classes/methods and Fire OS service restrictions. The experiment adds a separate `mantis` flavor, min25, applicationId `dev.mantistv.tigris.experimental`, ARMv7 filter, with original namespace/layout modules preserved. All hardware results remain unverified.

AFTMM evidence carried forward from Mantis physical QA: MT8695/IMG GE8300, 32-bit ARM, nominal1.5GB/8GB; observed Android7.1.2/API25 and Fire OS6.7.1.1(NS6711/5908), MemTotal1,316,676KiB. That observation certifies neither all Fire OS6 builds nor this fork. The current device connection failed before new QA; no reconnect/install/key/grant command is part of this experiment.

## API inventory and actions

Locations refer to the pinned upstream source; line numbers can change after patches. `evaluation/imported-new-platform-classes.json` and the reviewed source record supplement the inventory. Symbol searching alone cannot establish exhaustive API coverage: dependency-aware lint with min25 plus runtime validation is a mandatory gate.

| API / source | First API | Port action / remaining requirement |
|---|---:|---|
| Bitmap.Config.HARDWARE / DockGlassFallback |26| Added explicit SDK>=26 check before field access; software bitmap path retained. |
| Typeface.Builder and font variation / Ui.Fonts |26| API25 uses createFromAsset + style fallback; variable-weight Inter rendering on old Minikin remains physically unverified. Ship static OFL weight instances if old-font rendering is poor, not Apple's proprietary font. |
| java.nio.file Files.move / MediaFiles |26| Replaced with checked same-directory File.renameTo on Android's POSIX filesystem. Never delete target before rename; missing-source failure preservation regression added. File durability across sudden power loss is not verified. |
| NotificationChannel, Notification.Builder(ctx,channel) / KeeperService, Updates |26| Shared gated notification factory; pre26 one-argument builder. Channel-only semantics not emulated on25. |
| Context.startForegroundService / KeeperService |26| SDK gate, startService fallback; entire keeper disabled in mantis flavor. Boot/foreground permission restrictions must be tested independently before any future enablement. |
| PackageManager.canRequestPackageInstalls, ACTION_MANAGE_UNKNOWN_APP_SOURCES / Updates, UpdateActivity |26| Gate per-app permission API/settings intent;25 uses ordinary Security settings and system install confirmation if installation is later supported. No writes to unknown-source settings. Experimental install backend is blocked. |
| PackageInfo.longVersionCode / Updates |28| Wrapper uses legacy versionCode below28. Existing positive version46 fits signed32-bit. |
| GET_SIGNING_CERTIFICATES/signingInfo / Updates |28| Wrapper selects GET_SIGNATURES below28; retains nonempty exact certificate set/pinned identity checks. No permissive no-cert fallback. Experimental installation is disabled pending platform tests. |
| AudioManager.activePlaybackConfigurations / FrameIdleProbe |26| Added SDK>=26 gate and a separate RequiresApi26 helper containing AudioPlaybackConfiguration types. API25 fallback uses public music state/session evidence; unknown foreign playback must fail closed, never claim paused media. The cloud compile/lint/runtime gates remain required. |
| AudioManager.getStreamMinVolume / tv/AndroidAdapter |28| Added SDK28 gate; media stream lower bound0 below28. Physical CEC volume still unverified. |
| AudioAttributes.USAGE_ASSISTANT / FrameIdleProbe |26| Constant can be inlined but class/method paths still need gating; not a substitute for complete playback visibility. |
| AccessibilityService.takeScreenshot/mainExecutor/ScreenshotResult / ShotCaptureApi30 |30/28| Existing outer30 gate and separate helper retained. API25 cannot obtain these screenshots through the same mechanism; disable screenshot preview, keep actual launch/history cards. No MediaProjection consent or privileged task-snapshot workaround added. |
| BlendMode / GlassInk, ControlCenter |29| Existing SDK guards; verify each helper call with lint. Pre29 uses older paint/layer behavior. |
| MediaCodecInfo.isHardwareAccelerated / MediaQuality |29| Existing29 check; earlier codec-name heuristic is evidence of implementation, not confirmed decoder capability. Probe actual codec support and decode failures. |
| UsageEvents.ACTIVITY_RESUMED / FrameIdleProbe, recent tasks |29| FrameIdleProbe guards it; integer event constants elsewhere are inlined. MOVE_TO_FOREGROUND available on25. Usage access still requires firmware permission and may be absent. |
| PendingIntent.FLAG_MUTABLE, SessionParams.setRequireUserAction |31| Existing31 gates. Installer confirmation is retained, not bypassed. |
| registerReceiver(flags)/RECEIVER_NOT_EXPORTED |33| Existing33 check/legacy overload, not an API25 blocker. |
| VideoWallpaper PixelCopy.request(Surface,Bitmap,listener,Handler) |24| This exact overload is available on25; do not confuse Window overload introduced26. Vendor codec/surface failure remains possible; retain static fallback. |
| DirectBoot/user unlock |24| API25 supports these framework entry points; no Fire OS boot persistence claim. Experimental boot receiver removed. |
| Persistent Home-key filter |31| Upstream Android12+ feature stays unavailable on25. Accessibility does not grant universal HOME interception. |

Core library desugaring is enabled (`desugar_jdk_libs:2.1.5`) for Java library API compatibility in transitive code; it does not backport arbitrary Android framework APIs. Java17 build bytecode is converted by D8/R8, not shipped as a requirement for a desktop JVM on the Stick. Build/runtime linkage must still pass.

## Dependency/native audit

Keep exact upstream versions initially; **no dependency downgrade is justified solely by app min28**. RecyclerView's compileSDK requirement is distinct from its runtime floor. Media3's supported playback floors include25; codec support is device specific. NanoHTTPD/ZXing are JVM libraries; dadb/Okio and Kotlin need transitive bytecode/desugaring inspection. Official2.0.27 APK has zero packaged native libraries, so no observed64-bit-only JNI obstacle. The generated fork APK must be rechecked; an abiFilter alone proves nothing about hidden native payloads or successful decoding.

Save resolved runtime graph, every AAR minSdk and native ZIP entries and lint diagnostics in cloud. Do not force older AndroidX globally or use tools:overrideLibrary to silence minSdk conflicts. Where a dependency actually requires26+, pin the newest verified25-compatible version and run its policy/regression tests. Until that evidence exists, transitive audit is a gate, not a completed assertion.

Primary references: [Bitmap.Config](https://developer.android.com/reference/android/graphics/Bitmap.Config), [Context](https://developer.android.com/reference/android/content/Context), [Java API desugaring](https://developer.android.com/studio/write/java8-support), [RecyclerView release notes](https://developer.android.com/jetpack/androidx/releases/recyclerview), [Media3 device support](https://developer.android.com/media/media3/exoplayer/supported-devices). Exact pinned AAR evidence takes precedence over today's newer-library documentation.

## Fire OS6 restrictions and experimental boundary

No dependency on Google Play services is apparent in the direct graph, but standard Android intents (settings, app install, volume and media sessions) must resolve at runtime. A Fire Stick is an HDMI source, not a television tuner/display panel: brightness, HDMI input switching and CEC volume cannot be promised by generic Android/Sony adapter behavior. Hide/describe unavailable capabilities before user-facing acceptance.

Normal-app launch is the first goal. Experimental manifest has LAUNCHER/LEANBACK_LAUNCHER, no HOME category; boot, keeper, guard, listener, sync, dream and update services/receivers are removed, along with privileged settings/usage/overlay/install/delete permissions. Runtime gates additionally block self-ADB, cleanup, secure timer ownership, automatic updates and artwork downloads. Maintenance controls still need an availability presentation pass before physical use; they are not completed features.

Default HOME/remote physical HOME, startup auto-launch, accessibility enablement and survival across sleep/boot are separate unknowns. Do not transplant upstream installation grants or Sony/TCL workarounds. No root, firmware replacement, bootloader, exploits, package removal or stock-HOME disabling is proposed. The older Android12 Home-key filter is not backportable merely by changing constants.

All documented AFTMM firmware revisions remain required future test rows. Only the exact previously observed firmware has any Mantis physical evidence; this experiment has none. Apple account, AirPlay2/DRM, HomeKit, Siri and native iPhone Remote do not appear as implemented protocols and cannot be obtained through API compatibility wrappers.
