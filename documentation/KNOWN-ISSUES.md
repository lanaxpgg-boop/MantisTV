# Known issues and limits

- No physical installation, D-pad usability test, rendering screenshot, memory benchmark or AFTMM performance validation yet.
- Version 0.2 uses the official API25 platform for direct source compilation when its quality workflow succeeds. Android lint, vendor API behavior and physical testing remain outstanding.
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
