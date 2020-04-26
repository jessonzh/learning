package com.jessonzh.learning.designpattern;

public class Singleton {

    private static volatile Singleton instance;

    private Singleton(){
        System.out.println("only once");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

