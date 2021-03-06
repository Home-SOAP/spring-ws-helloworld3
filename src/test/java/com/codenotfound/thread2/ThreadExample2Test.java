package com.codenotfound.thread2;

import com.codenotfound.util.CompletableFutureUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Java 8 CompletableFuture
 *
 * @see https://vertex-academy.com/tutorials/ru/java-8-completablefuture
 *      https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2
 *      https://kurspc.com.ua/node/424
 *      https://dzone.com/articles/getting-the-most-out-of-the-java-thread-pool
 * @see https://vertex-academy.com/tutorials/ru/java-8-uchebnik
 *      https://gist.github.com/sanaulla123/3029344
 *
 * synchronized vs ReentrantLock
 * @see http://www.javaspecialist.ru/2011/11/synchronized-vs-reentrantlock.html
 *      https://www.ibm.com/developerworks/java/library/j-jtp10264/index.html
 *      ( https://stackoverflow.com/questions/11600520/synchronized-vs-reentrantlock-on-performance )
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ThreadExample2Test {

    @Autowired
    @Qualifier("completableFutureUtil")
    private CompletableFutureUtil completableFutureUtil;

    @Test
    public void testGetCompletableFuture() throws InterruptedException, ExecutionException {
        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(ThreadExample2.DF)); //TODO:  04:48:00.658

        CompletableFuture<?> future1 = future(3);
        CompletableFuture<?> future2 = future(1);
        CompletableFuture<?> future3 = future(2);
        completableFutureUtil.get(future1, future2, future3);

        CompletableFuture<?> future5 = future(3);
        CompletableFuture<?> future6 = future(2);
        completableFutureUtil.get(future5, future6);

        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(ThreadExample2.DF)); //TODO:  04:48:06.670
    }

    @Test
    public void testThenCombineCompletableFuture() throws InterruptedException, ExecutionException {
        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(ThreadExample2.DF)); //TODO:  04:47:57.613

        CompletableFuture<?> future1 = future(3);
        CompletableFuture<?> future2 = future(1);
        CompletableFuture<?> future3 = future(2);
        completableFutureUtil.thenCombine(future1, future2, future3);

        CompletableFuture<?> future5 = future(3);
        CompletableFuture<?> future6 = future(2);
        completableFutureUtil.thenCombine(future5, future6);
        completableFutureUtil.get();

        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(ThreadExample2.DF)); //TODO:  04:48:00.643
    }

    @Test
    public void testFutureFun() throws InterruptedException, ExecutionException {
        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(ThreadExample2.DF));

        Db db = new Db();
        CompletableFuture<?> futureFun1 = futureFun(3, db);
        CompletableFuture<?> futureFun2 = futureFun(2, db);
        CompletableFuture<?> futureFun3 = futureFun(1, db);
//        completableFutureUtil.get(futureFun1, futureFun2, futureFun3);
        completableFutureUtil.thenCombine(futureFun1, futureFun2, futureFun3);
//        completableFutureUtil.get();

//        db = new Db();
        futureFun1 = futureFun(1, db);
        futureFun2 = futureFun(1, db);
        futureFun3 = futureFun(1, db);
        completableFutureUtil.thenCombine(futureFun1, futureFun2, futureFun3);
        completableFutureUtil.get();

//        db = new Db();
//        futureFun1 = futureFun(2, db);
//        futureFun2 = futureFun(2, db);
//        futureFun3 = futureFun(3, db);
//        completableFutureUtil.get(futureFun1, futureFun2, futureFun3);

        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(ThreadExample2.DF));
        System.out.println(db);
    }

    CompletableFuture<?> future(int number) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(number * 1000); } catch (InterruptedException e) {}
            System.out.println(number + ". " + LocalDateTime.now().format(ThreadExample1.DF));
            return null;
        });
    }

    CompletableFuture<?> futureFun(int number, Db db) {
        return CompletableFuture.supplyAsync(() -> {
            new Fun().fun(number, db);
            return null;
        });
    }
}