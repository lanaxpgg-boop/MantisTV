# Operating system foundation comparison

| Foundation | Practical benefit | Constraint / evidence | Decision |
|---|---|---|---|
| Stock Fire OS plus ordinary app | Retains vendor apps, DRM stack, decoding and normal recovery path | Foreground app cannot replace protected controls, system launcher or sleep | Selected for initial release |
| Launcher / accessibility / system integration | May provide deeper focus and button behavior | Firmware-dependent grants and broader access; not physically verified | Separate future opt-in review |
| Mantis custom Android / Lineage device tree | More system UI control in principle | Device tree is not proof of a safe ready image; installation requires prohibited bootloader/flash work and could alter DRM/audio support | Research only; no build or installation |

The baseline launcher was built as a reference, but its system-integration features are not automatically enabled on any device. Original MantisTV has no boot receiver, HOME intent or accessibility service. No bootloader exploit or physical-system modification is part of this implementation.

LOSMantis reference: https://github.com/LOSMantis/android_device_amazon_mantis . Source/device-tree existence alone does not establish compatible firmware, vendor blobs, security patch state, Widevine certification or recovery reliability. No unsupported official Fire OS major version is claimed for AFTMM.
