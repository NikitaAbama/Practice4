package com.example.order.processing;

public class Order<T extends Product> {
    private final int id;
    private final T product;

    public Order(int id, T product) {
        this.id = id;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public T getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Order #" + id + ": " + product.getName()
                + " (" + product.getPrice() + " UAH)";
    }
}
