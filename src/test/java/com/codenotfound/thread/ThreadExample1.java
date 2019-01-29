package com.codenotfound.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.codenotfound.thread.ThreadExample1.DF;

public class ThreadExample1 {

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {
        Db1 db = new Db1();

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
        Thread threadFun1 = new Thread(new ThreadExample1().taskFun(1, db)); //TODO  first OR last
        Thread threadFun2 = new Thread(new ThreadExample1().taskFun(2, db)); //TODO  first OR last

        threadFun2.start(); //TODO  first-start
        threadFun1.start(); //TODO  last-start

//        while (threadFun1.isAlive() || threadFun2.isAlive()) Thread.sleep(50); //TODO  (worse)
        threadFun1.join(); //TODO  (better) last-start
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));
        System.out.println(db);
    }

    public Runnable taskFun(int number, Db1 db1) {
        return () -> new Worker1().fun(number, db1);
    }
}

class Worker1 {
    void fun(int number, Db1 db) {
//        synchronized (db) { //TODO  double time
        synchronized (this) { //TODO  once time
            try { Thread.sleep(3000); } catch (InterruptedException e) { }

            if (number==1) db.setDate1(LocalDateTime.now());
            if (number==2) db.setDate2(LocalDateTime.now());

            System.out.println(number + ". " + LocalDateTime.now().format(DF));
        }
    }
}

class Db1 {
    private LocalDateTime date1;
    private LocalDateTime date2;

    public LocalDateTime getDate1() {
        return date1;
    }

    public void setDate1(LocalDateTime date1) {
        this.date1 = date1;
    }

    public LocalDateTime getDate2() {
        return date2;
    }

    public void setDate2(LocalDateTime date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "{" +
                "date1=" + date1.format(DF) +
                ", date2=" + date2.format(DF) +
                '}';
    }
}
