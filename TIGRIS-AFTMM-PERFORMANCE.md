# Tigris AFTMM performance audit and lightweight configuration

Latest checkpoint: **API25 build,226 JVM tests and both lint variants passed; APK checksum/signing/metadata verified. Hardware execution untested.** See the additional authorized verification section below. [Artifact](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-verified-42894116665).

Earlier audit and bounded-run evidence are retained below as history.

## Evidence boundary

No Tigris startup, RAM, frame-time, thermal or idle-CPU measurement has been made on AFTMM. The Fire Stick is untouched. Sony BRAVIA upstream screenshots/measurements and Mantis measurements are comparison context, not transferable results.

Earlier Mantis0.4 physical record:840ms first launch,37.58MiB initial PSS,72.20MiB after interaction,3.21% cumulative gfxinfo jank in a mixed session.0.5 first launch was1193/1250ms in isolated design package; later navigation was confounded by user remote input. These are not controlled same-workload benchmarks against Tigris.

## Source cost model

- Native Views/RecyclerView/property animations avoid a browser engine. Tigris has no JNI payload in the inspected release; video decoding uses Android/Media3 and may allocate codec buffers outside Java heap.
- `AppRepo` caches400x225 ARGB tile bitmaps:360,000bytes each before view/GPU copies. Upstream cache cap24MiB. The experiment caps it at8MiB (roughly23 such buffers), with normal LRU eviction.
- `Shelf` caches processed images with maxMemory/5 clamped12–24MiB plus disk cache; this is a separate memory consumer. Wallpaper, glass intermediates, shadows, settings prebuild and ExoPlayer coexist; do not add only one cache and call it total RAM. Reduce the shelf budget further if actual pressure requires it.
- A1920x1080 ARGB bitmap is7.91MiB;3840x2160 is31.64MiB. Crossfades, blur input/output and IntArray temporaries can multiply peak usage. Decode sampling/caps must precede allocation; largeHeap is false.
- `Glass`/`DockGlassFallback` perform CPU sampling/box blur/per-pixel masks and cache the result, retaining full mask geometry. Re-baking on every Dpad event or synchronous large decoding would be a latency risk. Home prepares background work on its executor; validate frame traces at every change/Top Shelf return, not just steady idle.
- Pre31 fallback uses still samples160px(low-end)/240px, video capture320px, weak-device refresh10s rather than4s, and slows/stops refreshing after roughly150ms capture cost. AFTMM's memory class triggers the weak/low-end paths. PixelCopy Surface overload is API24, but vendor decoder behavior is unknown.
- Extreme glass adds refraction/dispersion/lens work and layers. Keep it off initially; preserve cached normal glass rather than replacing the entire UI with flat panels automatically. Current focus is scale+shadow, not a costly per-frame content blur.
- `HomeActivity` minute-boundary ticker and paused-video/idle checks, background keeper's30s ownership loop, updates every6h, phone-server grace and worker pools all matter for idle. Experiment removes keeper/revival/boot/update jobs; activity callbacks must still be checked in real pause/return cycles. TileAdapter's two-thread loader lifecycle and static shadow-cache growth deserve follow-up before release.

## Implemented experimental profile

`MantisExperiment.defaults`, mantis manifest, `AppRepo`, `MediaQuality`, `DockGlassFallback`:

| Setting | Experimental choice | Rationale |
|---|---|---|
| Rendering |Keep Tigris geometry, rounded masks, glass and scale/shadows |Preserve visual composition |
| Default material |Normal cached glass; extreme off; glass Control Center selected |Avoid unnecessary flat-panel downgrade |
| Default background |Built-in graphite; user may choose photos |Repeatable offline first launch, no initial download burst |
| Bitmap quality |1080p maximum wallpaper/aerial preference path; disable forced UHD in mantis flavor |Bound32-bit heap peaks |
| App artwork cache |8MiB |Bound recurring app-art pressure |
| Video |Off by default; existing weak-device capture fallback retained |No claim of hardware decode performance |
| Maintenance |Keeper/guard/revival/boot/system-dream/timer takeover disabled; selfADB/cleanup/install backends blocked |No idle maintenance or system changes during visual experiment |
| Intro |Welcome complete; intro default off; Apple asset downloads blocked |Reach Home directly without external presentation files |
| Font |Inter preserved, old-API load fallback |Maintain closer typography; exact weights require visual test |

These are implemented resource constraints, **not proven optimizations**. Shelf/scene/codec memory is not globally capped by the8MiB app cache. Prefer performance evidence over indiscriminately removing glass. A720p sample profile is a fallback candidate only if measured1080p costs demand it.

## Required physical benchmark

On an integrity-verified separately signed/package build, record exact fingerprint/display resolution, process startup mode and instrumentation. Reuse Mantis cases: cold/warm start, rapid grid traversal, Top Shelf enter/return, Control Center, photo change, app launch/return,20 repeated cycles and10min idle with no remote input. Collect PSS/heap/graphics, gfxinfo frame histograms and input-visible timing, CPU and thermal state; distinguish60Hz vs other modes. Repeat baseline/candidate under comparable media/network conditions.

Proposed acceptance targets, not results: visible focus response p95<=100ms; p95 frame timing within16.7ms on60Hz scenes where hardware supports it; no sustained PSS growth across20 cycles; no background video/worker wake loop after pause; no OOM/ANR/crash. Define a measured RAM ceiling from available device headroom before enabling video/extreme glass. Normal-app Home/Back escape and Amazon Home recovery remain mandatory regardless of speed.

## Final bounded cloud result — 2026-11-10

[Run42888758342](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42888758342) compiled and assembled the min25 Mantis variant. **226 JVM tests passed, zero failures;75 Python tests and the mocked shell installation contracts passed. Full lint failed:9errors/79warnings.** The failed result is preserved, not replaced with a green compilation claim.

Cloud runtime source: `06f9a10afc5df45971c7d4a49ad547be4c7bdcd9`. Evidence/checksum commit: `cf5f4072f44be19b979b9fc412828b61ffb7b57f`. [Separate source branch](https://github.com/lanaxpgg-boop/MantisTV/tree/experiment/tigris-api25-42888758342). [Diagnostic prerelease](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-api25-42888758342). The release deliberately contains **no experimental APK** because the full gate failed; its signing/integrity and actual ARMv7 execution have not been verified.

The21 resolved AAR manifests all declare minSDK14–21 and contain zero `jni/*.so` entries; no AndroidX downgrade was needed. The complete runtime dependency graph is saved. Kotlin resolves2.2.10, collection1.4.2, core/core-ktx1.13.0, lifecycle2.6.2, coroutines1.6.4, profileinstaller1.3.1, annotation1.8.1, Guava33.3.1-android and Okio2.10.0 alongside the pinned direct dependencies. JVM-only artifacts have no Android minSdk declaration; desugaring/DEX compilation passed, but reflection/vendor linkage still needs runtime tests.

Three API findings remained: MovingGlass's ColorMatrixColorFilter.getColorMatrix(API26), UpdateGlass's Canvas.clipOutPath(API26), and the unversioned defaultFocusHighlightEnabled theme item(API26). Six manifest lint findings concern explicit TV banner/features/exported metadata and protected-permission **removal markers**. Fixes for all nine are saved locally and in `TIGRIS-POST-BUILD-FIXES.patch`, **not rebuilt or verified**. The API25 lens fallback retains tint/single-pass geometry while disabling chromatic channel decomposition; clipping uses the old difference-path operation; the theme item moves to values-v26. ProtectedPermissions ignores apply only to removal markers, never NewApi.

Two actual cloud attempts were used; one earlier recipe validation rejection did not start a runner. No further cloud build is started in this bounded experiment. Preserve the checkpoint and resume verification in a later resource window. No device install, remote input, grant, root, firmware, bootloader or default-HOME operation occurred. No local Gradle/SDK/cache build was performed. Exact tvOS27 fidelity, physical performance, Apple ecosystem integration and all-firmware compatibility remain unverified or unimplemented as specified in the comparison.

## Additional authorized verification — 2026-11-10

**The nine lint fixes now pass.** [Free cloud run42894116665](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42894116665) completed successfully:226 JVM tests/zero failures;75 Python host tests and mocked shell contracts passed. Both debug and release lint report **0errors,0NewApi issues,79warnings each**. Warnings remain open; this is not physical or visual acceptance.

[APK and evidence prerelease](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-verified-42894116665) · [Verified experimental source](https://github.com/lanaxpgg-boop/MantisTV/tree/experiment/tigris-verified-42894116665). Runtime source commit `2ae8bcd8099246fcdbe5e7c19b85cca06d7b198c`; evidence commit `d9fc2a371383573ae514a89869ce7ef6f6cc8834`. The cloud checked237 source/resource/license hashes before compilation. Independent download checks reproduced release SHA256, API25 signature, alignment, ZIP integrity, metadata and DEX results using only tools already present on the Mac; no local build or new SDK/cache download occurred.

| Artifact check | Observed result |
|---|---|
| Package/version |dev.mantistv.tigris.experimental /2.0.27-mantis-api25-experiment /code46|
| SDK |min25,target31,compile36|
| APK size |1,577,541bytes (~1.50MiB)|
| APK SHA256 |`435b7b1434a467ce801ea58af0b9db4fc6557c81e921d9f80fc65391ed14e4ba`|
| Certificate SHA256 |`d103a704bfa582e2bb54196ed3756a6a2fabc932d10b9316f33cb26d8b1fae37`|
| Signature on API25 |APK Signature Scheme v2 verified; one self-signed Android Debug certificate|
| Alignment/archive |4byte ZIP alignment and all ZIP entry CRCs passed|
| DEX |classes.dex/classes2.dex use037 headers compatible with the declared API25 floor|
| Native code |No .so entries; no observed64-bit-only native dependency|
| HOME/system components |No HOME category; selected boot/guard/revival/keeper/dream/update components and privileged permissions absent in packaged manifest|
| Device execution |Not performed; no ADB/device commands or installation|

**Signing continuity is unresolved:** the valid certificate comes from a temporary cloud test key. The private key is not retained; this artifact does not establish a safe future in-place update path. Never uninstall an existing application or clear its data to bypass a signature mismatch. This separate package does not replace Mantis0.4/0.4.1/0.5. All three Mantis APK hashes and Mantis app-source hashes were checked unchanged.

The earlier published follow-up patch omitted a newly added values-v26 theme resource. A6second source preflight correctly stopped before Gradle, SDK setup or APK generation. The complete committed patch (`TIGRIS-NINE-FIXES-COMPLETE.patch`) includes that file; source-only replay reproduced all237 expected files before the successful cloud build. Record both workflow executions honestly: one preparation-only failure and one additional actual verification build. No further build is authorized in this window.

The new result supersedes the previous nine-error/withheld-APK checkpoint **only for static build and artifact gates**. AFTMM startup, remote interaction, frame timing/RAM/thermal performance, all firmware variants, exact tvOS27 screen fidelity and Apple ecosystem integrations remain unverified or unfinished. Keep OptionA conditional; preserve Mantis as the physical fallback. Next: address unavailable maintenance controls and durable signing, then obtain a separately authorized physical test window. The Fire Stick remains untouched.
