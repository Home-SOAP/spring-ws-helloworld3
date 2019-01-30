package com.codenotfound.thread;

import com.codenotfound.util.ExecutorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ThreadServiceBean4 {

//    @Autowired
//    @Qualifier("fixedThreadPool")
//    private ExecutorService executor; //TODO  + very fast time  + not 'java.lang.NullPointerException'

    @Autowired
    private ExecutorUtil executorUtil;

    public ThreadServiceBean4() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
    }

    public void run() throws InterruptedException, ExecutionException {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }

        Db db = new Db();

//        Future<Db> futureFun1 = executor.submit(new FunCallable(1, db));
//        Future<Db> futureFun2 = executor.submit(new FunCallable(2, db));
//        Future<Db> futureFun3 = executor.submit(new FunCallable(3, db));

//        Future<?> futureFun1 = executor.submit(taskFun(1, db));
//        Future<?> futureFun2 = executor.submit(taskFun(2, db));
//        Future<?> futureFun3 = executor.submit(taskFun(3, db));
//        futureFun1.get();
//        futureFun2.get();
//        futureFun3.get();
//        executor.shutdown();

        executorUtil.executorSubmit(taskFun(1, db), taskFun(2, db), taskFun(3, db));
        System.out.println(db);
    }

    //TODO:  Overload `fun()`
//    private class FunCallable implements Callable<Db> {
//        private int number;
//        private Db db;
//
//        FunCallable(int number, Db db) {
//            this.number = number;
//            this.db = db;
//        }
//
//        @Override
//        public Db call() {
//////            synchronized (db) { //TODO  double... time  + 'java.lang.NullPointerException'
////            synchronized (this) { //TODO  once time
////                try { Thread.sleep(3000); } catch (InterruptedException e) { }
////
////                if (number==1) db.setDate1(LocalDateTime.now());
////                if (number==2) db.setDate2(LocalDateTime.now());
////                if (number==3) db.setDate3(LocalDateTime.now());
////                System.out.println(number + ". " + LocalDateTime.now().format(ThreadPool4.DF));
////            }
//            // ////////////////////////////////
//            Lock lock = new ReentrantLock();
//            if (number==1) { try { lock.lock(); Thread.sleep(3000); } catch (InterruptedException e) { } db.setDate1(LocalDateTime.now()); lock.unlock(); }
//            if (number==2) { lock.lock(); try { Thread.sleep(3000); } catch (InterruptedException e) { } db.setDate2(LocalDateTime.now()); lock.unlock(); }
//            if (number==3) { lock.lock(); try { Thread.sleep(3000); } catch (InterruptedException e) { } db.setDate3(LocalDateTime.now()); lock.unlock(); }
//            System.out.println(number + ". " + LocalDateTime.now().format(ThreadPool4.DF));
//
//            return db;
//        }
//    }

    private Runnable taskFun(int number, Db db) {
        return () -> fun(number, db);
    }

    private void fun(int number, Db db) {
        Lock lock = new ReentrantLock();
        lock.lock();
        if (number == 1) {
            try {
                Thread.sleep(3000);
                db.setDate1(LocalDateTime.now());
            } catch (InterruptedException e) {}
        }
        if (number == 2) {
            try {
                Thread.sleep(3000);
                db.setDate2(LocalDateTime.now());
            } catch (InterruptedException e) { }
        }
        if (number == 3) {
            try {
                Thread.sleep(3000);
                db.setDate3(LocalDateTime.now());
            } catch (InterruptedException e) { }
        }
        lock.unlock();
        System.out.println(number + ". " + LocalDateTime.now().format(ThreadPool4.DF));
    }
}