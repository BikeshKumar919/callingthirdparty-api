package com.example.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.Product;
import com.example.api.services.ProductService;

@RestController  //To handle HTTP requests
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService){
        this.productService = productService;
    }   

    @GetMapping("/{id}")
    public Product getPoductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @GetMapping()
public List<Product> getAllProducts(){
    return productService.getAllProducts();
}
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return productService.replaceProduct(id, product);
    }


}
