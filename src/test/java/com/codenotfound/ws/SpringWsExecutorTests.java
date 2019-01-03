package com.codenotfound.ws;

import com.codenotfound.ws.service.HelloWorldClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringWsExecutorTests {

  @Autowired
  private HelloWorldClientService helloWorldClientService;

  private ExecutorService executor;

  public ExecutorService getExecutor() {
    return executor==null
            ? Executors.newFixedThreadPool(10)
            : executor;
  }

  @Test
  public void testSayHelloExecutors() throws ExecutionException, InterruptedException {
    //submit Callable tasks to be executed by thread pool
    Future<String> future1 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future2 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future3 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future4 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future5 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future6 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future7 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future8 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future9 = getExecutor().submit(new WsRequest("John", "Doe"));
    Future<String> future10 = getExecutor().submit(new WsRequest("John", "Doe"));

    assertThat(future1.get()).isEqualTo("Hello John Doe!");
    assertThat(future2.get()).isEqualTo("Hello John Doe!");
    assertThat(future3.get()).isEqualTo("Hello John Doe!");
    assertThat(future4.get()).isEqualTo("Hello John Doe!");
    assertThat(future5.get()).isEqualTo("Hello John Doe!");
    assertThat(future6.get()).isEqualTo("Hello John Doe!");
    assertThat(future7.get()).isEqualTo("Hello John Doe!");
    assertThat(future8.get()).isEqualTo("Hello John Doe!");
    assertThat(future9.get()).isEqualTo("Hello John Doe!");
    assertThat(future10.get()).isEqualTo("Hello John Doe!");

    //shut down the executor service now
    getExecutor().shutdown();
  }


  public Future<String> marshalSendAndReceive(Callable<String> wsRequest) {
    return getExecutor().submit(wsRequest);
  }

  @Test
  public void testMarshalSendAndReceive() throws ExecutionException, InterruptedException {
    Future<String> response1 = marshalSendAndReceive(new WsRequest("John-1", "Doe-1"));
    Future<String> response2 = marshalSendAndReceive(new WsRequest("John-2", "Doe-2"));
    Future<String> response3 = marshalSendAndReceive(new WsRequest("John-3", "Doe-3"));
    Future<String> response4 = marshalSendAndReceive(new WsRequest("John-4", "Doe-4"));
    Future<String> response5 = marshalSendAndReceive(new WsRequest("John-5", "Doe-5"));
    Future<String> response6 = marshalSendAndReceive(new WsRequest("John-6", "Doe-6"));
    Future<String> response7 = marshalSendAndReceive(new WsRequest("John-7", "Doe-7"));
    Future<String> response8 = marshalSendAndReceive(new WsRequest("John-8", "Doe-8"));
    Future<String> response9 = marshalSendAndReceive(new WsRequest("John-9", "Doe-9"));
    Future<String> response10 = marshalSendAndReceive(new WsRequest("John-10", "Doe-10"));

    assertThat(response1.get()).isEqualTo("Hello John Doe!");
    assertThat(response2.get()).isEqualTo("Hello John Doe!");
    assertThat(response3.get()).isEqualTo("Hello John Doe!");
    assertThat(response4.get()).isEqualTo("Hello John Doe!");
    assertThat(response5.get()).isEqualTo("Hello John Doe!");
    assertThat(response6.get()).isEqualTo("Hello John Doe!");
    assertThat(response7.get()).isEqualTo("Hello John Doe!");
    assertThat(response8.get()).isEqualTo("Hello John Doe!");
    assertThat(response9.get()).isEqualTo("Hello John Doe!");
    assertThat(response10.get()).isEqualTo("Hello John Doe!");

    //shut down the executor service now
    getExecutor().shutdown();
  }


  @Autowired
  @Qualifier("fixedThreadPool")
  private ExecutorService executorService;

  public Future<String> marshalSendAndReceive2(Callable<String> wsRequest) {
    return executorService.submit(wsRequest);
  }

  @Test
  public void testMarshalSendAndReceive2() throws ExecutionException, InterruptedException {
    List<Future<String>> responses = new ArrayList<>();

    for (int request=1; request<=10; request++) {
      responses.add(marshalSendAndReceive2(new WsRequest("John-" + request, "Doe-" + request)));
    }

    Thread.sleep(2990);

    for (int request=11; request<=20; request++) {
      responses.add(marshalSendAndReceive2(new WsRequest("John-" + request, "Doe-" + request)));
    }

    for (Future<String> response : responses) {
      if (response.isDone()) {
        assertThat(response.get()).isEqualTo("Hello John Doe!");
      }
    }

    //shut down the executor service now
    executorService.shutdown();
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
      return helloWorldClientService.sayHello(name, surName);
    }
  }
}
