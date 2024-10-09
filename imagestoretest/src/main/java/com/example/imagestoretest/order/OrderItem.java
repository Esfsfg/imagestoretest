package com.example.imagestoretest.order;

import com.example.imagestoretest.Product;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
