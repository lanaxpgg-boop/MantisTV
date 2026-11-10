# MantisTV status

Native API25 development build passed compilation, host tests, manifest/ABI/ZIP policy, signing and alignment checks. The source commit actually compiled is recorded below; this subsequent checkpoint changes only documentation.

Source commit: ab022168a5d8fff173ff0169fd328adb2040be89
Package: dev.mantistv.home
Debug package: dev.mantistv.home.debug (debuggable; side-by-side test variant)
Version: 0.4.0-preview
Min SDK: 25; Target SDK: 25; compile SDK: 25
No native libraries: DEX-only, compatible in principle with armeabi-v7a
Compilation, host unit tests, manifest policy, zip alignment and signature verification: passed
Compiled directly against official Android API25 platform; lint is a separate pipeline stage
Installed or physically tested: NO
Signing key: ephemeral development key. Future builds may require uninstall/reinstall; back up local lists first.
Android lint 31.5.2: NewApi, InlinedApi, MissingPermission, WrongThread, HandlerLeak, DrawAllocation, StaticFieldLeak: 0 errors, 0 warnings. Other lint checks and hardware instrumentation not run.

Cloud run: https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42747779541

76 catalog records were captured previously; all remain C without hardware evidence. No firmware binaries were downloaded. No physical device was connected or changed. All SDKs/build caches remained in the cloud, with no paid runners or metered Actions artifact uploads.

Not complete: physical QA, real performance measurements, Android lint, persistent private release signing, provider Continue Watching, AirPlay/Apple Remote and other ecosystem integrations. See documentation/IMPLEMENTATION-BACKLOG.md and KNOWN-ISSUES.md.
