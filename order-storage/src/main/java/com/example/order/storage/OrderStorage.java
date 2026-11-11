package com.example.order.storage;

import com.example.order.processing.Order;
import com.example.order.processing.Product;
import com.example.order.storage.internal.InMemoryOrderStore;

import java.util.List;

public class OrderStorage {
    private final InMemoryOrderStore store = new InMemoryOrderStore();

    public void save(Order<? extends Product> order) {
        store.save(order);
    }

    public List<Order<? extends Product>> getAll() {
        return store.getAll();
    }
}
