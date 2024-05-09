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
        //ResponseEntity represents the entire HTTP response, including both the body and the status code.
        //you can use it to return any type of object along with additional HTTP information such as status code, headers, etc. It is a powerful way to control your REST API's responses precisely.
    }
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ExceptionDto> handleProductNotFound(ProductNotFound e){
        ExceptionDto exceptionDto = new ExceptionDto(null);
        exceptionDto.setMessage("Product not found with id "+e.getId());
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
