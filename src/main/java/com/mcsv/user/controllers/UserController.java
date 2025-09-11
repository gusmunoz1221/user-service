package com.mcsv.user.controllers;

import com.mcsv.user.response.RatingDto;
import com.mcsv.user.response.UserDtoRequest;
import com.mcsv.user.response.UserDtoResponse;
import com.mcsv.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> saveUser(@Valid @RequestBody UserDtoRequest userDtoRequest){

        UserDtoResponse userDtoResponse = userService.saveUser(userDtoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);
    }

    @GetMapping("/{userId}")
    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserDtoResponse> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //Patron CircuitBreak con Builder
    public ResponseEntity<UserDtoResponse> ratingHotelFallback(String userId ,Exception e){
        log.info("El respaldo se ejecuta porque el servicio está inactivo: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                UserDtoResponse.builder()
                        .name("fallback-user")
                        .email("unavailable@service.com")
                        .ratingDto(List.of(
                                RatingDto.builder()
                                        .hotelId("N/A")
                                        .userId(userId)
                                        .rating(0)
                                        .observations("Servicio no disponible")
                                        .build()))
                        .build()
        );
    }
}
