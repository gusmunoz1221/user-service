package com.mcsv.user.user_service.controllers;

import com.mcsv.user.user_service.entity.UserEntity;
import com.mcsv.user.user_service.response.UserDtoRequest;
import com.mcsv.user.user_service.response.UserDtoResponse;
import com.mcsv.user.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

    @GetMapping("/userId")
    public ResponseEntity<UserDtoResponse> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
