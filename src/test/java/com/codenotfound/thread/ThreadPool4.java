package com.codenotfound.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class ThreadPool4 {

    public static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF)); //TODO  01:30:21.905 | 01:38:31.201
//        new ThreadService4().run();
//        new ThreadService4().run();
//        new ThreadService4().run();

        Future<Void> futureService1 = executor.submit(new ThreadService4Callable());
        Future<Void> futureService2 = executor.submit(new ThreadService4Callable());
        Future<Void> futureService3 = executor.submit(new ThreadService4Callable());
        futureService1.get();
        futureService2.get();
        futureService3.get();
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF)); //TODO  01:30:36.932 | 01:38:36.294

        executor.shutdown();
    }
}

class ThreadService4Callable implements Callable<Void> {

    @Override
    public Void call() throws Exception {
        new ThreadService4().run();
        return null;
    }
}
