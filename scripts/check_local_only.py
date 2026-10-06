#!/usr/bin/env python3
"""Regression gate: no cloud uploader in application source or APK."""
from pathlib import Path
import sys
import zipfile

ROOT = Path(__file__).resolve().parents[1]
FORBIDDEN = (b"carlito.i234.me", b"diplay-profiles/v1/", b"DiagnosticReportUpload",
             b"SteeringProfileUploadService")
for module in ("common", "shared", "mobile"):
    for path in (ROOT / module / "src/main").rglob("*"):
        if path.is_file():
            data = path.read_bytes()
            assert not any(value in data for value in FORBIDDEN), str(path)
if len(sys.argv) > 1:
    with zipfile.ZipFile(sys.argv[1]) as apk:
        for name in apk.namelist():
            if name.endswith(".dex"):
                assert not any(value in apk.read(name) for value in FORBIDDEN), name
        for name in ("assets/offline-mfi/identity.pk8", "assets/offline-mfi/certificate.p7b"):
            assert apk.getinfo(name).file_size > 0, name
print("Local-only source/APK checks passed")
