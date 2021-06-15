package com.jessonzh.learning.concurrency;

import java.io.Serializable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class SubmitRunnableInfo implements Serializable {

    private AtomicInteger rejectTimes;

    private Runnable runnable;

    private ThreadPoolExecutor pool;

    public SubmitRunnableInfo(Runnable r, ThreadPoolExecutor pool) {
        this.runnable = r;
        this.pool = pool;
        this.rejectTimes = new AtomicInteger(0);
    }

    public AtomicInteger getRejectTimes() {
        return rejectTimes;
    }

    public void incrementRejectTimes() {
        rejectTimes.incrementAndGet();
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public SubmitRunnableInfo setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public ThreadPoolExecutor getPool() {
        return pool;
    }

    public SubmitRunnableInfo setPool(ThreadPoolExecutor pool) {
        this.pool = pool;
        return this;
    }
}
