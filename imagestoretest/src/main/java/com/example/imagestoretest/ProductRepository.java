package com.example.imagestoretest;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
       List<Product> findByCategory(String category);
}

