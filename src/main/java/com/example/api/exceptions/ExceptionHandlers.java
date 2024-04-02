package com.example.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.api.dtos.ExceptionDto;
@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Void> handleArthematicException(){
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ExceptionDto> handleProductNotFound(ProductNotFound e){
        ExceptionDto exceptionDto = new ExceptionDto(null);
        exceptionDto.setMessage("Product not found with id "+e.getId());
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
