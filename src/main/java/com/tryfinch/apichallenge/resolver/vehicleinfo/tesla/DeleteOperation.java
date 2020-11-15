package com.tryfinch.apichallenge.resolver.vehicleinfo.tesla;

import lombok.AllArgsConstructor;
import lombok.Data;

// TOOD perhaps remove this class and only use SetOperation
@Data
@AllArgsConstructor
public class DeleteOperation<K, V> implements CacheOperation {
    private K key;
    private V newValue; // TODO perhaps remove this field
    private V oldValue;
    private Store<K, V> store;

    public void rollback() {
        store.set(key, oldValue);
    }
}
