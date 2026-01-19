# MiniMVCC Storage Engine   ![CI](https://github.com/anonymousgirl123/mini-mvcc-storage-engine/actions/workflows/ci.yml/badge.svg?branch=main)

MiniMVCC is a simplified Java storage engine designed to explore **MVCC**, **JVM memory behavior**, and **Linux internals**.

This project focuses on *how systems behave under load, memory pressure, and failure* rather than feature completeness.

## Why this project?
Modern data platforms require deep understanding of:
- Database internals (MVCC, isolation, versioning)
- JVM memory & GC behavior
- Linux I/O, page cache, and durability trade-offs

This repository demonstrates those concepts end-to-end in Java.

## Architecture
- Snapshot isolation using MVCC
- Version chains with tombstones
- Write-write conflict detection
- Version garbage collection
- Heap, file I/O, and mmap-based storage

## Storage Backends
- Heap-based (baseline)
- Buffered File I/O
- Memory-Mapped Files (mmap)

## Benchmarks
- Read-heavy workload (MVCC strength)
- Write-heavy workload (write amplification)
- JVM heap pressure vs mmap RSS growth

## Linux Observations
- mmap shifts memory management to Linux page cache
- RSS grows independently of JVM heap
- fsync dominates tail latency

## Durability Model
Data is durable **only after explicit flush**.
- fsync provides strongest guarantees
- mmap.force() is OS-dependent

## Trade-offs
This project intentionally prioritizes clarity and observability over production hardening.

## Performance & Profiling

- Lightweight benchmarks are executed in CI
- Results are uploaded as build artifacts
- Java Flight Recorder (JFR) is captured automatically
- Enables post-run analysis of GC, allocation, and CPU behavior

## Note: Architecture diagrams are authored in PlantUML and rendered automatically in CI as build artifacts.
  
