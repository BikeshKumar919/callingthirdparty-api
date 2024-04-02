



package com.example.api.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * ExceptionDto
 */
@Getter
@Setter
public class ExceptionDto {
private String message;
    public ExceptionDto(String message) {
        this.message = message;
    }
    
}