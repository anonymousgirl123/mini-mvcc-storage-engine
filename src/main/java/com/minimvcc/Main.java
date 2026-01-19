package com.minimvcc;

import com.minimvcc.store.MVCCStore;
import com.minimvcc.txn.Transaction;
import com.minimvcc.txn.TransactionManager;

public class Main {
    public static void main(String[] args) {
        TransactionManager tm = new TransactionManager();
        MVCCStore<String, String> store = new MVCCStore<>();

        Transaction t1 = tm.begin();
        store.write("x", "v1", t1, tm.commit(t1));

        Transaction t2 = tm.begin();
        store.write("x", "v2", t2, tm.commit(t2));

        System.out.println(store.read("x", t1));
        System.out.println(store.read("x", t2));
    }
}
