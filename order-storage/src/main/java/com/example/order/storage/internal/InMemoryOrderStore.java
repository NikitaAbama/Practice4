package com.example.order.storage.internal;

import com.example.order.processing.Order;
import com.example.order.processing.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderStore {
    private final List<Order<? extends Product>> orders = new ArrayList<>();

    public synchronized void save(Order<? extends Product> order) {
        orders.add(order);
    }

    public synchronized List<Order<? extends Product>> getAll() {
        return new ArrayList<>(orders);
    }
}
