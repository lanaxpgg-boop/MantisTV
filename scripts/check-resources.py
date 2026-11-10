import os, json
s=os.statvfs('.')
capacity=s.f_blocks*s.f_frsize
available=s.f_bavail*s.f_frsize
reserve=max(5*1024**3,capacity*.15)
print(json.dumps({'capacity_bytes':capacity,'available_bytes':available,'reserve_bytes':reserve,'headroom_bytes':1024**3}))
if available < reserve+1024**3: raise SystemExit('STOP: insufficient cloud disk reserve and build headroom')
