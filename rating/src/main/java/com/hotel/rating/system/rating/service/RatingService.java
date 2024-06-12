package com.hotel.rating.system.rating.service;

import com.hotel.rating.system.rating.exception.DataNotFoundException;
import com.hotel.rating.system.rating.model.RequestBean.RatingRequestBean;
import com.hotel.rating.system.rating.model.ResponseBean.ServiceResponseBean;

public interface RatingService {
    ServiceResponseBean createRating(RatingRequestBean ratingRequestBean);

    ServiceResponseBean getRatings() throws DataNotFoundException;

    ServiceResponseBean getRatingByUserId(String userId) throws DataNotFoundException;

    ServiceResponseBean getRatingByHotelId(String hotelId) throws DataNotFoundException;
}
