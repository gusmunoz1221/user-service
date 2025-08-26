package com.mcsv.user.user_service.service.impl;

import com.mcsv.user.user_service.entity.UserEntity;
import com.mcsv.user.user_service.exceptions.EmailAlreadyExistsException;
import com.mcsv.user.user_service.exceptions.ResourceNotFoundException;
import com.mcsv.user.user_service.mappers.UserMapper;
import com.mcsv.user.user_service.repository.UserRepository;
import com.mcsv.user.user_service.response.UserDtoRequest;
import com.mcsv.user.user_service.response.UserDtoResponse;
import com.mcsv.user.user_service.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDtoResponse saveUser(UserDtoRequest userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())) //valida si el email es unico
            throw new EmailAlreadyExistsException("el email ingresado ya existe");

        UserEntity user = userMapper.userDtoToUserEntity(userDto);

        userRepository.save(user);

        return userMapper.userEntityToUserDto(user);
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
