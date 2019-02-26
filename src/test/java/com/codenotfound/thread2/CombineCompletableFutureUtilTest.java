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
        CombineCompletableFuture futureThree = futureThree();  //TODO:  #3

        CompletableFuture<?> thenCombine = combineCompletableFutureUtil.thenCombine(futureOne, futureTwo, futureThree);
        System.out.println("   thenCombine.get >>>>>>>>>>>> " + thenCombine.get());
    }

    @Test
    public void testGetCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne(10); //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo(20); //TODO:  #2
        CombineCompletableFuture futureThree = futureThree();  //TODO:  #3

        System.out.println("               get >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree));
    }

    @Test
    public void testTryGetCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne(10);  //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo(20);  //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree1()));

        futureOne = futureOne(10);  //TODO:  #1
        futureTwo = futureTwo(0);   //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree1()));
//        futureOne = futureOne(10);  //TODO:  #1
//        futureTwo = futureTwo(0);   //TODO:  #2
//        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree11()));  //TODO:  java.lang.ArithmeticException: ERROR

        futureOne = futureOne2(true);  //TODO:  #1
        futureTwo = futureTwo2(true);  //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree2()));
        futureOne = futureOne2(true);  //TODO:  #1
        futureTwo = futureTwo2(false); //TODO:  #2
        System.out.println("               try >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree2()));
    }

    @Test
    public void testGetCompletableFuture2() throws InterruptedException, ExecutionException {
        CompletableFuture<?> futureOne = futureOne3(true); //TODO:  #1
        CompletableFuture<?> futureTwo = futureTwo3(true); //TODO:  #2

        System.out.println("               get >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree3()));
    }

    private CompletableFuture<?> futureOne(int param) {
        return CompletableFuture.supplyAsync(() -> param);
    }

    private CompletableFuture<?> futureTwo(int param) {
        return CompletableFuture.supplyAsync(() -> param);
    }

    private CombineCompletableFuture futureThree() {
        return (Object a, Object b) -> {
            Integer aInteger = (Integer) a;
            Integer bInteger = (Integer) b;

            //TODO:  some action
            return aInteger + bInteger;
        };
    }

    private CombineCompletableFuture futureThree1() {
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

    private CombineCompletableFuture futureThree11() {
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

    private CombineCompletableFuture futureThree2() {
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

    private CombineCompletableFuture futureThree3() {
        return (Object a, Object b) -> {
            //TODO:  some action
            System.out.println("   Three ============= ");
            return null;
        };
    }
}