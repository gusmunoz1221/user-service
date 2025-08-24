package com.mcsv.user.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

// POR ALGUNA RAZON NO ME ESTA TOMANDO LAS ANOTACIONES DE LOMBOK
@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
     String message;
     boolean success;
     HttpStatus status;

}
