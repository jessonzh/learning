package com.jessonzh.learning.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class Person {

    private String name;

    private Integer age;

    @Autowired
    private Car car;

    public Person() {
        System.out.println("...create Person");
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Person setCar(Car car) {
        this.car = car;
        return this;
    }
}
