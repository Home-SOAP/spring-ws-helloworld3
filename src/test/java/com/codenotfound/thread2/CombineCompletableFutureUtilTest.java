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
        CombineCompletableFuture futureThree = futureThree();        //TODO:  #3

        System.out.println("               get >>>>>>>>>>>> " + combineCompletableFutureUtil.get(futureOne, futureTwo, futureThree));
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
}