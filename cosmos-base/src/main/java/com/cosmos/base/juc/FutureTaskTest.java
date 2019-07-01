package com.cosmos.base.juc;

import java.util.concurrent.*;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: FutureTaskTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 10:04
 * @Version: 1.0
 */
public class FutureTaskTest {
    private ExecutorService service = Executors.newFixedThreadPool(10);

    //执行单个任务
    public Double result() throws ExecutionException, InterruptedException {
        CallableResult callableResult = new CallableResult();
        Future futureTask = service.submit(callableResult);
        service.shutdown();
        //FutureTask ft = new FutureTask(callableResult);

        return (Double) futureTask.get();
    }

    //执行多个future
    public Double reply() throws ExecutionException, InterruptedException {

        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> (Math.random()))
                .thenApply(data -> data * 100)
                .whenComplete((r, e) -> System.out.println(r));
        return future.get();
    }

    public class CallableResult implements Callable<Double> {

        @Override
        public Double call() throws Exception {
            return Math.random();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTaskTest futureTaskTest = new FutureTaskTest();
        System.out.println(futureTaskTest.result());

        futureTaskTest.reply();
    }
}
