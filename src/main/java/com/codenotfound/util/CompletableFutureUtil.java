package com.codenotfound.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureUtil {

    private CompletableFuture<?> future;

    public synchronized void thenCombine(CompletableFuture<?> future, CompletableFuture<?>... futures) {
        for (CompletableFuture<?> a : futures) future = future.thenCombine(a, (b,c) -> null);
        this.future = (this.future!=null) ? this.future.thenCombine(future, (b,c) -> null) : future;
    }

//    public Object get() throws ExecutionException, InterruptedException {
//        return (future!=null) ? future.get() : null;
//    }
    public synchronized Object get() throws ExecutionException, InterruptedException {
        if (future!=null) {
            Object get = future.get();
            future = null;
            return get;
        }
        return null;
    }

    public synchronized Object get(CompletableFuture<?> future, CompletableFuture<?>... futures) throws ExecutionException, InterruptedException {
        for (CompletableFuture<?> a : futures) future = future.thenCombine(a, (b,c) -> null);
        return future.get();
    }
}
