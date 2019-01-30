package com.codenotfound.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecutorServiceUtil {

    private final ExecutorService executorService;

    public ExecutorServiceUtil(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void submit(Runnable... tasks) throws ExecutionException, InterruptedException {
        List<Future<?>> submits = new ArrayList<>();
        for (Runnable task: tasks) submits.add(executorService.submit(task));
        for (Future<?> submit: submits) submit.get();
        executorService.shutdown();
    }
}
