package com.tryfinch.apichallenge.resolver.vehicleinfo.tesla;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final List<CacheOperation> cacheOperations = new ArrayList<>();

    public void addCacheOperation(CacheOperation cacheOperation) {
        cacheOperations.add(cacheOperation);
    }

    public void rollback() {
        for (int i = cacheOperations.size() - 1; i >= 0; i--) {
            CacheOperation current = cacheOperations.get(i);
            current.rollback();
        }
    }
}
