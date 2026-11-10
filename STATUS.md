# Tigris audit checkpoint — bounded experiment saved

2026-11-10: recommended direction is **Option A conditionally**: Tigris as the proposed primary UI/runtime after API25, AFTMM, fidelity and performance gates. Mantis remains the validated fallback; no primary-codebase switch or app-source overwrite occurred.

Separate Tigris2.0.27 worktree/branch preserves upstreamMIT and all third-party notices. Official APK is min28, not installable on API25. Experimental min25 flavor compiled/assembled in free cloud run42888758342;226 JVM tests,75 Python tests and mocked shell contracts passed. Full lint failed9errors/79warnings including3API26 findings. All9 fixes are saved but unbuilt/unverified; the two-build experiment limit is reached. No experimental APK was published or installed. Artifact signing/integrity, runtime ARMv7, hardware performance and exact tvOS27 acceptance remain pending. See the five documentation/TIGRIS reports and TIGRIS-EVALUATION-RESULT.json in the experimental worktree.

Existing Mantis0.4 APK/source,0.4.1 verified clock fix and0.5 Home preview remain preserved. Mantis's119-row audit counts/statuses are unchanged. Current Mantis0.5 source remains9ed29360cf61dead8dc31defb7d5ae0f813c8567; physical navigation is unfinished because the device connection failed on resume. No further device interaction occurred.

[Experimental source](https://github.com/lanaxpgg-boop/MantisTV/tree/experiment/tigris-api25-42888758342) · [Diagnostics](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/tigris-api25-42888758342).

## Historical checkpoints

# Resumed — Home device QA blocked; Tigris evaluation authorized

The user explicitly resumed the project. Normal Mac storage reserve passed (~44.47GiB free against34.24GiB), memory free40%. ADB get-state failed before any later device command; device interaction stopped.0.5 Home navigation remains unfinished. The user then requested a separate Tigris v2.0.27 source/compatibility evaluation and possible bounded API25 experiment. Existing MantisTV source, APKs and QA checkpoints are preserved; experimental work is separate. No Tigris APK installation is authorized before integrity/signing/compatibility gates.

---

## Previous checkpoint

# Paused — user reached usage limit

PAUSED — WAITING FOR USER AUTHORIZATION

The user explicitly requested an immediate stop after reaching a usage limit. No further development, cloud runs or device testing may occur. Resume only on an explicit user continuation request.

Last completed development milestone:0.5.0-preview/code6 Home composition built in free cloud run42849990172, source9ed29360cf61dead8dc31defb7d5ae0f813c8567; release https://github.com/lanaxpgg-boop/MantisTV/releases/tag/home-preview-42849990172. All candidate asset digests/checksums, source/runtime match and local signature/manifest/alignment checks passed. Design APK SHA25679174eee05449df61d2a5ac4faa590ad8c4f221bf4d806478ce0baed674d3985, certificatec2d2edfc13471c671f5618fef59ff3448e94c3a72d0f2d926e137a4c5b7b4522.

Verified design package dev.mantistv.home.design installed normally alongside both earlier apps. First launch Status:ok,1193ms activity/1250ms wait. Screenshot001 visually inspected: six lower app tiles, installed-app banner, live clock/profile badge; old Home greeting/page tabs removed. Six aligned lower targets and no legacy Home tab buttons were observed. Normal baseline Amazon Home and both older APK hashes were checked before this installation.

Navigation regression is unfinished. Two attempted batches stopped at initial-focus assertions after observed focus changed. The user confirmed using the physical remote, so these observations cannot be attributed to an autonomous app focus defect. The latest observed focus was Open Appstore within the Home Preview. Do not assume the app/device foreground is unchanged on resume. No external app launch or completed navigation/return/profile/performance/log regression is credited for0.5.0. No final safety comparison or final report publication was completed for this candidate.

The user reiterated the exact tvOS27 look. Current preview is only a first structural pass and FAILS final visual acceptance: typography, contextual launcher prose, app-logo backdrop, provider artwork/content, focus/motion measurement and supporting screen designs remain incomplete. Do not call it an exact tvOS27 recreation. Ecosystem functionality remains planned, not implemented.119-feature status counts must not be increased from this build or partial check.

All source, screenshots, private logs, preliminary navigation results and helper scripts are retained locally. Raw device files stay excluded from public publication. Original0.4 and0.4.1 apps/data have not been replaced or cleared. No root/flash/default-launcher/system-package/network-setting change was made. No additional device command was issued to clean up or change the display after the stop request. Candidate installation remains; signing is ephemeral, so never uninstall/clear it to bypass a future certificate mismatch.

Resume priorities:confirm user authorization/resource gates; inspect actual device state; establish a controlled navigation start state; complete or correct Home regression evidence; document exact visual deviations; solve safe signing before further same-package iterations; continue reference-based refinement. Keep all builds free/cloud-only. Preserve current commits, APKs and QA evidence.

---

## Previous checkpoint

# Current checkpoint —0.4.1 clock fixes physically verified

The two clock defects are fixed and passed real-minute regressions in the separate dev.mantistv.home.debug0.4.1/code5 candidate on AFTMM/mantis, Fire OS6.7.1.1(NS6711/5908), Android7.1.2/API25/ARMv7. Header16:20→16:21; reduced-motion screensaver16:21→16:22, with no input/page rebuild during each boundary wait. Preference restored off; pause/resume and Home/double-Back escape passed.

Source:b6f7a673a01619aca100adbcc4abcf97cbf3ab80. Cloud run42842633249 passed API25 compilation, existing host rules, seven selected lint rules and APK signature/manifest/ABI/ZIP/alignment checks. Downloaded debug APK hash/signature/source verified independently. Candidate release:https://github.com/lanaxpgg-boop/MantisTV/releases/tag/clock-candidate-42842633249.

Original0.4 dev.mantistv.home remains installed: exact APK SHA256 and prior watchlist fixture both verified unchanged. Candidate retained but its process stopped after QA; Amazon Home foreground/default, zero disabled packages. No uninstall/data clear/firmware/root/DNS/OTA/launcher mapping change. Signing remains ephemeral; no safe in-place update path is established. Never bypass a mismatch by uninstalling.

Candidate final PSS40.62MiB;357frames/4.76% cumulative jank in this different debug workload, not proof of an optimization. No observed fatal/ANR/OOM in bounded captured relevant logs; no long/thermal soak. Normal Mac reserve restored(~43.7GiB free); combined QA below100MiB. Cloud-only builds, existing QA tools, no SDK/cache downloads or paid services.

Definitive target remains maximum feasible1:1 latest tvOS.0.4.1 does not redesign the seven-tab scaffold, deliver media/Apple integration or prove all-firmware compatibility. All119feature statuses/counts remain unchanged:22narrow completed/verified,4implemented but not physically tested,45partial,46not implemented,2blocked. See documentation/CLOCK-PHYSICAL-QA.md, TVOS-DESIGN-SPEC.md and PROJECT-PROGRESS.md. Next:stable signing/migration, versioned references and measured Home/Top Shelf/focus reconstruction.

## User-confirmed final product direction

Latest-tvOS design fidelity and an optional MacBook/iPhone ecosystem connection are both explicit acceptance requirements. APPLE-ECOSYSTEM-PLAN.md records a source-reviewed free AirPlay receiver candidate (declared minSDK23/ARMv7, not yet built or tested for MantisTV) and separate companion/native Apple integration gates. Existing119-feature statuses and percentages are unchanged.

## 0.5.0 Home source candidate — build/physical gates pending

The first Home composition replaces the Home page tabs/greeting panel with a full-bleed installed-app backdrop, lower six-column app row, focused metadata/Open action and real utility tiles. It retains working catalog/launch/profile/list logic and clock fixes. Supporting pages and genuine content/Apple protocol integration remain incomplete.0.5.0/code6 uses separate dev.mantistv.home.design for isolated QA; do not replace or clear either tested baseline. See documentation/HOME-PREVIEW-PLAN.md. No visual acceptance is inferred from source alone.
