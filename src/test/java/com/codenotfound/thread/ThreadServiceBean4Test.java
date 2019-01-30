package com.codenotfound.thread;

import com.codenotfound.util.ExecutorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ThreadServiceBean4Test {

    public static DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Autowired
    private ThreadServiceBean4 threadServiceBean4;

//    @Autowired
//    @Qualifier("fixedThreadPool")
//    private ExecutorService executor;

    @Autowired
    private ExecutorUtil executorUtil;

    @Test
    public void testFunThreads() throws InterruptedException, ExecutionException {
        System.out.println("   >>>>>>>>>>>> " + LocalDateTime.now().format(DF)); //TODO  01:54:16.994 | 01:58:31.472
////        threadServiceBean4.run();
////        threadServiceBean4.run();
////        threadServiceBean4.run();
//
//        //TODO: созданием бина занимается Spring и если нужно для контролера этот бин вызывается асинхронно как Future...
//        Future<?> futureServiceBean1 = executor.submit(new ThreadServiceBean4Callable());
//        Future<?> futureServiceBean2 = executor.submit(new ThreadServiceBean4Callable());
//        Future<?> futureServiceBean3 = executor.submit(new ThreadServiceBean4Callable());
//        futureServiceBean1.get();
//        futureServiceBean2.get();
//        futureServiceBean3.get();
//        executor.shutdown();

        executorUtil.executorSubmit(taskRun(), taskRun(), taskRun());
        System.out.println("   <<<<<<<<<<<< " + LocalDateTime.now().format(DF)); //TODO  01:54:32.047 | 01:58:36.517

//        executor.shutdown();
    }

    Runnable taskRun() {
        return () -> {
            try {
                threadServiceBean4.run();
            } catch (InterruptedException|ExecutionException ex) {}
        };
    }

//    class ThreadServiceBean4Callable<T> implements Callable<T> {
//
//        @Override
//        public T call() throws Exception {
//            threadServiceBean4.run();
//            return null;
//        }
//    }

}