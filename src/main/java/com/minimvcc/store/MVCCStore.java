package com.minimvcc.store;

import com.minimvcc.mvcc.VersionChain;
import com.minimvcc.txn.Transaction;

import java.util.concurrent.ConcurrentHashMap;

public class MVCCStore<K, V> {

    private final ConcurrentHashMap<K, VersionChain<V>> data = new ConcurrentHashMap<>();

    public V read(K key, Transaction txn) {
        VersionChain<V> chain = data.get(key);
        if (chain == null) return null;
        return chain.read(txn.getStartTs());
    }

    public void write(K key, V value, Transaction txn, long commitTs) {
        VersionChain<V> chain = data.computeIfAbsent(key, k -> new VersionChain<>());

        if (!chain.getVersions().isEmpty()) {
            long latestTs = chain.getVersions()
                    .get(chain.getVersions().size() - 1)
                    .getCreatedTs();
            if (latestTs > txn.getStartTs()) {
                throw new IllegalStateException("Write-write conflict");
            }
        }
        chain.addVersion(value, commitTs);
    }

    public void cleanup(long safeTs) {
        data.values().forEach(vc -> vc.cleanup(safeTs));
    }
}
