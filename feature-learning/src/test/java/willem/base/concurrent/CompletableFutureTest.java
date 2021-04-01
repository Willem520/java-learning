package willem.base.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author weiyu
 * @Description
 * @Date 2019/4/29 17:27
 */
@Slf4j
public class CompletableFutureTest {

    @Test
    public void test() {
        CompletableFuture cf = CompletableFuture.completedFuture("completableFuture");
        assertTrue(cf.isDone());
        assertEquals("completableFuture", cf.getNow(null));
    }

    /**
     * 以async结尾的方法，通过ForkJoinPool使用守护线程异步执行
     */
    @Test
    public void test2() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        try {
            //中断时间大于异步执行时间
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(cf.isDone());
    }

    /**
     * 同步执行
     */
    @Test
    public void test3() {
        //thenApply接收一个方法，需要返回值
        CompletableFuture cf = CompletableFuture.completedFuture("completableFuture").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertEquals("COMPLETABLEFUTURE", cf.getNow(null));
    }

    /**
     * 使用ForkJoinPool.commonPool()异步执行
     */
    @Test
    public void test4() {
        CompletableFuture cf = CompletableFuture.completedFuture("completableFuture").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("COMPLETABLEFUTURE", cf.join());
    }

    /**
     * 使用Executor异步执行
     */
    @Test
    public void test5() {
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "custom-executor-" + count++);
            }
        });

        CompletableFuture cf = CompletableFuture.completedFuture("completableFuture").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        assertEquals("COMPLETABLEFUTURE", cf.join());
    }

    /**
     * 消费前一阶段结果
     */
    @Test
    public void test6() {
        //thenAccept接收一个consumer，不需要返回值
        StringBuffer result = new StringBuffer("test6");
        CompletableFuture.completedFuture("completableFuture").thenAccept(s -> result.append(s));
        assertTrue(result.length() > 0);
    }

    /**
     * 异步消费前一阶段结果
     */
    @Test
    public void test7() {
        //thenAccept接收一个consumer，不需要返回值
        StringBuffer result = new StringBuffer("test7");
        CompletableFuture cf =
                CompletableFuture.completedFuture("completableFuture").thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue(result.length() > 0);
    }

    /**
     * 完成计算异常
     */
    @Test
    public void test8() {
        CompletableFuture cf =
                CompletableFuture.completedFuture("completableFuture").thenApplyAsync(String::toUpperCase,
                        Executors.newSingleThreadExecutor());

        CompletableFuture exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue(cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch (CompletionException ex) { // just for testing
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }
        assertEquals("message upon cancel", exceptionHandler.join());
    }

    /**
     * 取消计算
     */
    @Test
    public void test9() {
        CompletableFuture cf =
                CompletableFuture.completedFuture("completableFuture").thenApplyAsync(String::toUpperCase,
                        Executors.newSingleThreadExecutor());
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        assertTrue(cf.cancel(true));
        assertTrue(cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());
    }

    @Test
    public void test10() {
        String original = "completableFuture";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture cf2 =
                cf.applyToEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        s -> s +
                                "from applyToEither");
        assertTrue(cf2.join().toString().endsWith(" from applyToEither"));
    }

    @Test
    public void test11() {
        String original = "completableFuture";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        assertTrue(result.toString().endsWith("acceptEither"));
    }

    @Test
    public void test12() {
        String original = "completableFuture";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .runAfterBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        () -> result.append("done"));
        assertTrue(result.length() > 0);
    }

    @Test
    public void test13(){
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
    }

    @Test
    public void test14(){
        String original = "Message";
        //thenCombine复合两阶段结果
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
    }

    @Test
    public void test15(){
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.join());
    }

    @Test
    public void test16(){
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                        .thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    @Test
    public void test17(){
        StringBuffer result = new StringBuffer();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures =
                messages.stream().map(msg->CompletableFuture.completedFuture(msg).thenApply(s->delayedUpperCase(s))).collect(
                Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th)->{
            if (th == null){
                result.append(res);
            }
        });
        assertTrue(result.length()>0);
    }

    @Test
    public void test18(){
        StringBuffer result = new StringBuffer();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures =
                messages.stream().map(msg->CompletableFuture.completedFuture(msg).thenApply(s->delayedUpperCase(s))).collect(
                        Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v,th)->{
            futures.forEach(cf->assertTrue(isUpperCase(cf.getNow(null))));
        });
    }

    /**
     * 随机休眠0-5秒
     */
    private void randomSleep() {
        Random rand = new Random();
        try {
            int sleepTime = rand.nextInt(6) * 1000;
            System.out.println("******randomSleep "+sleepTime+" ms");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String delayedUpperCase(String word) {
        randomSleep();
        return word.toUpperCase();
    }

    private String delayedLowerCase(String word) {
        randomSleep();
        return word.toLowerCase();
    }

    private boolean isUpperCase(Object word){
        if (word == null){
            return false;
        }
        String strWord = word.toString();
        String upperWord = strWord.toUpperCase();
        return strWord.equals(upperWord)? true:false;
    }
}
