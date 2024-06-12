package com.hotel.rating.system.rating.practice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "classOne")
public class ClassOne implements InterfaceOne {
    @Override
    public void getName() {
        System.out.println("Keyur");
    }
}
