package com.tryfinch.apichallenge.resolver.vehicleinfo.tesla;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetOperation<K, V> implements CacheOperation {
    private K key;
    private V newValue; // TODO perhaps remove this field
    private V oldValue;
    private Store<K, V> store;

    public void rollback() {
        store.set(key, oldValue);
    }
}
