package com.codenotfound.thread2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class ThreadExample2 {

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadExample2 threadExample = new ThreadExample2();

//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
//        Future<?> futureFun1 = executor.submit(threadExample.taskFun(3));
//        futureFun1.get();
//        Future<?> futureFun2 = executor.submit(threadExample.taskFun(2));
//        futureFun2.get();
//        Future<?> futureFun3 = executor.submit(threadExample.taskFun(1));
////        futureFun1.get();
////        futureFun2.get();
//        futureFun3.get();

//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> null);
//
//        future.thenApply(result -> {
//            try { Thread.sleep(3000); } catch (InterruptedException e) {}
//            System.out.println("1. " + LocalDateTime.now().format(ThreadExample1.DF));
//            return null;
//        });
//
//        future.thenApply(result -> {
//            try { Thread.sleep(2000); } catch (InterruptedException e) {}
//            System.out.println("2. " + LocalDateTime.now().format(ThreadExample1.DF));
//            return null;
//        });
//
//        future.get();

        CompletableFuture<?> future1 = threadExample.future(3);
        CompletableFuture<?> future2 = threadExample.future(2);
        CompletableFuture<?> future3 = threadExample.future(2);
        new CompletableFun2().get(future1, future2, future3);

        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));

//        executor.shutdown();
    }

    Runnable taskFun(int number) {
        return () -> new Fun2().fun(number);
    }

    CompletableFuture<?> future(int number) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(number * 1000); } catch (InterruptedException e) {}
            System.out.println(number + ". " + LocalDateTime.now().format(ThreadExample1.DF));
            return null;
        });
    }
}


class Fun2 {
    void fun(int number) {
        synchronized (this) {
            try { Thread.sleep(number * 1000); } catch (InterruptedException e) {}
            System.out.println(number + ". " + LocalDateTime.now().format(ThreadExample1.DF));
        }
    }
}

class CompletableFun2 {
    Object get(CompletableFuture<?> future, CompletableFuture<?>... futures) throws ExecutionException, InterruptedException {
        for (CompletableFuture<?> a : futures) future = future.thenCombine(a, (b,c) -> null);
        return future.get();
    }
}
