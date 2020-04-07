package com.jessonzh.learning.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx20m -Xms10m 堆内存最大20MB，最小20MB
 * -Xmn 新生代内存大小
 */
@Slf4j(topic = "CycleNewObjectDemo")
public class CycleNewObjectDemo {

    public static void main(String[] args) {
        log.warn("Hello World");
        while (true) {
            List<String> list = new ArrayList<>();
        }
    }
}
