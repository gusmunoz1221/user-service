package com.mcsv.user.user_service.exceptions;

import com.mcsv.user.user_service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionControler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        String message  = resourceNotFoundException.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message("Mensaje de error")
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();
       return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
