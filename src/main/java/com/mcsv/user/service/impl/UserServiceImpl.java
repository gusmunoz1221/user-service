package com.mcsv.user.service.impl;

import com.mcsv.user.response.HotelDto;
import com.mcsv.user.response.RatingDto;
import com.mcsv.user.entities.UserEntity;
import com.mcsv.user.exceptions.EmailAlreadyExistsException;
import com.mcsv.user.exceptions.ResourceNotFoundException;
import com.mcsv.user.external.service.HotelService;
import com.mcsv.user.mappers.UserMapper;
import com.mcsv.user.repository.UserRepository;
import com.mcsv.user.response.UserDtoRequest;
import com.mcsv.user.response.UserDtoResponse;
import com.mcsv.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
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


        //Llamo al mcsv RATING-SERVICE para traer todas las calificaciones del usuario.
        //restTemplate.getForObject devuelve un array de Rating.
        RatingDto[] ratingDtoByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getId(), RatingDto[].class);

        //Convierto array Rating[] a List<Rating> para poder usar streams.
        List<RatingDto> ratingDtos = Arrays.stream(ratingDtoByUser).toList();


        //Llamo al hotelService para obtener información del hotel correspondiente
        //Asigno ese objeto Hotel a la calificación
        //Devuelve la calificación con el hotel asociado.
        //Finalmente devuelve una lista de calificaciones completas -> ratingList
        List<RatingDto> ratingDtoList = ratingDtos.stream().map(ratingDto -> {

            HotelDto hotelDto = hotelService.getHotel(ratingDto.getHotelId());
            ratingDto.setHotelDto(hotelDto);
            return ratingDto;
        }).toList();

        UserDtoResponse userDtoResponse = userMapper.userEntityToUserDto(user);
        userDtoResponse.setRatingDto(ratingDtoList);

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