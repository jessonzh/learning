package com.jessonzh.learning.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class Car {

    private String band;

    private Integer price;

    @Autowired
    private Person person;

    public Car() {
        System.out.println("...create Car");
    }

    public String getBand() {
        return band;
    }

    public Car setBand(String band) {
        this.band = band;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Car setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public Car setPerson(Person person) {
        this.person = person;
        return this;
    }
}
