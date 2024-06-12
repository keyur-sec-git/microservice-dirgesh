package com.hotel.rating.system.user.model.RequestBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestBean {

    private String name;
    private String email;
    private String about;

}
