package com.mcsv.user.user_service.service;

import com.mcsv.user.user_service.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    UserEntity getUser(String userId);
    List<UserEntity> getAllUsers();
}
