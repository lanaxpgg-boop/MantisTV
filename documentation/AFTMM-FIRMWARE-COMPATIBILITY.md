# AFTMM firmware compatibility

Platform scope: Fire OS 6 / Android API 25 / 32-bit ARM only. Official device-family specifications do not prove per-build application compatibility.

Evidence labels: A = physically tested AFTMM; B = compatibility established by verified API/source analysis for that build; C = unknown/experimental. All builds remain C for this preview; no physical or complete firmware-specific API/package analysis has been performed.

FTVDB currently lists 41 full records and 35 incremental records. The catalog begins after the hardware's original release and is not proof of every officially shipped firmware. Incremental source builds can expose identifiers absent from the full list. Recorded catalog dates are not guaranteed Amazon release dates. Source URLs:

- https://ftvdb.com/firetv/firmware/com.amazon.mantis.android.os/
- https://ftvdb.com/firetv/firmware/com.amazon.mantis.android.os/diff/
- https://digprjsurvey.amazon.com/csad/help/node/G201497590

The optional cloud catalog script writes a row-level inventory and retains source links without downloading firmware. Package differences, launcher behavior, permission grants and system services are unknown unless independently inspected. Do not relabel unknown values as identical across builds. Matrix completion, missing launch-era versions and package-level diffs remain open work.
