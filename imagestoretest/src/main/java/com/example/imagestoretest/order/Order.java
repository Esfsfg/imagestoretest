package com.example.imagestoretest.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String buyerName;
    private String buyerAddress;
    private String buyerMobile;
    private List<OrderItem> items;
    private double totalPrice;
    private Date orderDate;

    public Order(String buyerName, String buyerAddress, String buyerMobile, List<OrderItem> items, double totalPrice) {
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.buyerMobile = buyerMobile;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getBuyerName() { return buyerName; }
    public String getBuyerAddress() { return buyerAddress; }
    public  String getBuyerMobile() { return buyerMobile; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
    public Date getOrderDate() { return orderDate; }
}

