package com.hotel.rating.system.user.exceptions;

public class DataAlreadyExistsException extends Throwable{

    private static final long serialVersionUID = 1L;

    public DataAlreadyExistsException(String message) {
        super(message);
    }

}
