package com.codenotfound.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {

    @Bean("fixedThreadPool")
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //TODO:  выиграш в скорости на 3-секунды
    public ExecutorService fixedThreadPool() {
        return Executors.newFixedThreadPool(10);
    }

    @Bean("fixedThreadPoolUtil")
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //TODO:  выиграш в скорости на 3-секунды
    public ExecutorUtil executorUtil() {
        return new ExecutorUtil(Executors.newFixedThreadPool(10));
    }

    @Bean("singleThreaded")
    public ExecutorService singleThreadedExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean("cachedThreadPool")
    public ExecutorService cachedThreadPool() {
        return Executors.newCachedThreadPool();
    }

}
