package com.minimvcc.txn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionManager {
    private final AtomicLong clock = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Transaction> active = new ConcurrentHashMap<>();

    public Transaction begin() {
        long ts = clock.incrementAndGet();
        Transaction txn = new Transaction(ts, ts);
        active.put(ts, txn);
        return txn;
    }

    public long commit(Transaction txn) {
        active.remove(txn.getTxnId());
        return clock.incrementAndGet();
    }

    public long oldestActiveTs() {
        return active.keySet().stream().min(Long::compare).orElse(Long.MAX_VALUE);
    }
}
