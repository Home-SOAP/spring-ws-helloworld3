package com.codenotfound.ws;

import com.codenotfound.ws.service.HelloWorldClientService;
import com.codenotfound.ws.service.HelloWorldClientServiceExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringWsExecutorServiceTests {

  private static final Logger logger = LoggerFactory.getLogger(HelloWorldClientService.class);

  @Autowired
  private HelloWorldClientService helloWorldClientService;

  @Autowired
  private HelloWorldClientServiceExecutor helloWorldClientServiceExecutor;

  @Test
  public void testMarshalSendAndReceive() {
    List<Future<String>> responses = new ArrayList<>();

    for (int request=1; request<=10; request++) {
      responses.add(helloWorldClientServiceExecutor.marshalSendAndReceive(new WsRequest("John-" + request, "Doe-" + request)));
    }

    for (Future<String> response : responses) {
      try {
        logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
//        if (response.isDone()) {
          String get = response.get();
          logger.info("                                        OK ... {}", get);
          assertThat(get).isEqualTo("Hello John Doe!");
//        }
      } catch (InterruptedException|ExecutionException e) {
        assertThat(false);
      }
    }

    //shut down the executor service now
//    executorService.shutdown();
  }

  @Test
  public void testMarshalSendAndReceiveThread() {
    Runnable task = () -> {
      try {
        String threadName = Thread.currentThread().getName();
        logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
        Future<String> response = helloWorldClientServiceExecutor.marshalSendAndReceive(new WsRequest(threadName, threadName));

//      try {
//        Thread.sleep(1000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }

//        if (response.isDone())
        String get = response.get();
          assertThat(get).isEqualTo("Hello John Doe!");
        logger.info(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< {}", get);
//        logger.info(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
      } catch (InterruptedException|ExecutionException e) {
        assertThat(false);
      }
    };

//    new Thread(task).start();
//    new Thread(task).start();
//    new Thread(task).start();
//    new Thread(task).start();
//    new Thread(task).start();

    Thread thread1 = new Thread(task);
    Thread thread2 = new Thread(task);
    Thread thread3 = new Thread(task);
    Thread thread4 = new Thread(task);
    Thread thread5 = new Thread(task);

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();

    try {
      thread1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  private class WsRequest implements Callable<String> {

    private String name;

    private String surName;

    WsRequest (String name, String surName) {
      this.name = name;
      this.surName = surName;
    }

    @Override
    public String call() {
      logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ThreadID = {}", Thread.currentThread().getId());
      return helloWorldClientService.sayHello(name, surName);
    }
  }
}
