# Known issues and limits

- No physical installation, D-pad usability test, rendering screenshot, memory benchmark or AFTMM performance validation yet.
- Source compilation targets the installed SDK 34 with manifest minimum/target 25. Complete Android lint/API-surface verification remains outstanding; SDK declarations alone are not compatibility evidence.
- Fire OS HOME interception is absent. MantisTV is launched as an ordinary TV app; Amazon remains the system foundation.
- Recent applications reflect MantisTV launches, not privileged OS task enumeration. No task killing or lifecycle control over other applications.
- Top Shelf contains local app history, not protected provider recommendations. Continue Watching is not implemented.
- Landscape artwork is procedural and original, not an aerial video library or Apple artwork. Glass is a lightweight translucent approximation; no real-time blur.
- Controls depend on each firmware's exported settings activities. HDMI audio may be fixed. Sleep uses the normal Fire TV remote only.
- Profiles are local convenience separation, not authentication, parental restrictions or streaming-account switching.
- Search covers installed applications only. No remote content indexes, microphone access or paid APIs.
- AirPlay, FairPlay content, Apple TV remote protocol, Siri, Photos, Music and smart-home pairing are not implemented.
- Development signing keys are ephemeral. A subsequent run may require a separately approved uninstall/reinstall, losing local application data.
- Idle screensaver operates only while MantisTV is in foreground, and cannot replace system sleep/screensaver behavior.
