package com.hotel.rating.system.user.service;

import com.hotel.rating.system.user.exceptions.DataAlreadyExistsException;
import com.hotel.rating.system.user.exceptions.DataNotFoundException;
import com.hotel.rating.system.user.model.RequestBean.UserRequestBean;
import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;

public interface UserService {

    ServiceResponseBean saveUser(UserRequestBean userRequestBean) throws DataAlreadyExistsException;

    ServiceResponseBean getAllUsers() throws DataNotFoundException;

    ServiceResponseBean getUser(String userId) throws DataNotFoundException;

}
