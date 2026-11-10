# Physical device baseline — read-only capture

Captured 2026-11-10T14:14:53.252313+00:00. The latest user instruction authorized lightweight read-only preparation while freeing Mac storage. Installation and larger capture remain gated by the local reserve.

ADB connection: authorized (`get-state` returned device); exact endpoint retained locally and not intended for public publication. Target identity confirmed before further queries.

| Property | Observed value |
|---|---|
| ro.product.model | AFTMM |
| ro.product.device | mantis |
| ro.product.name | mantis |
| ro.build.display.id | NS6711 |
| ro.build.version.release | 7.1.2 |
| ro.build.version.sdk | 25 |
| ro.build.version.incremental | 0011644900484 |
| ro.build.fingerprint | Amazon/mantis/mantis:6.0/NS6711/5908N:user/amz-p,release-keys |
| ro.product.cpu.abi | armeabi-v7a |
| ro.product.cpu.abilist | armeabi-v7a,armeabi |

Device memory: MemTotal 1316676 kB; MemAvailable 293164 kB. These are baseline kernel counters, not MantisTV PSS or performance results.

Device /data storage:
```
Filesystem                                            1K-blocks    Used Available Use% Mounted on
/dev/block/platform/soc/11230000.mmc/by-name/userdata   5600160 2201304   3382472  40% /data
```

Package inventory entries: 230. Disabled package entries: 0. Exact inventories are local logs for comparison; do not publish unreviewed personal application inventory.

Foreground activity: mResumedActivity: ActivityRecord{2fc224a u0 com.amazon.tv.launcher/.ui.HomeActivity_vNext t555}
  mFocusedActivity: ActivityRecord{2fc224a u0 com.amazon.tv.launcher/.ui.HomeActivity_vNext t555}

Default HOME resolution: priority=950 preferredOrder=0 match=0x108000 specificIndex=-1 isDefault=true
com.amazon.tv.launcher/.ui.HomeActivity_vNext

Existing MantisTV package query: (none returned)

No package installation/update/uninstall, data clearing, launcher change, key event, app launch or system modification was performed. Physical app compatibility remains untested. Raw relevant baseline logs are in LOGS; no screen content, accounts or playback history was captured.

Additional directly observed firmware label: **Fire OS 6.7.1.1 (NS6711/5908)** (`ro.build.version.name`). `ro.build.version.fireos` reports `6.0`, identifying the major platform family; Android release separately reports 7.1.2/API 25.

Read-only display configuration: Physical size: 1920x1080; Physical density: 320. This is reported device geometry, not a measured 4K rendering result.

## Subsequent test result

The text above is the pre-install baseline. Following explicit temporary-resource authorization, installation and physical QA completed with limitations; see DEVICE-TEST-REPORT.md and FINAL-SAFETY-CHECK.json. MantisTV is now installed. Baseline inventory is preserved unchanged for comparison.
