## Linux & JVM Observations

### Heap
- Fast access
- GC pressure under write-heavy workloads

### mmap
- Off-heap storage via Linux page cache
- Reduced GC pressure
- Page faults affect tail latency

### fsync
- Strong durability
- High latency spikes
