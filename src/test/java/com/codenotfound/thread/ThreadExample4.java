package com.codenotfound.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class ThreadExample4 {

    public ThreadExample4() {
        try { Thread.sleep(3000); } catch (InterruptedException e) { }
    }

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        Db db = new Db();

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
        Future<Db> futureFun1 = executor.submit(new ThreadExample4Callable(1, db));
        Future<Db> futureFun2 = executor.submit(new ThreadExample4Callable(2, db));
        Future<Db> futureFun3 = executor.submit(new ThreadExample4Callable(3, db));

        futureFun1.get();
        futureFun2.get();
        futureFun3.get();
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));
        System.out.println(db);

        executor.shutdown();


//        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
//        ThreadExample4 threadExample = new ThreadExample4();
//        threadExample.run(db);
//        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));
//        System.out.println(db);
    }

//    void run(Db db) throws InterruptedException {
//        Thread threadFun1 = new Thread(taskFun(1, db)); //TODO  first OR last
//        Thread threadFun2 = new Thread(taskFun(2, db)); //TODO  first OR last
//        Thread threadFun3 = new Thread(taskFun(3, db)); //TODO  first OR last
//
//        threadFun3.start(); //TODO  first-start
//        threadFun2.start(); //TODO  first-start
//        threadFun1.start(); //TODO  last-start
//
////        while (threadFun1.isAlive() || threadFun2.isAlive()) Thread.sleep(50); //TODO  (worse)
//        threadFun1.join(); //TODO  (better) last-start
}

//    Runnable taskFun(int number, Db db) {
//        return () -> fun(number, db);
//    }
//
//    void fun(int number, Db db) {
////        synchronized (db) { //TODO  double... time  + 'java.lang.NullPointerException'
//        synchronized (this) { //TODO  once time
//            try { Thread.sleep(3000); } catch (InterruptedException e) { }
//
//            if (number==1) db.setDate1(LocalDateTime.now());
//            if (number==2) db.setDate2(LocalDateTime.now());
//            if (number==3) db.setDate3(LocalDateTime.now());
//
//            System.out.println(number + ". " + LocalDateTime.now().format(DF));
//        }
//    }

class ThreadExample4Callable implements Callable<Db> {

    private int number;
    private Db db;

    public ThreadExample4Callable(int number, Db db) {
        this.number = number;
        this.db = db;
    }

    @Override
    public Db call() throws Exception {
//        synchronized (db) { //TODO  double... time  + 'java.lang.NullPointerException'
        synchronized (this) { //TODO  once time
            try { Thread.sleep(3000); } catch (InterruptedException e) { }

            if (number==1) db.setDate1(LocalDateTime.now());
            if (number==2) db.setDate2(LocalDateTime.now());
            if (number==3) db.setDate3(LocalDateTime.now());

            System.out.println(number + ". " + LocalDateTime.now().format(ThreadExample4.DF));
        }
        return db;
    }
    }

