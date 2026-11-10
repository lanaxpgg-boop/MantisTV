# Current checkpoint —0.4.1 clock fixes physically verified

The two clock defects are fixed and passed real-minute regressions in the separate dev.mantistv.home.debug0.4.1/code5 candidate on AFTMM/mantis, Fire OS6.7.1.1(NS6711/5908), Android7.1.2/API25/ARMv7. Header16:20→16:21; reduced-motion screensaver16:21→16:22, with no input/page rebuild during each boundary wait. Preference restored off; pause/resume and Home/double-Back escape passed.

Source:b6f7a673a01619aca100adbcc4abcf97cbf3ab80. Cloud run42842633249 passed API25 compilation, existing host rules, seven selected lint rules and APK signature/manifest/ABI/ZIP/alignment checks. Downloaded debug APK hash/signature/source verified independently. Candidate release:https://github.com/lanaxpgg-boop/MantisTV/releases/tag/clock-candidate-42842633249.

Original0.4 dev.mantistv.home remains installed: exact APK SHA256 and prior watchlist fixture both verified unchanged. Candidate retained but its process stopped after QA; Amazon Home foreground/default, zero disabled packages. No uninstall/data clear/firmware/root/DNS/OTA/launcher mapping change. Signing remains ephemeral; no safe in-place update path is established. Never bypass a mismatch by uninstalling.

Candidate final PSS40.62MiB;357frames/4.76% cumulative jank in this different debug workload, not proof of an optimization. No observed fatal/ANR/OOM in bounded captured relevant logs; no long/thermal soak. Normal Mac reserve restored(~43.7GiB free); combined QA below100MiB. Cloud-only builds, existing QA tools, no SDK/cache downloads or paid services.

Definitive target remains maximum feasible1:1 latest tvOS.0.4.1 does not redesign the seven-tab scaffold, deliver media/Apple integration or prove all-firmware compatibility. All119feature statuses/counts remain unchanged:22narrow completed/verified,4implemented but not physically tested,45partial,46not implemented,2blocked. See documentation/CLOCK-PHYSICAL-QA.md, TVOS-DESIGN-SPEC.md and PROJECT-PROGRESS.md. Next:stable signing/migration, versioned references and measured Home/Top Shelf/focus reconstruction.
