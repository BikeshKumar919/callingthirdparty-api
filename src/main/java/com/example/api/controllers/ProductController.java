package com.example.api.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.commons.AuthCommon;
import com.example.api.dtos.UserDto;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api.exceptions.ProductNotFound;
import com.example.api.models.Product;
import com.example.api.services.ProductService;


@RestController  // you're telling Spring that this class is a controller and all its methods should have their return values written directly to the body of the HTTP response.
@RequestMapping("/products")//annotation is used in Spring MVC to map web requests onto specific handler classes and/or handler methods. This annotation can be applied to class-level and/or method-level in a controller.
public class ProductController {
    private ProductService productService;
    private AuthCommon authCommon;
    ProductController(ProductService productService,AuthCommon authCommon){
        this.productService = productService;
        this.authCommon=authCommon;
    }   

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id,@RequestHeader("auth") String token) throws ProductNotFound{
        UserDto userDto=authCommon.validateToken(token);
        ResponseEntity<Product> responseEntity=null;
        if(userDto==null) {
                responseEntity=new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
                return responseEntity;
        }
        //return productService.getProductById(id);
        Product product = productService.getProductById(id);
       responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping()
public List<Product> getAllProducts(){
    return productService.getAllProducts();
}
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product){
        return productService.replaceProduct(id, product);
    }
    @GetMapping("/category/{category}")
    public List<Product> getCategory(@PathVariable("category") String category) {
        return productService.getCategory(category);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    
}
