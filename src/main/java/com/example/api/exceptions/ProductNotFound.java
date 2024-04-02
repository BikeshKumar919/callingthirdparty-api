package com.example.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFound extends Exception {
    private long id;
    private String message;
    public ProductNotFound(long id,String message) {
        super(message);
        this.id = id;
    }
}
