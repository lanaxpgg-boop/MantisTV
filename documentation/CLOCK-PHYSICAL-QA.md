# MantisTV 0.4.1 clock-fix physical regression — November 10,2026

**Both reproduced clock defects are fixed and verified in the separately installed 0.4.1 debug candidate on the exact AFTMM.** This does not make the existing 0.4 preview updated, resolve persistent signing, or establish tvOS design acceptance.

## Identity and cloud gates

AFTMM/mantis, Fire OS6.7.1.1(NS6711/5908), Android7.1.2/API25, armeabi-v7a. Candidate package:dev.mantistv.home.debug, version0.4.1-preview/code5. Original dev.mantistv.home0.4 remains installed with its original APK and saved watchlist fixture.

Compiled source:b6f7a673a01619aca100adbcc4abcf97cbf3ab80. [Cloud build](https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42842633249), [candidate release](https://github.com/lanaxpgg-boop/MantisTV/releases/tag/clock-candidate-42842633249). API25 compilation, existing host rule tests, seven selected zero-issue lint rules, both APK signature/alignment/manifest/ABI/ZIP policy gates passed. No new host unit test claims clock behavior; actual regressions below cover it.

Debug APK SHA256:f9ee4f4641b8127163134a6c0bbfb254cfbecdfdd07515955042ee8707f7d594. Certificate SHA256:727cce3d86ac784afd8a663fe341916d2029fe55dcbbaa2dc3646279b16f3361. All uploaded candidate asset digests/checksums verified; source archive runtime matched local source; existing local apksigner verified the downloaded APK at API25 floor, plus packaged version/SDK/debug policy and alignment. Normal install returned Success in1.179seconds; no replace/uninstall/data-clear operation. First launch Status:ok, activity1260ms/wait1294ms.

## Source changes

The header uses native Android TextClock with the existing HH:mm format, size and padding. Fullscreen landscape schedules reduced-motion redraw at the next real minute boundary; scenery remains motionless, timezone is refreshed when the displayed minute changes, and clock text is exposed as an accessibility description. Normal-motion100ms redraw requests remain. Version5/0.4.1 metadata and artifact inspection were updated consistently.

## Actual physical checks

| Check | Result and evidence |
|---|---|
| Live header |16:20→16:21, matching device wall time, with no key input/page rebuild during27second boundary wait. HEADER-CLOCK-RESULT.json; header-regression-before/after XML; screenshots002/003 |
| Reduced-motion clock |16:21→16:22, matching device wall time, no key/page rebuild during11second boundary wait. REDUCED-CLOCK-RESULT.json; clock accessibility description, screenshots004/005. Visually inspected: same scenery, advanced clock |
| Preference restore | Reduce motion verified off after test; candidate returned Home |
| Pause/resume | Home key returned Amazon launcher; explicit candidate reopen177ms activity/193ms wait; header matched wall time afterward |
| Back escape | Double Back from candidate Home returned original Amazon Home |
| Original app/data | Original 0.4 APK hash still6cc7dfe086518130770c7ceae6397d3ba1fd1dc8f1bd6a9efc00b2f5b12e30e4; prior MantisTV_QA_20261110 watchlist fixture rendered unchanged in original app. No original data edited |
| Final state | Amazon Home foreground/default; zero disabled packages; both apps retained. Candidate process deliberately stopped after QA, preserving installation/data |

The first locator assertion expected accessibility class android.widget.TextClock, but Android exposed android.widget.TextView. That assertion stopped before any clock wait. The corrected test selected the visible HH:mm text and checked real behavior; no app code/APK changed in response to this test locator correction.

## Performance and log limits

Candidate final PSS41,592KiB(40.62MiB). Mixed gfxinfo:357frames,17janky(4.76%);p50 5ms,p90 9ms,p95 16ms,p99 57ms. These are a single debug-session snapshot and cumulative counters; they cannot be compared as a measured speedup against the previous different0.4 workload. App-specific cpuinfo line was absent in this snapshot, so no0%CPU claim is made. No observed candidate FATAL EXCEPTION/ANR/OutOfMemoryError in bounded captured relevant logs; crash buffer was empty. Capture may miss/wrap entries. About5minutes from install to final checks is not long-term/thermal acceptance.

Final Mac free~43.7GiB, above normal34.24GiB reserve. New QA output~4.9MiB, combined with earlier~18.6MiB below100MiB. Existing tools only; no local builds, SDK/cache downloads or paid service. No firmware/root/DNS/OTA/default-launcher/system-package changes, uninstall or data clear.

## Remaining acceptance

0.4.1 still uses the seven-tab/generic-grid scaffold and does not meet the maximum feasible1:1 latest-tvOS product target. Genuine content shelves/media interfaces, measured screen-by-screen typography/layout/focus/motion, Apple protocols and family-wide firmware validation remain open. Only these clock fixes, local preference restore and return paths were retested; the earlier broad0.4 smoke suite is not silently credited to0.4.1.

Signing remains ephemeral; this candidate is not a data-preserving in-place update for original 0.4, and later clean builds are not guaranteed to update this debug installation. Never uninstall/clear an app to bypass a mismatch. Stable private signing and an explicit migration path remain required. Next UI engineering slice: versioned latest-tvOS references and measured Home/Top Shelf reconstruction, retaining tested catalog/store/search behavior.
