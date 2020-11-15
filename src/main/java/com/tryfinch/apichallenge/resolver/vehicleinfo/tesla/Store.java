package com.tryfinch.apichallenge.resolver.vehicleinfo.tesla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store<K, V> {

    private final Map<K, V> valuesMap = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>(); // TODO change this to a deque
    private Transaction currentTx;

    public void set(K key, V value) {
        if (currentTx == null) {
            throw new IllegalStateException("No active tx");
        }

        CacheOperation setOperation = new SetOperation<>(key, value, valuesMap.get(key), this);
        currentTx.addCacheOperation(setOperation);
        valuesMap.put(key, value);
    }

    public V get(K key) {
        return valuesMap.get(key);
    }

    public V delete(K key) {
        CacheOperation deleteOperation = new DeleteOperation<>(key, valuesMap.get(key), valuesMap.get(key), this);
        currentTx.addCacheOperation(deleteOperation);
        return valuesMap.remove(key);
    }

    public void begin() {
        currentTx = new Transaction();
        transactions.add(currentTx);
    }

    public void commit() {
        currentTx = null;
        transactions.clear();
    }

    public void rollback() {
        if (currentTx == null) {
            throw new IllegalStateException("can't rollback if there is not current tx");
        }

        currentTx.rollback();
        transactions.remove(transactions.size() - 1);
        currentTx = transactions.get(transactions.size() - 1);
    }

}
