package com.codenotfound.thread;

import com.codenotfound.ws.service.HelloWorldClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ThreadExample3Test {

//    @Autowired
//    HelloWorldClientService helloWorldClientService;

    @Autowired
    private ThreadExample3 threadExample;

    @Test
    public void testFunThreads() throws InterruptedException {
        Db db = new Db();

        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(ThreadExample3.DF));
        Thread threadFun1 = new Thread(threadExample.taskFun(1, db)); //TODO  first OR last
        Thread threadFun2 = new Thread(threadExample.taskFun(2, db)); //TODO  first OR last
        Thread threadFun3 = new Thread(threadExample.taskFun(3, db)); //TODO  first OR last

        threadFun3.start(); //TODO  first-start
        threadFun2.start(); //TODO  first-start
        threadFun1.start(); //TODO  last-start

//        while (threadFun1.isAlive() || threadFun2.isAlive()) Thread.sleep(50); //TODO  (worse)
        threadFun1.join(); //TODO  (better) last-start
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(ThreadExample3.DF));
        System.out.println(db);
    }

    @Test
    public void testSayHelloThreads() throws InterruptedException {
        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(ThreadExample3.DF));
//        String sayHelloResponse1 = helloWorldClientService.sayHello("John-1", "Doe-1") + " " + LocalDateTime.now().format(ThreadExample3.DF);
//        String sayHelloResponse2 = helloWorldClientService.sayHello("John-2", "Doe-2") + " " + LocalDateTime.now().format(ThreadExample3.DF);
//        String sayHelloResponse3 = helloWorldClientService.sayHello("John-3", "Doe-3") + " " + LocalDateTime.now().format(ThreadExample3.DF);

        Thread threadSayHello1 = new Thread(threadExample.taskSayHello("John-1", "Doe-1")); //TODO  first OR last
        Thread threadSayHello2 = new Thread(threadExample.taskSayHello("John-2", "Doe-2")); //TODO  first OR last
        Thread threadSayHello3 = new Thread(threadExample.taskSayHello("John-3", "Doe-3")); //TODO  first OR last
        threadSayHello3.start(); //TODO  first-start
        threadSayHello2.start(); //TODO  first-start
        threadSayHello1.start(); //TODO  last-start
//        while (threadFun1.isAlive() || threadFun2.isAlive()) Thread.sleep(50); //TODO  (worse)
        threadSayHello1.join(); //TODO  (better) last-start
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(ThreadExample3.DF));
    }
}
