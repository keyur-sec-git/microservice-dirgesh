package com.hotel.rating.system.user.controller;

import com.hotel.rating.system.user.exceptions.DataAlreadyExistsException;
import com.hotel.rating.system.user.exceptions.DataNotFoundException;
import com.hotel.rating.system.user.model.RequestBean.UserRequestBean;
import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;
import com.hotel.rating.system.user.model.dao.User;
import com.hotel.rating.system.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ServiceResponseBean> createUser(@RequestBody UserRequestBean userRequestBean) throws DataAlreadyExistsException {
        ServiceResponseBean serviceResponseBean = this.userService.saveUser(userRequestBean);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.CREATED);
    }

    int retryCount = 1;
    @GetMapping("/{user-id}")
//    @CircuitBreaker(name = "RatingHotelBreaker", fallbackMethod = "fallBack")
//    @Retry(name = "RatingHotelBreaker", fallbackMethod = "fallBack")
//    @RateLimiter(name = "userRateLimiter", fallbackMethod = "fallBack")
    public ResponseEntity<ServiceResponseBean> getUser(@PathVariable("user-id") String userId) throws DataNotFoundException {
        log.info("Retry Count :: {}", retryCount);
        retryCount++;
        ServiceResponseBean serviceResponseBean = this.userService.getUser(userId);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

    public ResponseEntity<User> fallBack(String userId, Exception ex) {
        log.info("Fallback is executed because service is down : ", ex.getMessage());
        User user =  User.builder()
                .userId("123567890")
                .name("Dummy")
                .email("dummy@gmail.com")
                .about("Dummy user is displayed cause some service is down")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ServiceResponseBean> getAllUsers() throws DataNotFoundException {
        ServiceResponseBean serviceResponseBean = this.userService.getAllUsers();
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

}
