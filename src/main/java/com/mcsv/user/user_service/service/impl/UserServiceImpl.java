package com.mcsv.user.user_service.service.impl;

import com.mcsv.user.user_service.entity.UserEntity;
import com.mcsv.user.user_service.exceptions.ResourceNotFoundException;
import com.mcsv.user.user_service.repository.UserRepository;
import com.mcsv.user.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + userId + " no encontrado"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
