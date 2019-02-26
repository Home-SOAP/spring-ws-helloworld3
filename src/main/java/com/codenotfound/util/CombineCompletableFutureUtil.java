package com.codenotfound.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineCompletableFutureUtil {

    public CompletableFuture<?> thenCombine(CompletableFuture<?> future1, CompletableFuture<?> future2, CombineCompletableFuture futureCombine) {
        return future1.thenCombine(future2, (a,b) -> futureCombine.get(a, b));
    }

    public Object get(CompletableFuture<?> future1, CompletableFuture<?> future2, CombineCompletableFuture futureCombine) throws ExecutionException, InterruptedException {
        return future1.thenCombine(future2, (a,b) -> futureCombine.get(a, b)).get();
    }
}
