package com.example.api.services;

import com.example.api.Reprositories.CatRepo;
import com.example.api.Reprositories.productRepo;
import com.example.api.exceptions.ProductNotFound;
import com.example.api.models.Category;
import com.example.api.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
// If you have two service classes of the same type (meaning the same class or implementing the same interface), Spring gets confused about which one to use for injection.
public class selfProductService implements ProductService{

    productRepo pr;
    CatRepo catRepo;
    selfProductService(productRepo pr,CatRepo catRepo){
        this.pr=pr;
        this.catRepo=catRepo;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFound {
        Optional<Product> pro=pr.findById(id);
        if(pro.isEmpty())
            throw new ProductNotFound(id,"not found");
        return pro.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public List<Product> getCategory(String category) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category c=product.getCategory();
        if(c.getId()==null){
            Category savedCat=catRepo.save(c);
            product.setCategory(savedCat);
        }

        return pr.save(product);
    }
//Usage: You can use all the methods of JpaRepository, such as findById, findAll, save, deleteById, etc., directly on the productRepository object without having to worry about their implementation.
    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
