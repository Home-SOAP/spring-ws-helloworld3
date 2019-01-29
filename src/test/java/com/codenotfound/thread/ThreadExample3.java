package com.codenotfound.thread;

import com.codenotfound.ws.service.HelloWorldClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //TODO  default Statelles Bean (static) = double... time  + 'java.lang.NullPointerException'
public class ThreadExample3 {

    static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Autowired
    private HelloWorldClientService helloWorldClientService;

    Runnable taskFun(int number, Db db) {
        return () -> fun(number, db);
    }

    Runnable taskSayHello(String firstName, String lastName) {
        return () -> {
            String.valueOf(helloWorldClientService.sayHello(firstName, lastName));
            System.out.println(firstName + " " + lastName + ". " + LocalDateTime.now().format(ThreadExample3.DF));
        };
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
