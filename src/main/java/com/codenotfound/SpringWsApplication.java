package com.codenotfound;

import com.codenotfound.ws.service.HelloWorldClientServiceExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import static com.codenotfound.util.JmsQueue.CONCURRENT_CONSUMERS;

@SpringBootApplication
public class SpringWsApplication extends SpringBootServletInitializer implements CommandLineRunner {

  @Autowired
  private HelloWorldClientServiceExecutor helloWorldClientServiceExecutor;

  public static void main(String[] args) {
    SpringApplication.run(SpringWsApplication.class, args);

  }

  @Override
  public void run(String... args) {
//    helloWorldClientServiceExecutor.sayHello("Alex"); //TODO: start ConsoleApplication
  }

}
