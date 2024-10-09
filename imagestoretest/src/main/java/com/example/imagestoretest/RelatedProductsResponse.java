package com.example.imagestoretest;

import java.util.List;

public class RelatedProductsResponse {
    private List<Product> products;
    private boolean hasMore;

    public RelatedProductsResponse(List<Product> products, boolean hasMore) {
        this.products = products;
        this.hasMore = hasMore;
    }

    // Getters and Setters
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}

