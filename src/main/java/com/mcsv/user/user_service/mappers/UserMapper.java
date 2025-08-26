package com.mcsv.user.user_service.mappers;

import com.mcsv.user.user_service.entity.UserEntity;
import com.mcsv.user.user_service.response.UserDtoRequest;
import com.mcsv.user.user_service.response.UserDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public UserEntity userDtoToUserEntity(UserDtoRequest userDtoRequest) {
        return UserEntity.builder()
                .name(userDtoRequest.getName())
                .email(userDtoRequest.getEmail())
                .description(userDtoRequest.getDescription())
                .build();
    }

    public UserDtoResponse userEntityToUserDto( UserEntity user){
        return UserDtoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
