# Hardware performance report — bounded AFTMM observations

Device: AFTMM/mantis; Fire OS 6.7.1.1(NS6711/5908), API 25. APK0.4.0-preview. About24 minutes of mixed inspection, idle and interaction, including a real five-minute screensaver timeout and one deliberate process restart. These are observations, not a sustained benchmark or optimization claim.

| Measurement | Observed value | Interpretation |
|---|---|---|
| First launch am start -W | ThisTime/TotalTime 840 ms; WaitTime 871 ms | Single launch with no prior MantisTV PID; no repeated distribution |
| Existing task reopen |175 ms activity /203 ms wait | One Home-return reopen |
| Process restart |737 ms activity /758 ms wait | One deliberate force-stop/relaunch |
| Initial PSS |38,480 KiB /37.58 MiB | Snapshot, not peak or heap cap |
| Post-interaction PSS |73,933 KiB /72.20 MiB | Snapshot before restart, not proof of a leak |
| Final PSS |47,921 KiB /46.80 MiB | After restart and more interaction; not a comparable peak measurement |
| Android gfxinfo |2,556 frames;82 janky(3.21%);p50 5 ms,p90 6 ms,p95 9 ms,p99 38 ms | Mixed session cumulative renderer counters; not achieved FPS or screen-to-input latency |
| App CPU, dumpsys cpuinfo |3.9%(3.0user+0.9kernel) | System report window15:45:12.340–15:45:19.884; not sustained/idle CPU |
| Baseline aggregate CPU |10.51% over~1.138 seconds | Whole device before install, not app CPU |
| Device /data available |3,382,472→3,370,468 KiB | Whole-device decrease12,004 KiB; concurrent system activity prevents assigning all growth to MantisTV |
| Logical display /reported refresh |1920×1080 /59.94Hz | Raw screenshots3840×2160; capture size does not prove native4K app rendering |
| Final Mac free /QA footprint |~28.3 GiB /18.6 MiB | Within temporary 15 GiB floor and 100 MiB output cap |

Raw before/after meminfo, gfxinfo, cpuinfo, storage and kernel counters are private LOGS files. LOCAL-EVIDENCE-INDEX.json records hashes. No GPU utilization, thermal/throttling, peak memory, repeated startup percentiles, media decode/DRM/HDMI audio quality, power, long soak or all-firmware measurement exists. The API 25 app executes on this ARMv7 device; that does not prove every protocol, display mode or firmware works.

Next measurements: repeat cold/warm trials; deterministic focus/scroll scenarios with input latency; separate idle/screensaver/interaction CPU; GC/peak PSS trends; sustained frame pacing; stock media return; thermal/long-session/storage checks. Proposed engineering budgets must be labelled targets until measured. No local build tools or caches were installed.
