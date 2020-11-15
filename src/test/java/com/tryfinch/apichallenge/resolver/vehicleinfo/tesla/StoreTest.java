package com.tryfinch.apichallenge.resolver.vehicleinfo.tesla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {


    @Test
    void setNoCurrentTx() {
        Store<String, Number> store = new Store<>();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            store.set("Foo", 5);
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    void set() {
        Store<String, Number> store = new Store<>();
        store.begin();
        store.set("foo", 12);
        assertEquals(12, store.get("foo"));
    }

    @Test
    void setWithTx() {
        Store<String, Number> store = new Store<>();
        store.begin();
        store.set("foo", 12);
        store.set("bar", 13);

        store.begin();
        store.set("foo", 112);
        store.set("bar", 113);
        assertEquals(112, store.get("foo"));
        assertEquals(113, store.get("bar"));
        store.rollback();

        assertEquals(12, store.get("foo"));
        assertEquals(13, store.get("bar"));
    }

    @Test
    void setWithMultipleTxs() {
        Store<String, Number> store = new Store<>();
        store.begin();
        store.set("foo", 12);
        store.set("bar", 13);
        store.set("hii", 14);

        store.begin();
        store.set("foo", 112);
        store.set("bar", 113);
        store.delete("hii");


        store.begin();
        store.set("foo", 212);
        store.set("bar", 213);
        store.set("hii", 214);


        store.begin();
        store.set("foo", 312);
        store.set("bar", 313);
        store.delete("hii");
        assertNull(store.get("hii"));
        store.rollback();

        assertEquals(212, store.get("foo"));
        assertEquals(213, store.get("bar"));
        assertEquals(214, store.get("hii"));


        store.rollback();

        assertEquals(112, store.get("foo"));
        assertEquals(113, store.get("bar"));
        assertNull(store.get("hii"));

        store.rollback();

        assertEquals(14, store.get("hii"));
    }
}