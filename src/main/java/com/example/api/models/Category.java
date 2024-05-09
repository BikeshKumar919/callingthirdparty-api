package com.example.api.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String title;
//    @OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)//will not create an extra table like category_Product
//    private List<Product> productList;
    int qty;
}
