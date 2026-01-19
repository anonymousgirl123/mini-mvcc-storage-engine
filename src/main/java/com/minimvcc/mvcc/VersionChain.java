package com.minimvcc.mvcc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VersionChain<T> {
    private final List<VersionedValue<T>> versions = new CopyOnWriteArrayList<>();

    public synchronized void addVersion(T value, long commitTs) {
        if (!versions.isEmpty()) {
            versions.get(versions.size() - 1).markDeleted(commitTs);
        }
        versions.add(new VersionedValue<>(value, commitTs, Long.MAX_VALUE));
    }

    public T read(long ts) {
        for (int i = versions.size() - 1; i >= 0; i--) {
            VersionedValue<T> v = versions.get(i);
            if (v.isVisible(ts)) {
                return v.getValue();
            }
        }
        return null;
    }

    public void cleanup(long safeTs) {
        versions.removeIf(v -> v.getDeletedTs() < safeTs);
    }

    public List<VersionedValue<T>> getVersions() {
        return versions;
    }
}
