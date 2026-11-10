# Apple ecosystem

The official Apple TV app can appear in the library if already installed and exported through a launcher intent. MantisTV does not bundle, install, authenticate or modify it. App availability, region, Fire OS support and playback must be verified on hardware. Streaming and DRM stay inside each official app.

AirPlay research: mazer666/PhairPlay is a candidate only; do not reuse until exact license, API25, ARMv7 native libraries, discovery/pairing, update trust and network-exposure behavior are checked. No receiver, Apple certification or FairPlay support is claimed. Never bypass DRM.

Future remote architecture: optional isolated receiver component with user-initiated pairing, local-network-only access, random expiring tokens, rate limits, explicit revocation and no unauthenticated control. An ordinary custom remote should not claim compatibility with Apple's built-in iPhone Remote protocol. Browser remote/Shortcuts support requires an authenticated documented API; no server is currently implemented.

Photos/Music should initially use explicit user-initiated sharing into compatible installed apps, without full-library scraping or Apple private APIs. Home Assistant/Homebridge integration should remain optional and require user pairing. Mac connectivity and Apple ecosystem integration are not yet implemented or tested.
