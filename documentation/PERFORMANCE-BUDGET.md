# Performance budget

These are design constraints and future acceptance targets, not measured results: no always-on service; one icon worker; 2 MiB icon cache; normalized 96px icons; no remote artwork cache; no background networking; 140ms focus animation with reduced-motion fallback; screensaver invalidation limited to 10Hz and foreground operation; no full-screen blur; no native dependencies or emulators.

Hardware targets: 1.5 GiB RAM / IMG GE8300 / 32-bit ARM / API 25. Measure actual startup, PSS, GC, focus/frame times and return-from-streaming behavior on AFTMM before changing these budgets. 4K output does not imply that every visual should be rendered into a 4K bitmap.

Use a separately authorized supervised session to capture dumpsys meminfo dev.mantistv.home, gfxinfo and launch timing, avoiding credentials or private viewing history in shared logs. No benchmark numbers are claimed.
