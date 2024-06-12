package com.hotel.rating.system.hotel.service.impl;

import com.hotel.rating.system.hotel.exceptions.DataNotFoundException;
import com.hotel.rating.system.hotel.model.dao.Hotel;
import com.hotel.rating.system.hotel.model.request.HotelRequestBean;
import com.hotel.rating.system.hotel.model.response.ServiceResponseBean;
import com.hotel.rating.system.hotel.repository.HotelRepo;
import com.hotel.rating.system.hotel.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceResponseBean createHotel(HotelRequestBean hotelRequestBean) {
        log.info("Creating Hotel");
        Hotel hotel = this.modelMapper.map(hotelRequestBean, Hotel.class);
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        Hotel savedHotel = this.hotelRepo.save(hotel);
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Hotel created successfully")
                .data(savedHotel)
                .build();
        log.info("Successfully created Hotel");
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getHotel(String hotelId) throws DataNotFoundException {
        log.info("Fetching Hotel by Id :: {}", hotelId);
        Optional<Hotel> optionalHotel = this.hotelRepo.findById(hotelId);
        if(!optionalHotel.isPresent()) {
            throw new DataNotFoundException("Hotel not found");
        }
        Hotel hotel = optionalHotel.get();
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Successfully fetched hotel")
                .data(hotel)
                .build();
        log.info("Successfully fetched Hotel by Id :: {}", hotelId);
        return serviceResponseBean;
    }

    @Override
    public ServiceResponseBean getAllHotels() throws DataNotFoundException {
        log.info("Fetching all users");
        List<Hotel> hotelList = this.hotelRepo.findAll();
        if(hotelList.isEmpty()) {
            throw new DataNotFoundException("No hotel data found");
        }
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.TRUE)
                .message("Successfully fetched all users")
                .data(hotelList)
                .build();
        log.info("Successfully fetched all users");
        return serviceResponseBean;
    }

}
