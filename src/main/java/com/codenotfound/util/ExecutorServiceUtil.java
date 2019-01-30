package com.codenotfound.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//@Component
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExecutorServiceUtil {

    private ExecutorService executorService;

    public ExecutorServiceUtil(ExecutorService executorService) {
        this.executorService = executorService;
    }

////    @PostConstruct
////    public ExecutorServiceUtil executorService(ExecutorService executorService) {
////        this.executorService = executorService;
////        return this;
////    }
//
//    @PostConstruct
//    public void init() {
//        executorService = Executors.newFixedThreadPool(10);
//    }
//
//    @PreDestroy
//    public void destroy() {
//        executorService.shutdown();
//    }

    public void submit(Runnable... tasks) throws ExecutionException, InterruptedException {
        List<Future<?>> submits = new ArrayList<>();
        for (Runnable task: tasks) submits.add(executorService.submit(task));
        for (Future<?> submit: submits) submit.get();
    }

    //TODO:  todo get result...
    public void submit(Callable... tasks) throws ExecutionException, InterruptedException {
        List<Future<?>> submits = new ArrayList<>();
        for (Callable task: tasks) submits.add(executorService.submit(task));
        for (Future<?> submit: submits) submit.get();
    }

    @Override
    protected void finalize() {
        executorService.shutdown();
    }
}
