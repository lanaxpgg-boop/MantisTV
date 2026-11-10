import urllib.request, re, json
from html.parser import HTMLParser
from pathlib import Path
from datetime import datetime, timezone
class Table(HTMLParser):
 def __init__(self): super().__init__(); self.rows=[];self.cells=[];self.cell=None
 def handle_starttag(self, tag, attrs):
  if tag=='tr': self.cells=[]
  if tag=='td': self.cell=[]
 def handle_data(self, data):
  if self.cell is not None:self.cell.append(data)
 def handle_endtag(self, tag):
  if tag=='td' and self.cell is not None:self.cells.append(' '.join(' '.join(self.cell).split()));self.cell=None
  if tag=='tr' and self.cells:self.rows.append(self.cells)
base='https://ftvdb.com/firetv/firmware/com.amazon.mantis.android.os/'
records=[]
for kind,url in [('full',base),('diff',base+'diff/')]:
 req=urllib.request.Request(url,headers={'User-Agent':'MantisTV public-source compatibility audit'})
 with urllib.request.urlopen(req,timeout=30) as response:
  raw=response.read(2*1024*1024+1)
 if len(raw)>2*1024*1024:raise RuntimeError('Catalog exceeded audit size limit')
 parser=Table();parser.feed(raw.decode('utf-8'))
 rows=[cells for cells in parser.rows if any('NS' in cell for cell in cells)]
 if not rows:raise RuntimeError('No firmware rows parsed; catalog format requires review')
 for cells in rows:
  records.append({'kind':kind,'source':url,'catalog_cells':cells,'evidence':'C','api':'25 (device-family specification; per-build unverified)','abi':'32-bit ARM (device-family specification; per-build unverified)','system_packages':'unknown','launcher':'ordinary app approach; per-build untested','permissions':'manifest only ACCESS_NETWORK_STATE; per-build behavior unknown','services':'public Android services; per-build availability unverified','testing':'no physical tests or firmware package extraction'})
out=Path('device');out.mkdir(exist_ok=True)
(out/'firmware-catalog.json').write_text(json.dumps({'retrieved_at':datetime.now(timezone.utc).isoformat(),'records':records},indent=2))
lines=['# AFTMM catalog matrix','', 'Every row is C: unknown/experimental. Recorded dates are catalog dates, not guaranteed release dates. No firmware binaries were downloaded. Platform API25/32-bit ARM comes from the device-family specification, not per-build testing. System-package differences, runtime permission/service behavior and launcher compatibility remain unknown for every row. The full and incremental lists are not evidence of an exhaustive official release history.','', '| Kind | Catalog version / build / date cells | Evidence | API / ABI | Packages / permissions / services | Physical tests |','|---|---|---|---|---|---|']
for r in records:lines.append('| '+r['kind']+' | '+' / '.join(r['catalog_cells']).replace('|','/')+' | C | 25 / ARM32 family only | Unknown per build | None |')
Path('documentation/AFTMM-CATALOG-MATRIX.md').write_text('\n'.join(lines)+'\n')
print(json.dumps({'catalog_records':len(records),'evidence':'all C','firmware_downloads':0}))
