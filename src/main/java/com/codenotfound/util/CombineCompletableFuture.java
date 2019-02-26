package com.codenotfound.util;

/**
 * @see https://stackoverflow.com/questions/22588518/lambda-expression-and-generic-method
 */

public interface CombineCompletableFuture <T1, T2, T3> {
    T3 get(T1 a, T2 b);
}
