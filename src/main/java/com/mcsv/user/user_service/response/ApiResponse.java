package com.mcsv.user.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

// POR ALGUNA RAZON NO ME ESTA TOMANDO LAS ANOTACIONES DE LOMBOK
@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
     private String message;
     private boolean success;
     private HttpStatus status;
     private Map<String, String> errors; // se guarda la info de los @valid en caso que ocurra una ex
}
