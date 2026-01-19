## MVCC Design

- Snapshot isolation via logical timestamps
- Deletes implemented using tombstones
- Write-write conflicts detected at commit time
- Old versions garbage-collected using oldest active transaction
