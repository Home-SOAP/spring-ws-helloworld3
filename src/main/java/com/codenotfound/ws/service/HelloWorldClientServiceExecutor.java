package com.codenotfound.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HelloWorldClientServiceExecutor {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClientServiceExecutor.class);

    @Autowired
    @Qualifier("fixedThreadPool")
    private ExecutorService executorService;

    @Autowired
    private HelloWorldClientService helloWorldClientService;

    public Future<String> marshalSendAndReceive(Callable<String> wsRequest) {
        return executorService.submit(wsRequest);
    }

    public Future<String> sayHello(String name) {
        return executorService.submit(new StringCallable(name, name));
    }


    private class StringCallable implements Callable<String> {

        private String name;

        private String surName;

        StringCallable (String name, String surName) {
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
