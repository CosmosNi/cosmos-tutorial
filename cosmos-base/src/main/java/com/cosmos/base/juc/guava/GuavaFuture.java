package com.cosmos.base.juc.guava;

import com.google.common.util.concurrent.*;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.guava
 * @ClassName: GuavaFuture
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 21:52
 * @Version: 1.0
 */
public class GuavaFuture {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> task = service.submit(new RealData("a"));
//        task.addListener(()->{
//            System.out.println("异步处理成功！");
//            try {
//                System.out.println(task.get());
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        },MoreExecutors.directExecutor());

       // 添加异常处理
        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                System.out.println("异步处理成功！，result" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步处理失败！，result" + throwable.getMessage());
            }

        }, MoreExecutors.newDirectExecutorService());

        System.out.println("main task down.....");

    }


    @AllArgsConstructor
    public static class RealData implements Callable<String> {
        private String data;

        @Override
        public String call() throws Exception {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10; i++) {
                sb.append(data);
                Thread.sleep(1000);
            }
            return sb.toString();
        }
    }
}
