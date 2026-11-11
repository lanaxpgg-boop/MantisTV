# Phone capability follow-up — source and host checks only

2026-11-11, after the recovery audit. Base:93da3b7, runtime c9671be. The recovered APK is unchanged and does not contain this follow-up.

Reused existing PhoneServer state/asset endpoint, HTML renderer and English/Chinese localization. Added optionalAssetDownloads capability derived from the existing Mantis flavor. UI disables unavailable default downloads and explains the restriction; removal of an existing default remains available. Both buttons start disabled until state loads; legacy/standard state preserves existing downloads and in-flight buttons remain disabled. Uploaded user media/avatar/backdrop handling is unchanged. Server rejects excluded requests with HTTP403/localized explanation before download, retaining OptionalAssets.download's own defensive gate.

Custom glue is justified because upstream does not know which functions the isolated flavor excludes; no new server/dependency/service/protocol or copied donor code was added.

Passed: existing phone localization coverage, dictionary/interpolation consistency; executable VM tests for blocked buttons, retained deletion, standard/older-state fallback and in-flight polling; JS parsing and git diff --check. Host tests are not browser rendering, Android compilation or API25 runtime tests. Node runtime was the existing tool runtime; no installed Mac toolchain/cache or new package.

Pending: one later bounded free-cloud verification, Android endpoint exercise, actual phone UI/accessibility and AFTMM tests. No build or device action during this recovery. Four baseline APKs and all nine Mantis source files still match saved hashes.
