package com.cosmos.base.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @ProjectName: cosmos-stream
 * @Package: com.cosmos.collections.simple
 * @ClassName: ToListCollector
 * @Author: keda
 * @Description: supplier:是一个容器提供者，提供容器A,比如：List list = new ArrayList()；
 * accumulator:是要操作的集合的每个元素以怎样的形式添加到supplier提供的容器A当中，即做累加操作，比如：List.add(item)；
 * combiner:用于在多线程并发的情况下，每个线程都有一个supplier和，如果有N个线程那么就有N个supplier提供的容器A，执行的是类似List.addAll(listB)这样的操作,只有在characteristics没有被设置成CONCURRENT并且是并发的情况下 才会被调用。ps：characteristics被设置成CONCURRENT时，整个收集器只有一个容器，而不是每个线程都有一个容器，此时combiner()方法不会被调用，这种情况会出现java.util.ConcurrentModificationException异常,此时需要使用线程安全的容器作为supplier返回的对象。
 * finisher:是终止操作，如果收集器的characteristics被设置成IDENTITY_FINISH，那么会将中间集合A牵制转换为结果R类型，如果A和R没有父子之类的继承关系，会报类型转换失败的错误，如果收集器的characteristics没有被设置成IDENTITY_FINISH，那么finisher()方法会被调用，返回结果类型R。
 * @Date: 2019/1/20 21:54
 * @Version: 1.0
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {


    /**
     * 创建一个集合
     *
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 新增对象
     *
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 修改第一个累加器，将其与第二个累加器的内容合并
     *
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {

        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 恒等函数
     *
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }

    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1, 5).boxed()
                .collect(new ToListCollector<>());
        System.out.println(list);

        List<Integer> list2 = Stream.iterate(1, i -> i + 1).limit(5)
                .collect(new ToListCollector<>());
        System.out.println(list2);
    }
}
