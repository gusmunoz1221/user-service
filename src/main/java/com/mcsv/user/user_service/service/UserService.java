package com.mcsv.user.user_service.service;

import com.mcsv.user.user_service.entity.UserEntity;
import com.mcsv.user.user_service.response.UserDtoRequest;
import com.mcsv.user.user_service.response.UserDtoResponse;

import java.util.List;

public interface UserService {
    UserDtoResponse saveUser(UserDtoRequest user);
    UserEntity getUser(String userId);
    List<UserEntity> getAllUsers();
}
