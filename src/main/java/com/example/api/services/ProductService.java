package com.example.api.services;

import java.util.List;

import com.example.api.exceptions.ProductNotFound;
import com.example.api.models.Product;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFound;
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);
}
