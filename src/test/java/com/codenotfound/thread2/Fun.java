package com.codenotfound.thread2;

import java.time.LocalDateTime;

public class Fun {
    void fun(int number, Db db) {
        synchronized (this) {
//        synchronized (db) {
            try { Thread.sleep(number * 1000); } catch (InterruptedException e) {}

            if (number==1) db.setDate1(LocalDateTime.now());
            if (number==2) db.setDate2(LocalDateTime.now());
            if (number==3) db.setDate3(LocalDateTime.now());

            System.out.println(number + ". " + LocalDateTime.now().format(ThreadExample1.DF));
        }
    }
}
