package com.jessonzh.learning.concurrency;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

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



    }

    private boolean isSameTask(SubmitRunnableInfo submitRunnableInfo, Runnable r, ThreadPoolExecutor executor) {
        return submitRunnableInfo.getRunnable() != r || submitRunnableInfo.getPool() != executor;
    }
}
