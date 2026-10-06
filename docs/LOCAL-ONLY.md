# DiPlay 0.2.12 local-only fork

Based on carlito12345/DiPlay commit 8f53b27b3168aedb661e9f9bb7122ea344b75ada.

- Removed diagnostic cloud uploads, steering-profile uploads, their UI and background queue.
- Steering mappings remain locally saved and exportable. Geely compatibility is preserved.
- Key diagnostic lines are redacted before local export.
- Original package/version configuration is unchanged: release package `com.shihab.diplay`, version `0.2.12`, version code `31`.
- Original debug suffixes are unchanged: debug package `com.shihab.diplay.hudtest`, version `0.2.12-hud-test`.
- The current workflow builds a debug-signed standalone test APK, not a release APK. No private release signing key was inherited or generated for this fork.
- Same package name does not permit updates across different signing keys. Run only one CarPlay receiver at a time.

The manual local-only workflow tests and builds this source and publishes an Actions artifact.
It verifies the reference APK SHA-256 before extracting only its two experimental CarPlay
authentication assets, never executable code. These assets remain outside Git. This retains
the original non-certified identity and its iOS compatibility limitations. Android signing
keys are separate and are not extracted. No release or authentication secrets are required.

Run `python3 scripts/check_local_only.py` to check source, or pass an APK to check its DEX
for removed uploader names/endpoints and confirm authentication assets exist.
Local CarPlay, Wi-Fi and Bluetooth networking remain; this is not an offline-only app.
