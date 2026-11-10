#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
python3 scripts/check-resources.py
SDK="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-/usr/local/lib/android/sdk}}"
BT="$SDK/build-tools/34.0.0"
JAR="$SDK/platforms/android-34/android.jar"
test -f "$JAR" && test -x "$BT/aapt2"
mkdir -p out/gen out/classes out/dex out/tests releases
javac -proc:none --release 8 -d out/tests app/src/dev/mantistv/home/StoreRules.java tests/StoreRulesTest.java
java -cp out/tests StoreRulesTest | tee out/unit-tests.txt
"$BT/aapt2" compile --dir app/res -o out/resources.zip
"$BT/aapt2" link -o out/unsigned.apk -I "$JAR" --manifest app/AndroidManifest.xml --java out/gen --min-sdk-version 25 --target-sdk-version 25 out/resources.zip
find app/src out/gen -name '*.java' > out/sources.txt
javac -proc:none -encoding UTF-8 --release 8 -Xlint:deprecation -classpath "$JAR" -d out/classes @out/sources.txt 2> out/javac-diagnostics.txt
find out/classes -name '*.class' > out/classes.txt
"$BT/d8" --release --min-api 25 --lib "$JAR" --output out/dex @out/classes.txt
(cd out/dex && zip -q ../unsigned.apk classes.dex)
"$BT/zipalign" -f 4 out/unsigned.apk out/aligned.apk
if [ ! -f out/development.keystore ]; then
 keytool -genkeypair -keystore out/development.keystore -storepass android -keypass android -alias mantistv -keyalg RSA -keysize 2048 -validity 365 -dname 'CN=MantisTV preview development'
fi
"$BT/apksigner" sign --ks out/development.keystore --ks-pass pass:android --key-pass pass:android --ks-key-alias mantistv --out releases/MantisTV-0.1.0-preview.apk out/aligned.apk
"$BT/apksigner" verify --min-sdk-version 25 --verbose --print-certs releases/MantisTV-0.1.0-preview.apk > releases/signature.txt
"$BT/aapt" dump badging releases/MantisTV-0.1.0-preview.apk > releases/manifest.txt
"$BT/zipalign" -c 4 releases/MantisTV-0.1.0-preview.apk
python3 scripts/inspect-apk.py
cp out/unit-tests.txt out/javac-diagnostics.txt releases/
{ echo "Source commit: $(git rev-parse HEAD)"; echo 'Package: dev.mantistv.home'; echo 'Version: 0.1.0-preview'; echo 'Min SDK: 25; Target SDK: 25; compile SDK: 34'; echo 'No native libraries: DEX-only, compatible in principle with armeabi-v7a'; echo 'Compilation, host unit tests, manifest policy, zip alignment and signature verification: passed'; echo 'Android lint and complete API-surface analysis: NOT RUN'; echo 'Installed or physically tested: NO'; echo 'Signing key: ephemeral development key. Future builds may require uninstall/reinstall; back up local lists first.'; } > releases/BUILD-REPORT.txt
python3 scripts/check-resources.py
