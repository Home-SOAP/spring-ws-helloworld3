package com.codenotfound.thread2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @see https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2
 *      https://annimon.com/article/3462
 * @see https://kurspc.com.ua/node/424
 */
public class ThenCombineDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test11();
    }

    static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> futureOne = CompletableFuture.supplyAsync(() -> 1);   //TODO:  #1
        CompletableFuture<Integer> futureTwo = CompletableFuture.supplyAsync(() -> 2);   //TODO:  #2

        CompletableFuture<Integer> resultFuture = futureOne.thenCombine(futureTwo, (one, two) -> one + two);
        System.out.println(resultFuture.get()); //output 3
    }

    static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> futureOne = CompletableFuture.supplyAsync(() -> 1);   //TODO:  #1
        CompletableFuture<Integer> futureTwo = CompletableFuture.supplyAsync(() -> 2);   //TODO:  #2
        CompletableFuture<Integer> futureThree = CompletableFuture.supplyAsync(() -> 3); //TODO:  #3

        CompletableFuture<Integer> resultFuture = futureOne.thenCombine(futureTwo, (one, two) -> one + two)
                .thenCombine(futureThree, (two, three) -> two + three);
        System.out.println(resultFuture.get()); //output 6
    }

    /**
     * @see https://kurspc.com.ua/node/424
     * @see https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm
     *      https://www.baeldung.com/java-8-lambda-expressions-tips
     */
    static void test11() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> futureOne = futureOne(10); //TODO:  #1
        CompletableFuture<Integer> futureTwo = futureTwo(20); //TODO:  #2
        MyCompletableFuture futureThree = futureThree();             //TODO:  #3

//        System.out.println(futureThree.operation(1, 2));
        System.out.println(resultFuture(futureOne, futureTwo, futureThree).get()); //output 30
    }

    static CompletableFuture<Integer> futureOne(int param) {
        return CompletableFuture.supplyAsync(() -> param);
    }

    static CompletableFuture<Integer> futureTwo(int param) {
        return CompletableFuture.supplyAsync(() -> param);
    }

    static MyCompletableFuture futureThree() {
        return (int one, int two) -> {
            return one + two; //TODO:  some action
        };
    }

    static CompletableFuture<Integer> resultFuture(CompletableFuture<Integer> futureOne, CompletableFuture<Integer> futureTwo, MyCompletableFuture futureThree) {
        return futureOne.thenCombine(futureTwo, (one, two) -> futureThree.operation(one, two));
    }
}

interface MyCompletableFuture {
    int operation(int one, int two);
}
