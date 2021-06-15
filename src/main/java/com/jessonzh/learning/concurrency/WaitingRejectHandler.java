package com.jessonzh.learning.concurrency;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;

public class WaitingRejectHandler implements RejectedExecutionHandler {

    private ThreadLocal<SubmitRunnableInfo> task = new ThreadLocal<>();

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        SubmitRunnableInfo submitRunnableInfo = task.get();
        if (submitRunnableInfo == null) {
            submitRunnableInfo = new SubmitRunnableInfo(r, executor);
            task.set(submitRunnableInfo);
        }

        if (isSameTask(submitRunnableInfo, r, executor)) {
            submitRunnableInfo = new SubmitRunnableInfo(r, executor);
            task.set(submitRunnableInfo);
        }

        submitRunnableInfo.incrementRejectTimes();

        if (submitRunnableInfo.getRejectTimes() > 100) {
            throw new RuntimeException("Thread pool is full");
        }

        while (!executor.isShutdown()) {
            LockSupport.parkNanos(100L * submitRunnableInfo.getRejectTimes());
            if (executor.getActiveCount() < executor.getMaximumPoolSize() * 0.9) {
                executor.execute(r);
                return;
            } else {
                LockSupport.parkNanos(100000000L * submitRunnableInfo.getRejectTimes());
            }
        }
    }

    private boolean isSameTask(SubmitRunnableInfo submitRunnableInfo, Runnable r, ThreadPoolExecutor executor) {
        return submitRunnableInfo.getRunnable() != r || submitRunnableInfo.getPool() != executor;
    }
}
