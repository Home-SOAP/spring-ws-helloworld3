package com.codenotfound.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecutorUtil {

    private final ExecutorService executor;

    public ExecutorUtil(ExecutorService executor) {
        this.executor = executor;
    }

    public void executorSubmit(Runnable... tasks) throws ExecutionException, InterruptedException {
        List<Future<?>> submits = new ArrayList<>();
        for (Runnable task: tasks) submits.add(executor.submit(task));
        for (Future<?> submit: submits) submit.get();
        executor.shutdown();
    }
}
