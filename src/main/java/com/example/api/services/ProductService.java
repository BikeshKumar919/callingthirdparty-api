package com.example.api.services;

import java.util.List;

import com.example.api.models.Product;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
}
