package com.mcsv.user.exceptions;

import com.mcsv.user.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        String message  = e.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message("Mensaje de error")
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();
       return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse>  handleEmailAlreadyExists(EmailAlreadyExistsException e){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(e.getMessage())
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    // maneja todos los errores de validación de @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        ApiResponse response = ApiResponse.builder()
                .message("Errores de validación")
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .errors(errores) //@valid
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
