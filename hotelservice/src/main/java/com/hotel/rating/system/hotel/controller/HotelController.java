package com.hotel.rating.system.hotel.controller;

import com.hotel.rating.system.hotel.exceptions.DataNotFoundException;
import com.hotel.rating.system.hotel.model.request.HotelRequestBean;
import com.hotel.rating.system.hotel.model.response.ServiceResponseBean;
import com.hotel.rating.system.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<ServiceResponseBean> createHotel(@RequestBody HotelRequestBean hotelRequestBean) {
        ServiceResponseBean serviceResponseBean = this.hotelService.createHotel(hotelRequestBean);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.CREATED);
    }

    @GetMapping("/{hotel-id}")
    public ResponseEntity<ServiceResponseBean> getHotel(@PathVariable("hotel-id") String hotelId) throws DataNotFoundException {
        ServiceResponseBean serviceResponseBean = this.hotelService.getHotel(hotelId);
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ServiceResponseBean> getAllHotels() throws DataNotFoundException {
        ServiceResponseBean serviceResponseBean = this.hotelService.getAllHotels();
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.OK);
    }

}
