package com.jessonzh.learning.jvm;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * -Xss256k
 */
@Slf4j(topic = "StackOverflowErrorDemo")
public class StackOverflowErrorDemo {

    public static void deathCycle() {
        deathCycle();
    }

    public static void main(String args[]) {
        try {
            deathCycle();
        } catch (Throwable t) {
            // 注意此处catch的是Throwable接口
            Marker fatal = MarkerFactory.getMarker("FATAL");
            log.error(fatal, "Enter in a death cycle : ", t);
        }
    }
}