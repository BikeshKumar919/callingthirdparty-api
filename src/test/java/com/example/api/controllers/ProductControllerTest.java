package com.example.api.controllers;

import com.example.api.dtos.ExceptionDto;
import com.example.api.exceptions.ProductNotFound;
import com.example.api.models.Product;
import com.example.api.services.ProductService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockBean
    ProductService productService;
    @Autowired
    ProductController productController;
    @Test
    public void getProductById() throws ProductNotFound {
        Product product=new Product();
        product.setId(1L);
        product.setDescription("new phone");
        product.setTitle("On board");
        when(productService.getProductById(1L)).thenReturn(product);
        ResponseEntity<Product> r=productController.getProductById(1L);
        Product ans=r.getBody();
        assertEquals(product,ans,"success");
    }
    @Test
    public void TestingThrow() throws  ProductNotFound{
        when(productService.getProductById(100L)).thenThrow(new ProductNotFound(100,"not found"));

        ProductNotFound thrown=assertThrows(ProductNotFound.class,()->productService.getProductById(100L));

        // Further assertions to check details of the exception
        System.out.println("Thrown message: " + thrown.getMessage());
        assertEquals("not found", thrown.getMessage());

    }
}