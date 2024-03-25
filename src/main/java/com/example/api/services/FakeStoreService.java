package com.example.api.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.dtos.FakeStoreProductDto;
import com.example.api.models.Category;
import com.example.api.models.Product;

@Service
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;// to call 3rd party API's

    private FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product ConvertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            return null;
        }
        return ConvertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

}
