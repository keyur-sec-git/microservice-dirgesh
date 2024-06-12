package com.hotel.rating.system.user.external.services;

import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE", url = "lb://HOTEL-SERVICE")
public interface HotelServiceFeignClient {

    @GetMapping("/hotels/{hotel-id}")
    ServiceResponseBean getHotel(@PathVariable("hotel-id") String hotelId);

}
