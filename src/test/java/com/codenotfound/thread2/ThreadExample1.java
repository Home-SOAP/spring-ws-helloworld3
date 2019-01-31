package com.codenotfound.thread2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExample1 {

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadExample1 threadExample = new ThreadExample1();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        Db db = new Db();

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
//        Future<?> futureFun1 = executor.submit(threadExample.taskFun(1, db));
//        Future<?> futureFun2 = executor.submit(threadExample.taskFun(2, db));
//        Future<?> futureFun3 = executor.submit(threadExample.taskFun(3, db));
//        futureFun1.get();
//        futureFun2.get();
//        futureFun3.get();

//        Future<?> futureFun1 = executor.submit(threadExample.taskFun(1, db));
//        if (futureFun1.isDone()) futureFun1.get();
        executor.execute(threadExample.taskFun(1, db));
//        Future<?> futureFun2 = executor.submit(threadExample.taskFun(2, db));
//        if (futureFun2.isDone()) futureFun2.get();
        executor.execute(threadExample.taskFun(2, db));
//        Future<?> futureFun3 = executor.submit(threadExample.taskFun(3, db));
//        if (futureFun3.isDone()) futureFun3.get();
        executor.execute(threadExample.taskFun(3, db));
        while (0<executor.getActiveCount()) {
            System.out.println("getActiveCount = " + executor.getActiveCount());
        }

//        List<Future<?>> submits = new ArrayList<>();
//        for (int number=1; number<=3; number++) submits.add(executor.submit(threadExample.taskFun(number, db)));
//        for (Future<?> submit : submits) if (submit.isDone()) submit.get();
//        for (int number=1; number<=3; number++) submits.add(executor.submit(threadExample.taskFun(number, db)));
//        for (Future<?> submit : submits) submit.get();
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));

        System.out.println(db);

        executor.shutdown();
    }

    Runnable taskFun(int number, Db db) {
        return () -> {
            new Fun().fun(number, db);
        };
    }
}