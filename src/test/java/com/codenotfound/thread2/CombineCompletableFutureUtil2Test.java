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
public class CombineCompletableFutureUtil2Test {

    @Autowired
    @Qualifier("combineCompletableFutureUtil")
    private CombineCompletableFutureUtil combineCompletableFutureUtil;

    @Test
    public void testGetCompletableFuture1() throws InterruptedException, ExecutionException {
        CombineCompletableFuture futureCombine = futureCombine(); //TODO:  #3
        System.out.println("               get (1-sec. 2-sec.) >>>>>>>>>>>> A-B = " + combineCompletableFutureUtil.get(futureA(1000L, new ParamDto(20)), futureB(2000L, new ParamDto(10)), futureCombine));
        System.out.println("               get (2-sec. 1-sec.) >>>>>>>>>>>> A-B = " + combineCompletableFutureUtil.get(futureA(2000L, new ParamDto(20)), futureB(1000L, new ParamDto(10)), futureCombine));
    }

    @Test
    public void testGetCompletableFuture2() throws InterruptedException, ExecutionException {
        System.out.println("               get (1-sec. 2-sec.) >>>>>>>>>>>>   A:  " + combineCompletableFutureUtil.get(futureA(1000L, new ParamDto(10)), futureB(2000L, new ParamDto(null)), futureCombineA()));
        System.out.println("               get (1-sec. 2-sec.) >>>>>>>>>>>>   B:  " + combineCompletableFutureUtil.get(futureA(1000L, new ParamDto(null)), futureB(2000L, new ParamDto(20)), futureCombineB()));
        System.out.println("        get (B=null 1-sec. 2-sec.) >>>>>>>>>>>>   B:  " + combineCompletableFutureUtil.get(futureA(1000L, new ParamDto(null)), futureB(2000L, new ParamDto(null)), futureCombineB()));
    }

    private CompletableFuture<?> futureA(Long millis, ParamDto paramDto) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(millis); } catch (InterruptedException e) { }
            return paramDto.getParam()!=null ? paramDto : null;
        });
    }

    private CompletableFuture<?> futureB(Long millis, ParamDto paramDto) {
        return CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(millis); } catch (InterruptedException e) { }
            return paramDto.getParam()!=null ? paramDto : null;
        });
    }

    private CombineCompletableFuture futureCombine() {
        return (Object a, Object b) -> {
            ParamDto aParamDto = (ParamDto) a;
            ParamDto bParamDto = (ParamDto) b;

            //TODO:  some action
            return aParamDto.getParam() - bParamDto.getParam();
        };
    }

    private CombineCompletableFuture futureCombineA() {
        return (Object a, Object b) -> {
            ParamDto aParamDto = (ParamDto) a;

            //TODO:  some action
            return aParamDto!=null ? aParamDto : null;
        };
    }

    private CombineCompletableFuture futureCombineB() {
        return (Object a, Object b) -> {
            ParamDto bParamDto = (ParamDto) b;

            //TODO:  some action
            return bParamDto!=null ? bParamDto : null;
        };
    }
}

class ParamDto {

    private Integer param;

    public ParamDto(Integer param){
        this.param = param;
    }

    public Integer getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ParamDto{" +
                "param=" + param +
                '}';
    }
}