package com.minimvcc.mvcc;

public class VersionedValue<T> {
    private final T value;
    private final long createdTs;
    private volatile long deletedTs;

    public VersionedValue(T value, long createdTs, long deletedTs) {
        this.value = value;
        this.createdTs = createdTs;
        this.deletedTs = deletedTs;
    }

    public boolean isVisible(long readTs) {
        return createdTs <= readTs && readTs < deletedTs;
    }

    public void markDeleted(long ts) {
        this.deletedTs = ts;
    }

    public long getCreatedTs() {
        return createdTs;
    }

    public long getDeletedTs() {
        return deletedTs;
    }

    public T getValue() {
        return value;
    }
}
