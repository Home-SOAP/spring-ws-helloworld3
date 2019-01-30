package com.codenotfound.thread;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ThreadService4 {

    public ThreadService4() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
    }

    public void run() throws InterruptedException, ExecutionException {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        Db db = new Db();

        Future<Db> futureFun1 = executor.submit(new FunCallable(1, db));
        Future<Db> futureFun2 = executor.submit(new FunCallable(2, db));
        Future<Db> futureFun3 = executor.submit(new FunCallable(3, db));
        futureFun1.get();
        futureFun2.get();
        futureFun3.get();

        System.out.println(db);
        executor.shutdown();
    }

    //TODO:  Overload `fun()`
    private class FunCallable implements Callable<Db> {
        private int number;
        private Db db;

        FunCallable(int number, Db db) {
            this.number = number;
            this.db = db;
        }

        @Override
        public Db call() {
//        synchronized (db) { //TODO  double... time  + 'java.lang.NullPointerException'
            synchronized (this) { //TODO  once time
                try { Thread.sleep(3000); } catch (InterruptedException e) { }

                if (number==1) db.setDate1(LocalDateTime.now());
                if (number==2) db.setDate2(LocalDateTime.now());
                if (number==3) db.setDate3(LocalDateTime.now());

                System.out.println(number + ". " + LocalDateTime.now().format(ThreadPool4.DF));
            }
            return db;
        }
    }
}
