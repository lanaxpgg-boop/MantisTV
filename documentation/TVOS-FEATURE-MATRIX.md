# tvOS feature matrix

Reference checked November 10, 2026: Apple's software-update page lists tvOS 27.2; its security-content page records release October 26, 2026. Sources: https://support.apple.com/en-us/106336 and https://support.apple.com/en-us/149524 . MantisTV is an original approximation, not Apple software.

| Feature | Preview implementation | Verification / limitation |
|---|---|---|
| Home / artwork | Native home and procedural landscape | Requires physical visual QA |
| Dock / favorites | Profile-local app favorites; six default apps when empty | Source implemented |
| Top Shelf | Local recent launches | No provider feed |
| Focus / animation | Android D-pad focus, scale/elevation, reduce motion | Hardware navigation untested |
| Library / launch | Public launcher intents, sorted app grid | Firmware package visibility untested |
| Search | App label/package filtering | Pure matching logic host-tested by build |
| Recent switcher | MantisTV-launched apps | No OS task snapshots or task killing |
| Controls | Network/display information, guarded settings/audio | Depends on firmware and HDMI |
| Settings | Appearance, profiles, device info, shortcuts | No protected system writes |
| Screensaver | Foreground five-minute idle procedural landscape | No aerial video or system replacement |
| Profiles / watchlist | Four local profiles, manual title lists | No provider account switching |
| Apple TV app | Generic installed-app discovery and launch | No special integration / DRM claims |
| Continue Watching | Not implemented | Provider permissions/contracts unknown |
| AirPlay / iPhone Remote / Siri | Not implemented | No Apple certification or private services |
| Photos / Music / smart home | Architecture backlog | Not implemented |

Compilation and automated evidence are attached to each actual successful release. No feature is physically tested yet.
