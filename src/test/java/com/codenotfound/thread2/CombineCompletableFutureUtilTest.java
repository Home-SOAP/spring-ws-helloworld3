package com.codenotfound.thread2;

import com.codenotfound.util.CombineCompletableFuture;
import com.codenotfound.util.CombineCompletableFutureUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Java 8 CompletableFuture
 * ***
 * @see https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2
 *      https://annimon.com/article/3462
 * @see https://kurspc.com.ua/node/424
 * @see https://stackoverflow.com/questions/22588518/lambda-expression-and-generic-method
 *
 * Руководство по CompletableFuture
 * https://www.codeflow.site/ru/article/java-completablefuture
 *
 * ***
 * CompletableFuture.allOf
 * http://qaru.site/questions/16237790/how-to-combine-multiple-completionstage-responses-of-type-listfor-me-or-some-other-in-java
 * https://github.com/te21wals/CompletableFuturesDemo/blob/master/src/main/java/Main.java
 *
 * Thread'ом Java не испортишь: Часть IV - Callable, Future и друзья
 * https://javarush.ru/groups/posts/2065-threadom-java-ne-isportishjh--chastjh-iv---callable-future-i-druzjhja
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CombineCompletableFutureUtilTest {

    @Autowired
    @Qualifier("combineCompletableFutureUtil")
    private CombineCompletableFutureUtil combineCompletableFutureUtil;

    @Test
    public void testGetCombineCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne(10); //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo(20); //TODO:  #2
        CombineCompletableFuture futureCombine = futureCombine();  //TODO:  #3

        CompletableFuture<?> thenCombine = combineCompletableFutureUtil.thenCombine(futureOne, futureTwo, futureCombine);
        System.out.println("   thenCombine.get >>>>>>>>>>>> " + thenCombine.get());
    }

    @Test
    public void testGetCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne(10); //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo(20); //TODO:  #2
        CombineCompletableFuture futureCombine = futureCombine();  //TODO:  #3

        System.out.println("               get >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine));
    }

    @Test
    public void testTryGetCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne(10);  //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo(20);  //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine1()));

        futureOne = futureOne(10);  //TODO:  #1
        futureTwo = futureTwo(0);   //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine1()));
//        futureOne = futureOne(10);  //TODO:  #1
//        futureTwo = futureTwo(0);   //TODO:  #2
//        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine11()));  //TODO:  java.lang.ArithmeticException: ERROR

        futureOne = futureOne2(true);  //TODO:  #1
        futureTwo = futureTwo2(true);  //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine2()));
        futureOne = futureOne2(true);  //TODO:  #1
        futureTwo = futureTwo2(false); //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine2()));
    }

    @Test
    public void testGetCompletableFuture2() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne3(true); //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo3(true); //TODO:  #2

        System.out.println("               get >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureCombine3()));
    }

    private CompletableFuture<?> futureOne(int param) {
        return CompletableFuture.supplyAsync(() -> param);
    }

    private CompletableFuture<?> futureTwo(int param) {
        return CompletableFuture.supplyAsync(() -> param);
    }

    private CombineCompletableFuture futureCombine() {
        return (Object a, Object b) -> {
            Integer aInteger = (Integer) a;
            Integer bInteger = (Integer) b;

            //TODO:  some action
            return aInteger + bInteger;
        };
    }

    private CombineCompletableFuture futureCombine1() {
        return (Object a, Object b) -> {
            Integer aInteger = (Integer) a;
            Integer bInteger = (Integer) b;

            //TODO:  some action
            try {
                int result = aInteger / bInteger;
                return true;
            } catch (ArithmeticException ex) {
                return false;
            }
        };
    }

    private CombineCompletableFuture futureCombine11() {
        return (Object a, Object b) -> {
            Integer aInteger = (Integer) a;
            Integer bInteger = (Integer) b;

            //TODO:  some action
            try {
                int result = aInteger / bInteger;
                return true;
            } catch (ArithmeticException ex) {
                throw new ArithmeticException("ERROR");
            }
        };
    }


    private CompletableFuture<?> futureOne2(boolean param) {
        return CompletableFuture.supplyAsync(() -> {
            return param;
        });
    }

    private CompletableFuture<?> futureTwo2(boolean param) {
        return CompletableFuture.supplyAsync(() -> {
            return param;
        });
    }

    private CombineCompletableFuture futureCombine2() {
        return (Object a, Object b) -> {
            Boolean aBoolean = (Boolean) a;
            Boolean bBoolean = (Boolean) b;

            //TODO:  some action
            return aBoolean && bBoolean;
        };
    }

    private CompletableFuture<?> futureOne3(boolean param) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("   One >>>>>>>>>>>>>>> " + param);
            return null;
        });
    }

    private CompletableFuture<?> futureTwo3(boolean param) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("   Two >>>>>>>>>>>>>>> " + param);
            return null;
        });
    }

    private CombineCompletableFuture futureCombine3() {
        return (Object a, Object b) -> {
            //TODO:  some action
            System.out.println("   Three ============= ");
            return null;
        };
    }
}