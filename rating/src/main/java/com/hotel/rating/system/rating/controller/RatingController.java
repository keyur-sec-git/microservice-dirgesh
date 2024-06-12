package com.hotel.rating.system.rating.controller;

import com.hotel.rating.system.rating.exception.DataNotFoundException;
import com.hotel.rating.system.rating.model.RequestBean.RatingRequestBean;
import com.hotel.rating.system.rating.model.ResponseBean.ServiceResponseBean;
import com.hotel.rating.system.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<ServiceResponseBean> createRating(@RequestBody RatingRequestBean ratingRequestBean) {
        ServiceResponseBean serviceResponseBean = this.ratingService.createRating(ratingRequestBean);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ServiceResponseBean> getRatings() throws DataNotFoundException {
        ServiceResponseBean serviceResponseBean = this.ratingService.getRatings();
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<ServiceResponseBean> getRatingByUserId(@PathVariable("user-id") String userId) throws DataNotFoundException {
        ServiceResponseBean serviceResponseBean = this.ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotel-id}")
    public ResponseEntity<ServiceResponseBean> getRatingByHotelId(@PathVariable("hotel-id") String hotelId) throws DataNotFoundException {
        ServiceResponseBean serviceResponseBean = this.ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

}
