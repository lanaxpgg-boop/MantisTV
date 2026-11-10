#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
python3 scripts/check-resources.py
: "${RUNNER_TEMP:?Cloud runner required}"
SDK="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-/usr/local/lib/android/sdk}}"
mkdir -p out/lint releases
mvn -B -q -f tests/lint-pom.xml -Dmaven.repo.local="$RUNNER_TEMP/mantistv-maven" org.apache.maven.plugins:maven-dependency-plugin:3.6.1:copy-dependencies -DincludeScope=runtime -DoutputDirectory="$PWD/out/lint"
java -cp 'out/lint/*' com.android.tools.lint.Main --version
java -cp 'out/lint/*' com.android.tools.lint.Main --sdk-home "$SDK" --compile-sdk-version 25 --java-language-level 8 --sources app/src --resources app/res --classpath "$SDK/platforms/android-25/android.jar" --check NewApi,InlinedApi,MissingPermission,WrongThread,HandlerLeak,DrawAllocation,StaticFieldLeak -Werror --xml releases/lint.xml --text releases/lint.txt --exitcode app
python3 - <<'PY'
from pathlib import Path
import xml.etree.ElementTree as ET
issues=ET.parse('releases/lint.xml').getroot().findall('issue')
assert not issues, 'Selected Android lint checks must be warning/error free'
with Path('releases/BUILD-REPORT.txt').open('a') as report:
 report.write('Android lint 31.5.2: NewApi, InlinedApi, MissingPermission, WrongThread, HandlerLeak, DrawAllocation, StaticFieldLeak: 0 errors, 0 warnings. Other lint checks and hardware instrumentation not run.\n')
print('PASS: selected Android lint gate has zero issues')
PY
