package com.mcsv.user.mappers;

import com.mcsv.user.entities.Rating;
import com.mcsv.user.entities.UserEntity;
import com.mcsv.user.response.UserDtoRequest;
import com.mcsv.user.response.UserDtoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {
    public UserEntity userDtoToUserEntity(UserDtoRequest userDtoRequest) {
        return UserEntity.builder()
                .name(userDtoRequest.getName())
                .email(userDtoRequest.getEmail())
                .description(userDtoRequest.getDescription())
                .build();
    }

    public UserDtoResponse userEntityToUserDto(UserEntity user){
        return UserDtoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
