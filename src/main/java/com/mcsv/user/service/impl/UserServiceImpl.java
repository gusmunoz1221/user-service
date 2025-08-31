package com.mcsv.user.service.impl;

import com.mcsv.user.entities.Hotel;
import com.mcsv.user.entities.Rating;
import com.mcsv.user.entities.UserEntity;
import com.mcsv.user.exceptions.EmailAlreadyExistsException;
import com.mcsv.user.exceptions.ResourceNotFoundException;
import com.mcsv.user.mappers.UserMapper;
import com.mcsv.user.repository.UserRepository;
import com.mcsv.user.response.UserDtoRequest;
import com.mcsv.user.response.UserDtoResponse;
import com.mcsv.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
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
    public UserDtoResponse getUser(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + userId + " no encontrado"));

        Rating[] ratingByUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getId(),Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingByUser).toList();

        List<Rating> ratingList = ratings.stream().map( rating -> {

            ResponseEntity<Hotel>  forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            rating.setHotel(hotel);

            return rating;
        }).toList();


        UserDtoResponse userDtoResponse = userMapper.userEntityToUserDto(user);
        userDtoResponse.setRatings(ratingList);

        return userDtoResponse;
    }

    @Override
    public List<UserDtoResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::userEntityToUserDto)
                .toList();
    }
}
