## Failure Analysis

- Writes before flush are not durable
- fsync ensures disk persistence
- mmap durability depends on OS writeback
- Power loss favors fsync-backed writes
