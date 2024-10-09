package com.example.imagestoretest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }


     // Fetch related products based on some criteria
     public List<Product> getRelatedProducts(String productId) {
        // Example: fetch products from the same category, or any custom logic
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return productRepository.findByCategory(product.get().getCategory());
        }
        return List.of();  // Return an empty list if no related products are found
    }


    // SEARCH FUNCTIONALITY
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
