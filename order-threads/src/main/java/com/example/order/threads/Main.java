package com.example.order.threads;

import com.example.order.processing.Clothing;
import com.example.order.processing.Electronics;
import com.example.order.processing.Order;
import com.example.order.processing.Product;
import com.example.order.storage.OrderStorage;
import com.github.javafaker.Faker;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        Electronics phone = Electronics.builder()
                .name("Phone " + faker.commerce().productName())
                .price(12000)
                .warrantyMonths(12)
                .build();

        Clothing shirt = Clothing.builder()
                .name("T-shirt " + faker.commerce().productName())
                .price(800)
                .size("M")
                .build();

        Electronics laptop = Electronics.builder()
                .name("Laptop " + faker.commerce().productName())
                .price(32000)
                .warrantyMonths(24)
                .build();

        Clothing broken = Clothing.builder()
                .name("Invalid shirt")
                .price(-100) // Invalid price to demonstrate exception
                .size("L")
                .build();

        Order<Electronics> order1 = new Order<>(1, phone);
        Order<Clothing> order2 = new Order<>(2, shirt);
        Order<Electronics> order3 = new Order<>(3, laptop);
        Order<Clothing> order4 = new Order<>(4, broken);

        List<Order<? extends Product>> orders = List.of(
                order1, order2, order3, order4);

        System.out.println("=== Electronics orders ===");
        orders.stream()
                .filter(order -> order.getProduct() instanceof Electronics)
                .forEach(System.out::println);

        System.out.println();
        System.out.println("=== Start processing orders ===");
        OrderStorage storage = new OrderStorage();
        ThreadManager manager = new ThreadManager();
        orders.forEach(order -> manager.submit(order, storage));

        try {
            manager.waitForAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
            return;
        }

        System.out.println();
        System.out.println("=== Saved orders: " + storage.getAll().size() + " ===");
        storage.getAll().forEach(System.out::println);
    }
}
