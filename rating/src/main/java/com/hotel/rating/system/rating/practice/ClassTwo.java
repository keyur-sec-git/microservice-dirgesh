package com.hotel.rating.system.rating.practice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "classTwo")
public class ClassTwo implements InterfaceOne {
    @Override
    public void getName() {
        System.out.println("Ankit");
    }
}
