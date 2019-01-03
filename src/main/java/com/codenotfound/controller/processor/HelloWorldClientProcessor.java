package com.codenotfound.controller.processor;

import com.codenotfound.ws.service.HelloWorldClientServiceExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.codenotfound.util.JmsQueue.CONCURRENT_CONSUMERS;

@RestController
public class HelloWorldClientProcessor {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldClientProcessor.class);

	@Autowired
	private HelloWorldClientServiceExecutor helloWorldClientServiceExecutor;

	/**
	 * http://localhost:8080/spring-ws-helloworld3-0.0.1-SNAPSHOT/sayHello/Alex
	 */
	@RequestMapping("/sayHello/{name}")
	public String sayHello(@PathVariable String name) throws ExecutionException, InterruptedException {
		logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONCURRENT_CONSUMERS = {}", CONCURRENT_CONSUMERS);

		Future<String> sayHello = helloWorldClientServiceExecutor.sayHello(name);
		return sayHello.get();
	}

}
