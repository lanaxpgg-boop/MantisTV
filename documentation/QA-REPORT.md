# QA report

The native build script runs host-JVM tests for bounded/deduplicated recent history, immutable inputs and normalized search. It compiles Java, builds DEX/resources, aligns/signs the APK, verifies its signature for SDK25, inspects SDK/package badging, and checks manifest permission policy and absence of native libraries. Successful result files are published only when these checks pass.

Not performed: Android instrumentation, complete Android lint/API-surface analysis, screenshots from the running app, emulator or physical AFTMM installation/testing, firmware-specific service/package validation and measured performance. Code review is not equivalent to those tests.

The upstream baseline cloud run succeeded: https://github.com/lanaxpgg-boop/MantisTV/actions/runs/42744518215 . Native results must be read from the actual native workflow and its release BUILD-REPORT.txt, signature.txt, manifest.txt, unit-tests.txt and javac-diagnostics.txt; source preparation alone is not a passed build.

Version 0.2 compiled directly against official API25 and passed tests/signature/manifest checks in run 42746145356. That run also parsed 76 full/incremental firmware catalog records, all classified C. Version 0.3 adds profile bounds tests and a separately debuggable package; inspect its actual release reports for outcomes. Module extraction and Java formatting must preserve compilation; no visual or device QA is inferred.
