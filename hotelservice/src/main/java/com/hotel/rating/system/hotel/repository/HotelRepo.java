package com.hotel.rating.system.hotel.repository;

import com.hotel.rating.system.hotel.model.dao.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, String> {
}
