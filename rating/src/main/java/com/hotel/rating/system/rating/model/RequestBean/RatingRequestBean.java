package com.hotel.rating.system.rating.model.RequestBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingRequestBean {

    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

}
