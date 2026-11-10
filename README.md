# MantisTV

An independent native Android TV home experience for Amazon Fire TV Stick 4K (2018 / AFTMM). This is an early application preview, not a replacement operating system. No physical Fire TV installation or testing has been performed.

## Preview functionality

Home with original procedural landscape artwork; a favorites dock; local recent-launch Top Shelf; app library; package/name search; guarded Control Center shortcuts; local profiles and title watchlists; two appearance palettes; reduce-motion preference; five-minute idle landscape screensaver.

Use the D-pad and OK. Hold OK on an app for favorites and app information. Menu opens Controls. Back returns Home; press Back twice from Home to exit. Profiles store favorites, recent launches and watchlists separately. Profiles are not parental controls.

## Build and download

Cloud workflows use standard Ubuntu GitHub Actions runners in this public repository. No Actions caches or metered artifact uploads are used. Outputs are published as development prerelease assets under Releases. All SDK tools stay in the cloud. Run scripts/build.sh only in a suitably provisioned cloud checkout; it expects existing Android build-tools 34.0.0, platform 25 and a JDK.

The APK has package dev.mantistv.home, minimum/target SDK 25 and no native libraries. Its source uses native Android APIs and no runtime third-party dependencies. It is development-signed with an ephemeral key; repeated cloud builds are not guaranteed to update an existing installation. It does not register as a HOME replacement or start at boot.

Read documentation/INSTALLATION-GUIDE.md and documentation/ROLLBACK-GUIDE.md before any supervised test. No automatic installation, ADB connection, system-package changes or firmware changes are part of the build.

Version 0.4 retains library filters and tagged focus restoration, separates app discovery and scene rendering, and adds a separately packaged debug APK. Appearance and reduce-motion preferences are local to each profile. The quality workflow compiles against official Android API25; its actual result is recorded in release reports.

## Evidence

The untouched upstream reference techgenius1/tvOS-Launcher built at commit 463fa6c2a9b7322c4407f1d00db935ef09d20f4f in a separate baseline run. MantisTV app source is original; no upstream Java, artwork, fonts or third-party libraries are incorporated. The upstream reference keeps its own Apache-2.0 and component license notices in the baseline source release.

Tests cover local history/search logic and manifest/APK policy. Compilation or SDK metadata is not proof of firmware compatibility or acceptable hardware performance. AirPlay, Apple Remote pairing, provider Continue Watching and Apple certification are not implemented.

The 0.4 quality pipeline also gates seven Android lint checks at zero warnings/errors and preallocates landscape geometry. See release lint.xml/lint.txt for actual scope and outcomes. This is not physical performance validation.
