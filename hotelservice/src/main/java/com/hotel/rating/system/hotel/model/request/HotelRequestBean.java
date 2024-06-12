package com.hotel.rating.system.hotel.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelRequestBean {

    private String name;
    private String location;
    private String about;

}
