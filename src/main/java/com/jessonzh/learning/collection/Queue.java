package com.jessonzh.learning.collection;

public interface Queue<T> {

    /**
     * 入队
     * @param element 入队元素
     * @return 是否入队成功
     */
    boolean put(T element);

    /**
     * 出队
     * @return T 出队元素
     */
    T take();

}
