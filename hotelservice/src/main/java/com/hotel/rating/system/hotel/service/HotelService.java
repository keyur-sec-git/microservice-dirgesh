package com.hotel.rating.system.hotel.service;

import com.hotel.rating.system.hotel.exceptions.DataNotFoundException;
import com.hotel.rating.system.hotel.model.request.HotelRequestBean;
import com.hotel.rating.system.hotel.model.response.ServiceResponseBean;

public interface HotelService {
    ServiceResponseBean createHotel(HotelRequestBean hotelRequestBean);

    ServiceResponseBean getHotel(String hotelId) throws DataNotFoundException;

    ServiceResponseBean getAllHotels() throws DataNotFoundException;
}
