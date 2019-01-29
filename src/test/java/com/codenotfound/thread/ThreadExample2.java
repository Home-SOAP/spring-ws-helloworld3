package com.codenotfound.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codenotfound.thread.ThreadExample1.DF;

public class ThreadExample2 {

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {
        Db db = new Db();

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
        Thread threadFun1 = new Thread(new ThreadExample2().taskFun(1, db)); //TODO  first OR last
        Thread threadFun2 = new Thread(new ThreadExample2().taskFun(2, db)); //TODO  first OR last
        Thread threadFun3 = new Thread(new ThreadExample2().taskFun(3, db)); //TODO  first OR last

        threadFun3.start(); //TODO  first-start
        threadFun2.start(); //TODO  first-start
        threadFun1.start(); //TODO  last-start

//        while (threadFun1.isAlive() || threadFun2.isAlive()) Thread.sleep(50); //TODO  (worse)
        threadFun1.join(); //TODO  (better) last-start
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));
        System.out.println(db);
    }

    Runnable taskFun(int number, Db db) {
        return () -> fun(number, db);
    }

    void fun(int number, Db db) {
//        synchronized (db) { //TODO  double... time  + 'java.lang.NullPointerException'
        synchronized (this) { //TODO  once time
            try { Thread.sleep(3000); } catch (InterruptedException e) { }

            if (number==1) db.setDate1(LocalDateTime.now());
            if (number==2) db.setDate2(LocalDateTime.now());
            if (number==3) db.setDate3(LocalDateTime.now());

            System.out.println(number + ". " + LocalDateTime.now().format(DF));
        }
    }
}
