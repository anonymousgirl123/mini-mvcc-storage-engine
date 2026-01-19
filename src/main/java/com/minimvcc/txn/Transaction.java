package com.minimvcc.txn;

public class Transaction {
    private final long txnId;
    private final long startTs;

    public Transaction(long txnId, long startTs) {
        this.txnId = txnId;
        this.startTs = startTs;
    }

    public long getTxnId() {
        return txnId;
    }

    public long getStartTs() {
        return startTs;
    }
}
