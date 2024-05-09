package com.example.api.Reprositories;

import com.example.api.models.Product;
import com.example.api.projections.projectionbyattr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface productRepo extends JpaRepository<Product,Long> {
    //you don't need to provide an implementation for the interfaces that extend JpaRepository.
    //Spring Data JPA will create a proxy implementation of this interface when the application context is loaded. This implementation will include the methods inherited from JpaRepository as well as any custom query methods you define using Spring Data's query derivation mechanism.
    @Override
    Optional<Product> findById(Long id);
    List<Product> findByTitle(String title);
    List<Product> findByTitleContains(String str);
    List<Product> findByTitleAndDescription(String title,String description);
    Optional<Product> findByImage(String url);
    Product save(Product product);//creates and updates

    @Query("select p.title as title,p.description as description from Product p where p.id=:id")
    projectionbyattr something(@Param("id") Long id);
    //The above alias is as same as given in the projectionattr interface
    @Query(value = "select title,description from product where id=:id",nativeQuery = true)
    projectionbyattr dosomething(@Param("id") Long id);
}
