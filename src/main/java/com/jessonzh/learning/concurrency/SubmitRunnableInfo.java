package com.jessonzh.learning.concurrency;

import java.io.Serializable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class SubmitRunnableInfo implements Serializable {

    private AtomicInteger rejectTimes;

    private Runnable runnable;

    private ThreadPoolExecutor executor;

    public SubmitRunnableInfo(Runnable r, ThreadPoolExecutor executor) {
        this.runnable = r;
        this.executor = executor;
        this.rejectTimes = new AtomicInteger(0);
    }

    public AtomicInteger getRejectTimes() {
        return rejectTimes;
    }

    public void incrementRejectTimes() {
        rejectTimes.incrementAndGet();
    }
}
