package com.codenotfound.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureUtil {

    private CompletableFuture<?> future;

    public void thenCombine(CompletableFuture<?> future, CompletableFuture<?>... futures) {
        for (CompletableFuture<?> a : futures) future = future.thenCombine(a, (b,c) -> null);
        this.future = (this.future!=null) ? this.future.thenCombine(future, (b,c) -> null) : future;
    }

    public Object get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    public Object get(CompletableFuture<?> future, CompletableFuture<?>... futures) throws ExecutionException, InterruptedException {
        for (CompletableFuture<?> a : futures) future = future.thenCombine(a, (b,c) -> null);
        return future.get();
    }
}
