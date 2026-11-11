package com.example.order.processing;

public class OrderProcessor<T extends Product> {
    private final T product;

    public OrderProcessor(T product) {
        this.product = product;
    }

    public void process() {
        if (product == null) {
            throw new InvalidOrderException("Product is missing");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidOrderException("Product name is empty");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidOrderException("Product price must be positive");
        }

        System.out.println(Thread.currentThread().getName()
                + " processes: " + product.getName());

        try {
            Thread.sleep(300); // Simulate order processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InvalidOrderException("Order processing was interrupted");
        }
    }
}
