# Isolated Home Preview candidate

2026-11-11. User explicitly approved exactly one additional free public cloud build up to20minutes and, only after integrity/compatibility/signing checks pass, one fresh separate install/basic smoke up to20minutes at the user's Fire Stick, then manual remote handoff. This workflow performs NO device operation.

Source e3949da72a8cb1f53161f52b9df77207302772b6. Runtime Home/Controls unchanged from verified c82db03; only app/build.gradle.kts and app/src/mantis/AndroidManifest.xml change. Package dev.mantistv.tigris.homepreview, versionCode47, label MantisTV Home Preview. The previous dev.mantistv.tigris.experimental and all Mantis builds remain unchanged. No HOME role, system modifications, root, grants, uninstall or firmware changes. A package already present or any signer conflict is a stop condition; never remove it.

The new selected-app static artwork fallback and readable Controls status remain Implemented but not physically tested until an exact-device report exists. Existing real feeds take precedence. No fabricated media. Exact tvOS27 fidelity/Apple ecosystem/performance remain unverified. Ephemeral signing is for this isolated test; durable owner-controlled signing remains unresolved.
