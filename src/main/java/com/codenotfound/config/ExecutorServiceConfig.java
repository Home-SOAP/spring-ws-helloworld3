package com.codenotfound.config;

import com.codenotfound.util.CompletableFutureUtil;
import com.codenotfound.util.ExecutorServiceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {

    @Bean("completableFutureUtil")
    public CompletableFutureUtil completableFutureUtil() {
        return new CompletableFutureUtil();
    }

    @Bean("newFixedThreadPool") //TODO:   (11:02:35.085 - 11:02:43.127)
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //TODO:  выиграш в скорости на 3-секунды   (11:03:08.009 - 11:03:13.053)
    public ExecutorServiceUtil executorUtil() {
        return new ExecutorServiceUtil(Executors.newFixedThreadPool(10));
    }

    @Bean("fixedThreadPool")
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //TODO:  выиграш в скорости на 3-секунды
    public ExecutorService fixedThreadPool() {
        return Executors.newFixedThreadPool(10);
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
