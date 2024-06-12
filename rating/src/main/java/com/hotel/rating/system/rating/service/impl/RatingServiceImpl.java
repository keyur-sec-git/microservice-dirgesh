package com.hotel.rating.system.rating.service.impl;

import com.hotel.rating.system.rating.exception.DataNotFoundException;
import com.hotel.rating.system.rating.model.RequestBean.RatingRequestBean;
import com.hotel.rating.system.rating.model.ResponseBean.ServiceResponseBean;
import com.hotel.rating.system.rating.model.dao.Rating;
import com.hotel.rating.system.rating.repository.RatingRepo;
import com.hotel.rating.system.rating.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceResponseBean createRating(RatingRequestBean ratingRequestBean) {
        log.info("Creating rating ");
        Rating rating = this.modelMapper.map(ratingRequestBean, Rating.class);
        Rating savedRating = this.ratingRepo.save(rating);
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Successfully created rating")
                .data(savedRating)
                .build();
        log.info("Successfully created rating ");
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getRatings() throws DataNotFoundException {
        log.info("Fetching all ratings from another service");
        List<Rating> ratingList = this.ratingRepo.findAll();
        if(ratingList.isEmpty()) {
            throw new DataNotFoundException("No rating data found");
        }
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Successfully fetched all ratings")
                .data(ratingList)
                .build();
        log.info("Successfully Fetched all ratings");
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getRatingByUserId(String userId) throws DataNotFoundException {
        log.info("Fetching all ratings by userId :: {}",userId);
        List<Rating> ratingList = this.ratingRepo.findByUserId(userId);
        if(ratingList.isEmpty()) {
            throw new DataNotFoundException("No Rating data found for this user");
        }
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Successfully Fetched all ratings for this User")
                .data(ratingList)
                .build();
        log.info("Successfully Fetched all ratings by userId :: {}",userId);
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getRatingByHotelId(String hotelId) throws DataNotFoundException {
        log.info("Fetching all ratings by hotel Id :: {}", hotelId);
        List<Rating> ratingList = this.ratingRepo.findByHotelId(hotelId);
        if(ratingList.isEmpty()) {
            throw new DataNotFoundException("No rating data found for this hotel");
        }
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Successfully Fetched all ratings for this hotel")
                .data(ratingList)
                .build();
        log.info("Fetched all ratings by hotel Id :: {}", hotelId);
        return serviceResponseBean;
    }
}
