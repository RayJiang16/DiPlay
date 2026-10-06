# DiPlay 0.2.12 local-only fork

Based on carlito12345/DiPlay commit 8f53b27b3168aedb661e9f9bb7122ea344b75ada.

- Removed diagnostic cloud uploads, steering-profile uploads, their UI and background queue.
- Steering mappings remain locally saved and exportable. Geely compatibility is preserved.
- Key diagnostic lines are redacted before local export.
- Original package/version configuration is unchanged: release package `com.shihab.diplay`, version `0.2.12`, version code `31`.
- Original debug suffixes are unchanged: debug package `com.shihab.diplay.hudtest`, version `0.2.12-hud-test`.
- The original Android CI is restored. Pushes to `main` run checks and, when all six required Actions secrets are present, build a signed release APK and publish a GitHub Release.
- Same package name does not permit updates across different signing keys. Run only one CarPlay receiver at a time.

The release workflow uses four `ANDROID_*` signing secrets plus
`DIPLAY_AUTH_IDENTITY_BASE64` and `DIPLAY_AUTH_CERTIFICATE_BASE64` for the two
experimental CarPlay authentication assets. Neither signing keys nor authentication
assets belong in Git. Forks do not inherit these secrets from their parent repository.
The non-certified identity retains its iOS compatibility limitations. Android signing
keys are separate from CarPlay authentication keys. Automatic upstream synchronization
remains disabled so it cannot silently reintroduce removed upload functionality.

Run `python3 scripts/check_local_only.py` to check source, or pass an APK to check its DEX
for removed uploader names/endpoints and confirm authentication assets exist.
Local CarPlay, Wi-Fi and Bluetooth networking remain; this is not an offline-only app.
