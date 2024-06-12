package com.hotel.rating.system.user.external.services;

import com.hotel.rating.system.user.model.RequestBean.RatingRequestBean;
import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingServiceFeignClient {

    @GetMapping("/ratings/users/{user-id}")
    ServiceResponseBean getRatingByUserId(@PathVariable("user-id") String userId);

    @PostMapping("/ratings")
    ServiceResponseBean createRating(RatingRequestBean ratingRequestBean);


}
