package com.codenotfound.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Component
public class ExecutorUtil {

    @Autowired
    @Qualifier("fixedThreadPool")
    private ExecutorService executor; //TODO  + very fast time  + not 'java.lang.NullPointerException'

    public void executorSubmit(Runnable... tasks) throws ExecutionException, InterruptedException {
        List<Future<?>> submits = new ArrayList<>();
        for (Runnable task: tasks) submits.add(executor.submit(task));
        for (Future<?> submit: submits) submit.get();
        executor.shutdown();
    }
}
