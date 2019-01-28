package com.codenotfound.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.codenotfound.thread.ThreadExample.DF;

public class ThreadExample {

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {
//        Worker worker = new Worker();
//
//        System.out.println("   " + LocalDateTime.now().format(DF));
//        System.out.println("   >>>>>>>>>>>>");
//        for (int number = 1; number<=5; number++) System.out.println(worker.toDo(number));
//        System.out.println("   <<<<<<<<<<<<");
//
//        System.out.println("\n   " + LocalDateTime.now().format(DF));
//        System.out.println("   >>>>>>>>>>>>");
//        for (int number = 1; number<=5; number++) new Thread(new ThreadExample().taskToDo(number)).start();
//        System.out.println("   <<<<<<<<<<<<");


        MyDb myDb = new MyDb();

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF));
        Thread threadFun1 = new Thread(new ThreadExample().taskFun(1, myDb)); //TODO  first~last
        Thread threadFun2 = new Thread(new ThreadExample().taskFun(2, myDb)); //TODO  first~last

        threadFun2.start(); //TODO  first
        threadFun1.start(); //TODO  last

//        while (threadToDo1.isAlive() || threadToDo2.isAlive()) Thread.sleep(50); //TODO  mistake
        threadFun1.join(); //TODO  (good) last
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF));
        System.out.println(myDb);
    }

    public Runnable taskFun(int number, MyDb myDb) {
//        return () -> System.out.println(new Worker().toDo(number, myMyDb));
        return () -> new Worker().fun(number, myDb);
    }
}

class Worker {
//    public String toDo(int number, MyDb myCase) {
//        fun(number, myCase);
//        return number + ". " + LocalDateTime.now().format(DF);
//    }

    void fun(int number, MyDb myDb) {
//        synchronized (myDb) { //TODO  double time
        synchronized (this) { //TODO  once time
            try { Thread.sleep(3000); } catch (InterruptedException e) { }
            if (number==1) {
                myDb.setDate1(LocalDateTime.now());
            }

            if (number==2) {
                myDb.setDate2(LocalDateTime.now());
            }
        }
    }
}

class MyDb {
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
