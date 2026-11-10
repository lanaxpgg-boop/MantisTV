# Known issues — audited November 10, 2026

- No physical AFTMM installation, TV visual QA, D-pad/keyboard test, Android persistence/isolation test, hardware benchmark or soak test.
- API25 source compilation and seven selected lint checks passed for 0.4. A complete lint sweep, vendor API/service verification and real firmware compatibility are outstanding.
- Empty saved favorites repopulate the Home dock with default apps. Source-inferred; not physically reproduced.
- Reduced-motion screensaver has no periodic invalidate for its clock; the header clock is created on page construction. Time display can become stale absent other redraw/reconstruction. Source-inferred.
- Focus tags are package-based and can duplicate between Dock and Top Shelf; rebuilt pages do not preserve library filter/search input/scroll state. Source-inferred.
- Startup invokes synchronous discovery in onCreate and onResume. Icon executor queue/stale work is not explicitly bounded/canceled. Total PSS is not bounded by the 2MiB cache cap. No hardware regression/performance measurement exists.
- MantisTV is a foreground app, not default HOME/system UI. No boot receiver, accessibility service, global overlay, OS task snapshots/killing, app-driven sleep or system screensaver replacement.
- Top Shelf/recent views are MantisTV's local app-launch history. No content Continue Watching or provider feed. Search only matches installed app label/package.
- No wallpaper picker/aerial video library, full app manager, comprehensive recovery/backup, semantic categories or rich media catalog.
- Settings shortcuts depend on exported firmware targets; fixed HDMI audio may be controlled by the TV/receiver. No full audio/HDCP capability analysis.
- Four local profiles separate app data/preferences but are not authenticated OS/streaming accounts or parental controls.
- No Apple TV-specific adapter, AirPlay/iPhone Remote/Mac/Siri/Photos/Music/Find My/FaceTime/Home Assistant/Homebridge protocol integration. Generic launch of an installed app is not Apple certification or integration evidence.
- Every firmware record remains C; 76 records includes incremental edges and is not a complete official or physically tested firmware matrix.
- Signing keys are ephemeral development keys. No secure persistent production key/update continuity is configured. Do not auto-uninstall after a signature mismatch: that would delete local profiles and lists.

This audit publishes documentation and unchanged existing APKs. It does not fix application behavior or generate a new runtime release. Full statuses, source references and ten next tasks: ../PROJECT-PROGRESS.md.
