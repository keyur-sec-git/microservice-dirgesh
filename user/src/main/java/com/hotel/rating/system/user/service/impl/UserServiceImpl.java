package com.hotel.rating.system.user.service.impl;

import com.hotel.rating.system.user.exceptions.DataAlreadyExistsException;
import com.hotel.rating.system.user.exceptions.DataNotFoundException;
import com.hotel.rating.system.user.external.services.HotelServiceFeignClient;
import com.hotel.rating.system.user.external.services.RatingServiceFeignClient;
import com.hotel.rating.system.user.model.RequestBean.UserRequestBean;
import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;
import com.hotel.rating.system.user.model.dao.Hotel;
import com.hotel.rating.system.user.model.dao.Rating;
import com.hotel.rating.system.user.model.dao.User;
import com.hotel.rating.system.user.repository.UserRepo;
import com.hotel.rating.system.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServiceFeignClient hotelServiceFeignClient;

    @Autowired
    private RatingServiceFeignClient ratingServiceFeignClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceResponseBean saveUser(UserRequestBean userRequestBean) throws DataAlreadyExistsException {
        log.info("Saving user");
        Optional<User> optionalUser = this.userRepo.findByEmail(userRequestBean.getEmail());
        if (optionalUser.isPresent()) {
            throw new DataAlreadyExistsException("Data already exists");
        }
        User user = this.modelMapper.map(userRequestBean, User.class);
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User savedUser = this.userRepo.save(user);
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Data saved successfully")
                .data(savedUser)
                .build();
        log.info("User saved successfully");
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getAllUsers() throws DataNotFoundException {
        log.info("Fetching all the users");
        List<User> userList = this.userRepo.findAll();
        if (userList.isEmpty()) {
            throw new DataNotFoundException("No user data found");
        }
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Fetched all the users")
                .data(userList)
                .build();
        log.info("Successfully fetched all the users");
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getUser(String userId) throws DataNotFoundException {
        log.info("Fetching user by Id :: {}", userId);
        Optional<User> optionalUser = this.userRepo.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new DataNotFoundException("User not found");
        }
        User user = optionalUser.get();
        log.info("Fetching ratings given by above user from Rating Service");
//            ServiceResponseBean ratingsGivenByUser = restTemplate.getForObject("http://localhost:3003/ratings/users/" + userId, ServiceResponseBean.class);
//            ServiceResponseBean ratingsGivenByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), ServiceResponseBean.class);
        ServiceResponseBean ratingsGivenByUser = ratingServiceFeignClient.getRatingByUserId(userId);
        List<Object> ratingList = (List<Object>) ratingsGivenByUser.getData();
        List<Rating> userRatings = ratingList.stream()
                .map(rating -> this.modelMapper.map(rating, Rating.class))
                .toList();
        List<Rating> ratingsList = userRatings.stream().map(rating -> {
//                ServiceResponseBean hotelServiceResponseBean = restTemplate.getForObject("http://localhost:3002/hotels/" + rating.getHotelId(), ServiceResponseBean.class);
//                ServiceResponseBean hotelServiceResponseBean = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), ServiceResponseBean.class);
            ServiceResponseBean hotelServiceResponseBean = hotelServiceFeignClient.getHotel(rating.getHotelId());
            Hotel hotel = this.modelMapper.map(hotelServiceResponseBean.getData(), Hotel.class);
            rating.setHotel(hotel);
            return rating;
        }).toList();
        user.setRatings(ratingsList);

        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("User fetched successfully")
                .data(user)
                .build();
        log.info("Fetched user by Id :: {}", userId);
        return serviceResponseBean;
    }
}
