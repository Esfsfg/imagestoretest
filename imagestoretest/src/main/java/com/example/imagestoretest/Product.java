package com.example.imagestoretest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private byte[] image;
    private int rating; // Add this field to store product rating
    private String category;
    private String address;
  
}
