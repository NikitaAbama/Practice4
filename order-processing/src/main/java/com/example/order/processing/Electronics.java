package com.example.order.processing;

import lombok.Builder;

public class Electronics extends Product {
    private final int warrantyMonths;

    @Builder
    public Electronics(String name, double price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }
}
