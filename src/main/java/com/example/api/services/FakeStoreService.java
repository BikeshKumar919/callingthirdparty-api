package com.example.api.services;


import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.example.api.dtos.FakeStoreProductDto;
import com.example.api.exceptions.ProductNotFound;
import com.example.api.models.Category;
import com.example.api.models.Product;

@Service//@Service is being used to mark the FakeStoreService class as a service layer within the application. This means that Spring will automatically create an instance of FakeStoreService and inject it where it's needed in your application.
//@Primary
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;// to call 3rd party API's

    //Spring's Inversion of Control (IoC) container calls the FakeStoreService constructor when it creates a bean of that type. This happens during the startup of the application context.
    private FakeStoreService(RestTemplate restTemplate) {//When Spring creates this bean, it sees that FakeStoreService has a constructor that takes a RestTemplate as a parameter. Spring will then look for a bean of type RestTemplate in its container. If it finds one, it will call the FakeStoreService constructor, passing in the RestTemplate bean.
        this.restTemplate = restTemplate;
    }

    //In your current code, you're using constructor injection without the @Autowired annotation. This works because since Spring 4.3, if a class only declares one constructor, @Autowired is not necessary. Spring will use that constructor and inject the dependencies.


    public Product ConvertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFound {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFound(id,"Product not found hmmmm");
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
        public List<Product> getCategory(String category) {
            FakeStoreProductDto[]  fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+category,
            FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(ConvertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return products;
        }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
