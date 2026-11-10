# API25 build refinement

The API25 quality workflow installs only the official Android platform-25 SDK package in the cloud using sdkmanager. It does not pipe acceptance into a license prompt; if the runner's existing licenses do not permit installation, the job stops. No SDK is placed on the Mac.

The native Java source is compiled directly against platform-25/android.jar, and resource linking and DEX generation also use that platform. This catches source references to Android APIs introduced after API25, beyond minimum-SDK manifest metadata. It does not establish Fire OS vendor behavior or hardware performance. Android lint and instrumentation still remain outstanding.

Version 0.2.0-preview adds focus restoration by navigation/app tag when returning from another app, plus All apps/Favorites/Recently opened library filters. Actual compilation, tests and signature results are in the successful quality run and its release assets. Its development signing key is ephemeral and differs from earlier preview runs.
