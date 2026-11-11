package com.example.order.threads;

import com.example.order.processing.InvalidOrderException;
import com.example.order.processing.Order;
import com.example.order.processing.OrderProcessor;
import com.example.order.processing.Product;
import com.example.order.storage.OrderStorage;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private final List<Thread> threads = new ArrayList<>();

    public <T extends Product> void submit(Order<T> order, OrderStorage storage) {
        OrderProcessor<T> processor = new OrderProcessor<>(order.getProduct());
        Runnable processingTask = processor::process;

        Runnable task = () -> {
            try {
                processingTask.run();
                storage.save(order);
                System.out.println("Saved order #" + order.getId());
            } catch (InvalidOrderException e) {
                System.out.println("Error in order #" + order.getId()
                        + ": " + e.getMessage());
            }
        };

        Thread thread = new Thread(task, "Worker-" + order.getId());
        threads.add(thread);
        thread.start();
    }

    public void waitForAll() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
