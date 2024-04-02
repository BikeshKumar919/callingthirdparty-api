package com.example.api.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.example.api.dtos.FakeStoreProductDto;
import com.example.api.exceptions.ProductNotFound;
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
    public Product getProductById(Long id) throws ProductNotFound {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFound(id,"Product not found");
        }
        
        return ConvertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[]  fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class);
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                products.add(ConvertFakeStoreDtoToProduct(fakeStoreProductDto));
            }
            return products;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto res= restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return ConvertFakeStoreDtoToProduct(res);
    }

}
