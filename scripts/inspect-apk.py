import zipfile, xml.etree.ElementTree as ET
from pathlib import Path
ns='{http://schemas.android.com/apk/res/android}'
r=ET.parse('app/AndroidManifest.xml').getroot()
assert r.attrib['package']=='dev.mantistv.home'
assert r.find('uses-sdk').attrib[ns+'minSdkVersion']=='25'
assert {x.attrib[ns+'name'] for x in r.findall('uses-permission')}=={'android.permission.ACCESS_NETWORK_STATE'}
assert not r.findall('.//service') and not r.findall('.//receiver')
assert not any(x.attrib.get(ns+'name')== 'android.intent.category.HOME' for x in r.findall('.//category'))
p=Path('releases/MantisTV-0.4.0-preview.apk')
with zipfile.ZipFile(p) as z:
 assert 'classes.dex' in z.namelist()
 assert not any(n.startswith('lib/') for n in z.namelist()), 'Unexpected native ABI dependency'
 assert not any(n.endswith(('.keystore','.jks')) for n in z.namelist())
 assert z.testzip() is None
badging=Path('releases/manifest.txt').read_text()
assert "sdkVersion:'25'" in badging and "targetSdkVersion:'25'" in badging
assert "package: name='dev.mantistv.home'" in badging
print('PASS: permission policy, non-HOME manifest, no services/receivers, SDK 25 metadata, DEX-only ABI, ZIP integrity')

with zipfile.ZipFile('releases/MantisTV-0.4.0-debug.apk') as z:
 assert 'classes.dex' in z.namelist() and z.testzip() is None
 assert not any(n.startswith('lib/') or n.endswith(('.keystore','.jks')) for n in z.namelist())
debug=Path('releases/debug-manifest.txt').read_text()
assert "package: name='dev.mantistv.home.debug'" in debug and "sdkVersion:'25'" in debug
assert 'application-debuggable' in debug
assert 'application-debuggable' not in badging
print('PASS: debug/release package separation and debuggable flag policy')
