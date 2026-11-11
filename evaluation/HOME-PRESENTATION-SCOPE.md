Third/final approved free cloud build for Search refinement 1ea2dc4748e5eb622137bd51ae1aeb90f1191c1b. No install/device command. Following scope is pre-build; use RESULT.json and APK-VERIFICATION.json for results.

# Search corrective batch

2026-11-11. Source51a9e71/run43032388271 passed714JVM executions(238per flavor),75host tests, phone/shell contracts and independent API25 APK/250source/manifest/license gates. APK5e4b6406e7f5edb2b673de58954fb7d6d5e482433e8bf772b9e58069ecbb5ebe, code48; no physical execution. Both lints0errors/81warnings/0NewApi. New warnings are PluralsCandidate(counted English applications) and DiscouragedApi(fixed Search orientation on Android16). Raw reports retained.

Correction: proper English/Chinese quantity resources; Search uses getQuantityString. Remove only Search orientation lock; TV orientation remains device-owned. Query field uses native hint/text accessibility. D-pad Down from query reaches first result, and a partial last grid row is reached before Cancel from any column above it. One new pure navigation test method; existing match tests unchanged. Same AppRepo/TileAdapter/focus/launch owners, no new permission/service/dependency/package. Mantis code49, no install or key setup.

Third and last approved free20minute build verifies the combined correction, all3flavors/both lints and API25 APK/source/manifest/license gates. Current new code is uncompiled/unphysical until that run returns. Do not transfer code48 success to code49. User testing is not required for this intermediate engineering check; installed e3949da preview stays unchanged. No more cloud builds after the third dispatch without a fresh allowance.
