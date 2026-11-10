# MantisTV status — audited November 10, 2026

**Milestone: native foreground TV application development preview; not a complete tvOS environment or replacement OS.** Priority 0 has a signed build checkpoint; physical acceptance remains outstanding. Priority 1 is partial; Apple protocols are not implemented and Priority 3 is preliminary research.

Authoritative feature audit: [PROJECT-PROGRESS.md](PROJECT-PROGRESS.md). Each of its 119 unique rows has exactly one status: 11 Completed and verified, 21 Implemented but not physically tested, 35 Partially implemented, 47 Not implemented, 5 Blocked. Counts are not effort-weighted completion percentages. No user-facing TV feature is marked completed/verified on compilation alone.

Latest application: 0.4.0-preview, versionCode4; dev.mantistv.home and separate dev.mantistv.home.debug. Min/target/compile SDK25, no native ABI payload. Audited pre-report HEAD: a1a51fe520363c2512eb14a79e4c17998c8ad0ad. Compiled source: ab022168a5d8fff173ff0169fd328adb2040be89. Original release/source target: 54619013a18b723559520e6d32241dc56909d7c7. The audit release's publication/source commit is recorded in AUDIT-PUBLICATION.json; no new application version or signing key is generated.

Verified evidence: existing CI compilation, one host-JVM data-rule test main, package/manifest/ZIP/ABI policy, alignment, development signature verification and seven selected Android lint checks at zero errors/warnings. Audit independently checked actual APK/source ZIP hashes and ZIP structure; fresh cloud signature/manifest and uploaded-asset verification accompany the report release. No full lint sweep, Android UI/instrumentation, persistence/isolation integration test, device screenshot, physical installation or hardware performance/soak result exists.

Firmware: 41 full plus 35 incremental catalog records; all C. They are not 76 distinct tested or verified-compatible firmware releases. Per-build package/service/permission/launcher behavior and complete official history remain unverified. API25 source/DEX packaging evidence is not hardware proof.

Implemented source: native Home/artwork, generic app library/launching, local recent history, app text search, profile-local preferences/lists, manual watchlist, guarded foreground controls/settings and original idle screensaver. Missing: real content shelves/Continue Watching, full media/app/system management, aerial library, Apple TV-specific integration, AirPlay/iPhone Remote/Mac/Siri/Photos/Music/smart-home protocols, persistent production signing and OS replacement. See all statuses and the next ten tasks in PROJECT-PROGRESS.

Source-level issues awaiting regression tests include empty favorites repopulating Home, reduced-motion screensaver clock refresh, incomplete focus/search/filter restoration and unmeasured startup/icon-queue work. They were inferred by code inspection, not physically reproduced. The audit changes documentation only.

The next published prerelease packages this report with the unchanged verified 0.4 APKs, checksums, fresh source ZIP and provenance/verification metadata. Original APKs: preview SHA256 6cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4; debug SHA256 447a8a7e72a3fa1965c99f41b3d0e3f8f1e591831266259198ede41b1a360c1e. Existing release: https://github.com/lanaxpgg-boop/MantisTV/releases/tag/quality-42747779541 .

No device changes or local SDK/build cache installations. Local builds remain disabled by the Mac storage reserve; free public cloud verification only. Hardware testing needs separate supervised authorization. Ephemeral development keys prevent a claim of update-stable production signing; do not auto-uninstall to resolve a future mismatch, since local data would be erased.
