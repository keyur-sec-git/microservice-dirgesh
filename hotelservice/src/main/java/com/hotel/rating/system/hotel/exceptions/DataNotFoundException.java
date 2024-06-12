package com.hotel.rating.system.hotel.exceptions;

public class DataNotFoundException extends Throwable {

    private final static long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
        super(message);
    }

}
