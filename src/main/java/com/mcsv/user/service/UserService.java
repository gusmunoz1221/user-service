package com.mcsv.user.service;

import com.mcsv.user.response.UserDtoRequest;
import com.mcsv.user.response.UserDtoResponse;

import java.util.List;

public interface UserService {
    UserDtoResponse saveUser(UserDtoRequest user);
    UserDtoResponse getUser(String userId);
    List<UserDtoResponse> getAllUsers();
}
