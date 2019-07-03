
## 1.cosmos-base
主要存放jdk的相关基础知识。包括juc，lambda，stream等相关知识。

### 1.1 java基础理论知识
#### 1.1.1 对象
   - 万物皆对象
   - 程序是一组对象，通过信息传递告知彼此该做什么。
   - 每个对象都有自己的存储空间，可容纳其他对象。
   - 每个对象都有一个类型。每个对象都是某个类的实例。
   - 同一类的对象都能接口相同的类型。

一个对象具有自己的状态，行为和身份。这意味着对象都有自己的内部数据（由状态提供），方法（由特性提供），并彼此区分（每个对象在内存中都有唯一的地址。）

#### 1.1.2 抽象类
   - 抽象方法：只有声明，没有具体的实现。
   - 如果有一个类含有抽象方法，则这个类抽象类，需由abstract修饰。
   特点：
   - 抽象方法必须是public或者protected
   - 抽象类不能实例化
   - 如果子类不是不是抽象类，则必须实现父类的抽象方法

#### 1.1.3 接口
   - 接口中定义的变量被隐式指定为public static final。
   - 接口中所有的方法不能有具体的实现。在java8中，可用default关键字在接口中实现默认方法。
    
   接口与抽象类的区别：
   - 抽象类中的成员变量可以是各种类型的，而接口中的变量是public static final
   - 接口不能有静态代码块以及静态方法，而抽象方法可以
   - 一个类只能实现一个抽象类，但是一个类可以实现多个接口。
   - 抽象类是对事物的抽象，接口是对行为的抽象

#### 1.1.4 Object相关注意点
##### 1.1.4.1 重写equals方法注意点
   - 自反性：对于任何非空引用，x.equals(x)应该返回true
   - 对称性：对于任何引用x和y，如果x.equals(y)返回为true，那么y.equals(x)也应该返回true
   - 传递性：对于任何引用，x，y和z，如果x.equals(y)返回为true，那么y.equals(x)也应该返回true
   - 一致性：如果x和y引用的对象没有发生变化，返回调用x.equals(y)应该返回相同的结果
   - 对于任何非空引用x，x.equals(null)应该返回false


#### 1.1.5 对象序列化
  定义：对象序列化是以特殊的文件格式存储对象数据的。

#### 1.1.6 线程状态
- New（新创建）
- Runnable（可运行）
- Blocked（被堵塞）
- Waiting（等待）
- Timed（waiting）
- Terminated（被终止）
- 新创建一个新的线程对象对象后，在调用他的start（）方法，系统会为此线程分配CPU资源，使其处于Runnable（可运行状态），这是一个准备
运行的阶段。如果线程抢占到CPU资源，此线程就处于Rinning（运行）状态。
- Runnable状态和Running状态可相互切换，因为有可能线程运行一段时间后，有其他高优先级的线程抢占了CPU资源，这时Running状态变成了Runnable状态。
线程进入Runnable状态大体分为如下几种情况：
   - 调用sleep（）方法后经过的时间超过了指定的休眠时间
   - 线程调用的堵塞IO已经返回，堵塞方法执行完毕 
   - 线程成功地获取了试图同步的监视器
   - 线程处于等待某个通知，其他线程发出了通知。
   - 处于挂起状态的线程调用了resume回复方法。
- 堵塞：例如遇到一个IO操作，此时CPU处于空闲状态，可能会转而把CPU时间片分配给其他的线程，这是也可以成为“暂停”状态。
   - 线程调用sleep（）方法，主动放弃占用的处理器资源。
   - 线程调用了堵塞式IO方法，在该方法返回前，该线程堵塞
   - 线程试图获得一个同步监视器，但该同步监视器正被其他线程所持有。
   - 线程等待某个通知。
   - 程序调用了suspend方法将该线程挂起。此方法容易导致死锁，尽量避免使用该方法。
- run（）：运行结束进入销毁阶段，整个线程执行完毕。
- join（）方法：使所属线程对象x正常执行run（）方法中的任务，而使当前线程z无限期堵塞，等待线程x销毁后再继续执行线程z后面的代码。
join具有使线程排队运行的作用，有些类似同步的运行效果。join与synchronized的区别是：join在内部使用wait方法进行等待，而synchronized使用的“对象监视器”原理做同步。


每个锁对象都有两个队列，一个是就绪队列，一个是堵塞队列。就绪队列存储了将要获取得到锁的线程，堵塞队列存储了被堵塞的线程。一个线程被唤醒后，才会进入就绪队列，等待cpu的调度。反之，一个线程被wait后，就会进入堵塞队列，等待下一次被唤醒。


释放对象锁：
- 执行完同步代码块就会释放对象锁
- 在执行同步代码块的过程中，遇到异常而导致线程终止，锁也会被释放。
- 在执行同步代码块的过程中，执行锁所属对象的wait（）方法，这个线程会释放对象锁，而此线程对象会进入线程等待池中，等待被唤醒



#### 1.1.7 servlet生命周期
- Servlet 通过调用 init () 方法进行初始化。
- Servlet 调用 service() 方法来处理客户端的请求。
- Servlet 通过调用 destroy() 方法终止（结束）。
- Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。

#### 1.1.8 类的加载
Java默认提供的三个ClassLoader：
- BootStrap ClassLoader：称为启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库
- Extension ClassLoader：称为扩展类加载器，负责加载Java的扩展类库，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar。
- App ClassLoader：称为系统类加载器，负责加载应用程序classpath目录下的所有jar和class文件。

 ClassLoader使用的是双亲委托模型来搜索类的（避免重复加载），从上至下搜索。每个ClassLoader实例都有一个父类加载器的引用。
 JVM在判定两个class是否相同时，不仅要判断两个类名是否相同，而且要判断是否由同一个类加载器实例加载的。


### 1.2 lambda表达式
- [案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-base/src/main/java/com/cosmos/base/lambda)
#### 1.2.1简单示例
lambda：
- 布尔表达式：(List<String> list)->list.isEmpty();
- 创建对象：()->new Apple(10)
- 消费一个对象：(Apple a)->{System.out.println(a.getName())}
- 从一个对象中选择/抽取：(String s)->s.length();
- 组合两个值：(int a,int b)->a*b;
- 比较两个对象：(Apple a1,Apple a2)-> a1.getWeight().compareTo(a2.getWeight);

#### 1.2.2 java8中的函数接口
- Predicate<T>  T->boolean  接收T类型的对象并返回boolean值
   - IntPredicate  接收Integer类型对象
   - LongPredicate 接收Long类型对象
   - DoublePredicate 接收Double类型对象
   
- Consumer<T> T->void  接收T类型的对象并进行处理
   - IntConsumer  接收Integer类型对象
   - LongConsumer 接收Long类型对象
   - LongConsumer 接收Double类型对象
   
-  Function<T,R> T->R  接收T类型对象，进行处理后变成R类型对象
    - IntFunction<R>  接收Integer类型对象，进行处理后变成R类型对象
    - IntToDoubleFunction
    - IntToLongFunction
    - LongFunction<R>
    - LongToDoubleFunction
    - LongToIntFunction
    - DoubleFunction<R>
    - ToIntFunction<T> 接收T类型对象，返回Integer
    - ToDoubleFunction<T>
    - ToLongFunction
    
 - Supplier<T> ()->T Supplier主要是用来创建对象的。可以将耗时操作放在get里，在程序中，传递是Supplier对象，只有真正调用get方法时才执行运算，这就是所谓的惰性求值。
    - BooleanSupplier 
    - IntSupplier
    - LongSupplier
    - DoubleSupplier
    
 - UnaryOperator<T> T->T  接收T类型对象，经过处理后返回T类型对象
    - IntUnaryOperator
    - LongUnaryOperator
    - DoubleUnaryOperator

- BinaryOperator<T> (T,T)->T 对两个T类型对象进行处理返回T类型对象
  - IntBinaryOperator
  - LongBinaryOperator
  - DoubleBinaryOperator
  
- BiPredicate<L,R>  (L,R)->boolean 对两个不同类型对象进行处理，返回boolean类型

- BiConsumer<T,U>  (T,U)->void 对两个对象进行处理
  - ObjIntConsumer
  - ObjLongConsumer
  - objDoubleConsumer
  
- BiFunction<T,U,R> (T,U)->R 对T,U两个类型的对象进行处理并返回R类型数据
  - ToIntBiFunction<T,U>
  - ToLongBiFunction<T,U>
  - ToDoubleBiFunction<T,U>

### 1.3 Stream流
#### 1.3.1 定义及特点
定义：从支持数据处理操作的源生成元素的序列。

详细定义：
1. 元素序列：就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序值。集合讲的是数据，流讲的是计算。
2. 源：流会使用一个提供数据的源，如集合，数组或输入/输出资源。
3. 数据处理操作：流的数据处理功能类似于数据库的操作，以及函数式编程语言中的常用操作。流操作可以顺序执行，也可并行执行。

流操作特点：
1. 流水线：很多操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大的流水线。流水线的操作可以看做对数据源进行数据库式查询。
2. 内部迭代：与使用迭代显式迭代的集合不同，流的迭代操作是在背后进行的。
3. 流只能遍历一次。

#### 1.3.2 Stream常用方法
(数值类型的流：IntStream，LongStream)
1. map：转换流，将一种类型的流转换为另外一种流。
2. filter：过滤流，过滤流中的元素。
3. flapMap：拆解流，将流中每一个元素拆解成一个流。
4. sorted：对流进行排序。
5. limit:元素不能超过指定长度。短路操作。
6. distinct:去重。返回一个元素各异（根据流生成的hashcode和equal的方法实现）的的流。
7. skip(n):返回一个扔掉前n个元素的流。
8. flatMap:将一个流中的每个值都转换成另一个流，然后将所有的流链接起来成为一个流，见simpleStreamTest2。
9. anyMatch：流中是否有一个元素能匹配给定的谓词。
10. allMatch：类似anyMatch，查看流流中元素是否都能匹配给定的谓词。
11. noneMatch：allMatch相反。
12. findAny:返回当前元素的任意元素。
13. findFirst：查找第一个元素。
14.reduce：数值计算。 

### 1.4 JUC常用组件

- [案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-base/src/main/java/com/cosmos/base/juc)
####1.4.1 同步器
##### 1.4.1.1 CountDownLatch闭锁
1. 确保一个计算不被执行，直到它需要的资源初始化。
2. 确保一个服务不会开始，直到它依赖的其他服务都已经开始。
3. 等待，直到活动的所有部分都为继续处理做好充分准备。
    CountDownLatch是一个灵活的闭锁实现。允许一个或多个线程等待一个事件集的发生。闭锁状态包括一个计数器，初始化为一个整数。
用来表现需要等待的事件数。countDown方法针对计数器做减操作，表示一个事件已经发生了，而await方法等待计数器达到零，此时所有需要等待的事件都已经发生。如果计数器入口时值为非零，await会一直堵塞直到计数器为零，或者等待线程中断以及超时。

 
##### 1.4.1.2 CyclicBarrier同步屏障
类似于闭锁。与闭锁不同之处在于，所有的线程必须同时到达关卡点，才能继续处理。
闭锁等待的是事件；而同步屏障等待的是其他的线程。
常用示例比如：可将一个任务分割成多个子部分，然后再整合。


     
##### 1.4.1.3 Semaphore计数信号量
- 用来控制能够同时访问某特定资源的活动的数量。
- 计数信号量可以用来实现资源池或者给一个容器限定边界。
- 一个Semaphore管理一个有效的许可，许可的除湿量通过构造函数传递给semaphore活动能够获得许可（只要还有剩余许可），并在使用之后释放许可，如果没有可用的许可，则acquire会被堵塞，直到有可用的为止。
- 常见的信号量使用即数据库连接池。

##### 1.4.1.4 Exchanger交换器
当两个线程在同一个数据缓冲区的两个实例上工作的时候， 就可以使用交换器
( Exchanger) 典型的情况是， 一个线程向缓冲区填人数据， 另一个线程消耗这些数据。当它们都完成以后，相互交换缓冲区。

##### 1.4.1.5 同步队列SynchronousQueue
同步队列是一种将生产者与消费者线程配对的机制。当一个线程调用 SynchronousQueue
的 put 方法时，它会阻塞直到另一个线程调用 take 方法为止， 反之亦然。 与 Exchanger 的情
况不同， 数据仅仅沿一个方向传递，从生产者到消费者。
即使 SynchronousQueue 类实现了 BlockingQueue 接口， 概念上讲， 它依然不是一个队
列。它没有包含任何元素，它的 size 方法总是返回 0。

#### 1.4.2 Lock+Condition
Lock作用:
 - 锁用来保护代码片段，任何时刻只能有一个线程执行被保护的代码。
 - 锁可以管理试图进入被保护代码段的线程。
 - 锁可以拥有一个或多个相关的条件对象。
 - 每个条件对象管理那些已经进入被保护的代码段但还不能运行的线程。

 #####1.4.2.1 ReentrantLock (默认非公平锁)
公平与非公平锁：公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序。而非公平锁是一种获取锁的抢占机制，是随机获得锁的，和公平锁不一样的就是先来的不一定先得到锁，这个方式肯呢过造成某些线程一直拿不到锁，结果也就是不公平的了
 重入锁（ReentrantLock）是一种递归无阻塞的同步机制。
 它有一个与锁相关的获取计数器，如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放。这模仿了 synchronized 的语义；如果线程进入由线程已经拥有的监控器保护的 synchronized 块，就允许线程继续进行，当线程退出第二个（或者后续） synchronized 块的时候，不释放锁，只有线程退出它进入的监控器保护的第一个 synchronized 块时，才释放锁。
  - lock()：
     - 如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。
     - 如果当前线程已经保持该锁，则将保持计数加 1，并且该方法立即返回。
     - 如果该锁被另一个线程保持，则出于线程调度的目的，禁用当前线程，并且在获得锁之前，该线程将一直处于休眠状态，此时锁保持计数被设置为 1
     
  - lockInterruptibly()
     - 如果当前线程未被中断，则获取锁。 
     - 如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。
     - 如果当前线程已经保持此锁，则将保持计数加 1，并且该方法立即返回。 
     - 如果锁被另一个线程保持，则出于线程调度目的，禁用当前线程，并且在发生以下两种情况之一以前，该线程将一直处于休眠状态(锁由当前线程获得；或者 其他某个线程中断当前线程。 ) 
     - 如果当前线程获得该锁，则将锁保持计数设置为 1。 
     - 此方法是一个显式中断点，所以要优先考虑响应中断，而不是响应锁的普通获取或
重入获取。 
  - tryLock():仅在调用时锁未被另一个线程保持的情况下，才获取该锁。 
     - 如果该锁没有被另一个线程保持，并且立即返回 true 值，则将锁的保持计数设置为 1。即使已将此锁设置为使用公平排序策略，但是调用 tryLock() 仍将 立即获取锁（如果有可用的），而不管其他线程当前是否正在等待该锁。在某些情况下，此“闯入”行为可能很有用，即使它会打破公平性也如此。如果希望遵守此锁的公平设置，则使用 tryLock(0, TimeUnit.SECONDS) ，它几乎是等效的（也检测中断）。 
      - 如果当前线程已经保持此锁，则将保持计数加 1，该方法将返回 true。 
      - 如果锁被另一个线程保持，则此方法将立即返回 false 值。 



#### 1.4.3 volatile
定义：
加锁可以保证可见性与原子性。但volatile变量只能保证可见性。

使用场景:
1.写入变量时并不依赖变量的当前值。或者能够确定保证只有单一的线程能修改变量的值。
2.变量不需要与其他状态变量共同参与不变约束。
3.访问变量的时候，没有其他原因需要加锁。

volatile和synchronized比较：
- 关键字volatile是线程同步的轻量实现，所以volatile性能肯定比synchronized好，并且volatile只能修饰于变量，而synchronized可以修饰方法以及代码块。
- 多线程访问volatile不会发生堵塞，而synchronized会出现堵塞。
- volatile只能保证数据的可见性，但不能保证原子性；而synchronized可以保证原子性，也可间接保证可见性，因为它会将私有内存和公共内存中的数据做同步
- volatile解决的是变量在多个线程之间的可见性，而synchronized解决的是多个线程之间访问资源的同步性。


#### 1.4.4 ThreadLocal
Threadlocal提供了get和set的访问器，为每个使用它的线程维护一份单独的拷贝。所以get返回的都是当前线程设置的最新值。

#### 1.4.5 堵塞队列
用处：
对于许多线程问题， 可以通过使用一个或多个队列以优雅且安全的方式将其形式化。生产者线程向队列插人元素， 消费者线程则取出它们。使用队列，可以安全地从一个线程向另一个线程传递数据。 
- add 添加一个元素，如果队列满，则抛出IllegalStateException异常
- element 返回队列的头元素，如果队列空，抛出NoSuchElementException
- offer 添加一个元素并返回true 如果队列满则返回false
- peek 返回队列的头元素，如果队列为空，则返回null
- poll 移除并返回的头元素，如果队列空，则返回null
- put 添加一个元素，如果队列满，则堵塞
- remove 移除并返回头元素，如果队列空，抛出NoSuchElementException
- take 移除并返回头元素，如果队列空，则堵塞

常用堵塞队列
- ArrayBlockingQueue：构造一个带有指定容量和公平性设置的堵塞队列。该队列用循环数组实现。
- LinkedBlockingQueue/LinkedBlockingDeque：构造一个无上限的堵塞队列或双向队列，用链表实现。
- DelayQueue：构造一个包含 Delayed 元素的无界的阻塞时间有限的阻塞队列。只有那些延迟已经超过时间的元素可以从队列中移出。
- PriorityBlockingQueue：构造一个无边界阻塞优先队列，用堆实现。
- ConcurrentLinkedQueue：构造一个可以被多线程安全访问的无边界非阻塞的队列。
- ConcurrentSkipListSet：构造一个可以被多线程安全访问的有序集。第一个构造器要求元素实现 Comparable
接口。



#### 1.4.6 Future+callable
所谓异步调用其实就是实现一个可无需等待被调用函数的返回值而让操作继续运行的方法。在 Java 语言中，简单的讲就是另启一个线程来完成调用中的部分计算，使调用继续运行或返回，而不需要等待计算结果。但调用者仍需要取线程的计算结果。

CompletableFuture:
- supplyAsync：执行一个异步请求，并返回一个future
- thenApply：对结果应用一个函数
- thenCompose：对结果调用函数并执行返回的future
- handle：处理结果或错误
- thenAccept： 类似于 thenApply, 不过结果为 void
- whenComplete：类似于 handle, 不过结果为 void
- thenRun：执行 Runnable, 结果为 void
- thenCombine:执行两个动作并用给定函数组合结果
- thenAcceptBoth:与 thenCombine 类似， 不过结果为 void
- runAfterBoth:两个都完成后执行_able
- applyToEither:得到其中一个的结果时， 传入给定的函数
- acceptEither:与 applyToEither 类似， 不过结果为 void
- runAfterEither:其中一个完成后执行 runnable
- static allOf:所有给定的 future 都完成后完成，结果为 void
- static anyOf:任意给定的 future 完成后则完成，结果为 void

#### 1.4.7 synchroized
- 调用关键字synchroized生命的方法一定是排队运行的
- 避免数据出现交叉的情况，使用synchroized关键词进行同步
- 关键词synchroized拥有锁重入的功能 ，也就是在使用synchroized时，当一个线程得到一个对象锁后，在此请求此对象锁时是可以再次得到该对象锁的。
- 当一个线程执行的代码出现异常时，其所持有的锁会自动释放。
- 同步不可以被继承。
- synchroized同步块 锁非this对象相比synchroized（this）更加灵活，当一个方法中有多个同步块时，不用竞争this对象锁。

关于synchroized（非this对象x）的写法是将x对象本身作为"对象监视器"，有如下三个结论：
- 当多个线程同时执行synchroized（X）{}同步代码块时呈同步效果。
- 当其他线程执行x对象中synchroized同步方法时呈同步效果。
- 当其他线程执行x对象方法中的synchroized（this）代码块时也呈现同步效果。
- 如果其他线程调用不加synchroized关键字的方法时，还是异步调用。

- synchroized关键字加到非static静态方法是给对象上锁，而教导static方法上则是对Class类加锁。

- 对于String对象，不要用作对象锁。String a="A",String b="A"，a==b，导致线程会使用同一个对象锁

- 在设计程序时，要避免双方互相持有对方锁的情况，会导致死锁。



### 1.5 常见的8种排序算法
|排序方法 |时间复杂度  | 空间复杂度|
|--|--|--|
| 直接插入排序 |O(n<sup>2</sup>)  | O(1)  |
| 希尔排序|O(n<sup>2</sup>)  | O(1)  |
| 直接选择排序 |O(n<sup>2</sup>)  | O(1)  |
|堆排序  |O(nlog<sub>2</sub>n)  | O(1)  |
| 冒泡排序|O(n<sup>2</sup>)  | O(1)  |
| 快速排序|O(nlog<sub>2</sub>n)  | O(nlog<sub>2</sub>n） |
| 归并排序|O(nlog<sub>2</sub>n)  | O(1)  |
| 基数排序|O(d(r+n))  | O(rd+n)  |

目前使用java对于快速排序，选择排序，冒泡排序，归并排序以及插入排序进行实现。具体案例可见于。

- [案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-arithmetic)



## 2. cosmo-boot
主要是基于springboot+springCloud+Alibaba等框架的一些使用。

### 2.1 kafka
- [案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-boot/cosmos-kafka)
#### 2.1.1 kafka简介
- Kafka用于构建实时数据管道和流应用程序。它具有水平可扩展性，容错性，快速性。
- Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者在网站中的所有动作流数据
- 主要由以下部分组成：
##### 2.1.1.1 topic
- Topic是Kafka数据写入操作的基本单元。一个Topic包含一个或多个Partition。每条消息属于且仅属于一个Topic。一个Topic可以认为是一类消息。
- producer发布数据时，必须指定将该消息发布到哪个Topic。Consumer订阅消息时，也必须指定订阅哪个Topic的信息。
- 每个partition在存储层面是append log文件。任何发布到此partition的消息都会被直接追加到log文件的尾部，每条消息在文件中的位置称为offset（偏移量），- offset为一个long型数字，它是唯一标记一条消息。它唯一的标记一条消息。kafka并没有提供其他额外的索引机制来存储offset，因为在kafka中几乎不允许对消息进行“随机读写”。

##### 2.1.1.2 consumer
- 本质上kafka只支持Topic.每个consumer属于一个consumer group;反过来说,每个group中可以有多个consumer.发送到Topic的消息,只会被订阅此Topic的每个group中的一个consumer消费.
- 在kafka中,一个partition中的消息只会被group中的一个consumer消费;每个group中consumer消息消费互相独立;我们可以认为一个group是一个"订阅"者,一个Topic中的每个partions,只会被一个"订阅者"中的一个consumer消费,不过一个consumer可以消费多个partitions中的消息.kafka只能保证一个partition中的消息被某个consumer消费时,消息是顺序的.事实上,从Topic角度来说,消息仍不是有序的.
- kafka的原理决定,对于一个topic,同一个group中不能有多于partitions个数的consumer同时消费,否则将意味着某些consumer将无法得到消息.

##### 2.1.1.3 producer
Producer将消息发布到指定的Topic中,同时Producer也能决定将此消息归属于哪个partition;比如基于"round-robin"方式或者通过其他的一些算法等.

#### 2.1.2 kafka的相关配置 
##### 2.1.2.1 kafka消费者参数
1. bootstrap.servers: 消费者初始连接kafka集群时的地址列表。不管这边配置的什么地址，消费者会使用所有的kafka集群服务器。消费者会通过这些地址列表，找到所有的kafka集群机器。                  
2. key.deserializer: 实现了Deserializer的key的反序列化类
3. value.deserializer: 实现了Deserializer的value的反序列化类
4. fetch.min.bytes: 每次请求，kafka返回的最小的数据量。如果数据量不够，这个请求会等待，直到数据量到达最小指标时，才会返回给消费者。如果设置大于1，会提高kafka的吞吐量，但是会有额外的等待期的代价。
5. group.id: 标识这台消费者属于那个消费组。 如果消费者通过订阅主题来实现组管理功能，或者使用基于kafka的偏移量管理策略，这个配置是必须的          
6. heartbeat.interval.ms: 使用kafka集群管理工具时，消费者协调器之间的预计心跳时间。心跳的作用是确保消费者的session是活跃的， 同时当新的机器加入集群或有机器挂掉的情况下触发再平衡操作。这个配置必须小于heartbeat.interval.ms，而且应该不大于这个值的1/3。为了控制正常的负载均衡的预期时间，这个值可以设置的更小。                
7. max.partition.fetch.bytes: kafka集群每个分区一次返回的最大数据量。 一次请求的最大内存使用量应该等于#partitions * max.partition.fetch.bytes。这个值必须与kafka集群允许的最大消息数据量差不多大小，否则可能生产者发送了一个消息，大于消费者配置的值。这种情况下，消费者可能会在获取那条消息时堵住。                              
8. session.timeout.ms: 使用kafka集群管理工具时检测失败的超时时间。 如果在session超时时间范围内，没有收到消费者的心跳，broker会把这个消费者置为失效，并触发消费者负载均衡。因为只有在调用poll方法时才会发送心跳，更大的session超时时间允许消费者在poll循环周期内处理消息内容，尽管这会有花费更长时间检测失效的代价。如果想控制消费者处理消息的时间， 还可以参考max.poll.records。注意这个值的大小应该在group.min.session.timeout.ms和group.max.session.timeout.ms范围内。
9. ssl.key.password: 私钥存储文件的私钥密码。可选配置。
10. ssl.keystore.location:私钥存储文件的路径。可选配置，并且可用来作为客户端的双向认证。
11. ssl.keystore.password:私钥存储文件的存储密码。可选配置，并且只有ssl.keystore.location配置的情况下才需要配置。
12. ssl.truststore.location:信任秘钥文件路径。
13. ssl.truststore.password:信任秘钥文件密码。
14. auto.offset.reset:当kafka的初始偏移量没了，或者当前的偏移量不存在的情况下，应该怎么办？下面有几种策略：earliest（将偏移量自动重置为最初的值）、latest（自动将偏移量置为最新的值）、none（如果在消费者组中没有发现前一个偏移量，就向消费者抛出一个异常）、anything else（向消费者抛出异常）   
15. connections.max.idle.ms:配置时间后，关闭空闲的连接	
16. enable.auto.commit:如果设为true，消费者的偏移量会定期在后台提交。
17. exclude.internal.topics:内部主题（比如偏移量）是否需要暴露给消费者。如果设为true，获取内部主题消息的途径就是订阅他们
18. max.poll:一次poll调用返回的最大消息数量。
19. partition.assignment.strategy:使用组管理时，客户端使用的分区策略的类名，根据这个策略来进行消费分区。
20. receive.buffer.bytes:SO_RCVBUF读取数据使用的内存大小。
21. request.timeout.ms：这个配置控制一次请求响应的最长等待时间。如果在超时时间内未得到响应，kafka要么重发这条消息，要么超过重试次数的情况下直接置为失败。


##### 2.1.2.2 kafka提供者参数
1. acks: acks指定了必须有多少个分区副本接收到了消息，生产者才会认为消息是发送成功的。acks=0，生产者成功写入消息之前不会等待来自任何服务器的响应，这种配置，提高吞吐量，但是消息存在丢失风险。acks=1，只要集群的leader（master）收到了消息，生产者将会受到发送成功的一个响应，如果消息无撞到达首领节点（比如首领节点崩愤，新的首领还没有被选举出来），生产者会收到一个错误响应，为了避免数据丢失，生产者会重发消息。不过，如果一个没有收到消息的节点成为新首领，消息还是会丢失。这个时候的吞吐量取决于使用的是同步发送还是异步发送。如果让发送客户端等待服务器的响应（通过调用Futu re 对象的get（）方法， 显然会增加延迟（在网络上传输一个来回的延迟）。如果客户端使用回调，延迟问题就可以得到缓解，不过吞吐量还是会受发送中消息数量的限制（比如，生产者在收到服务器响应之前可以发送多少个消息）。acks=all，所有参与复制的节点全部收到消息的时候，生产者才会收到来自服务器的一个响应，这种模式最安全，但是吞吐量受限制，它可以保证不止一个服务器收到消息，就算某台服务器奔溃，那么整个集群还是会正产运转。    
2. buffer.memory:该参数用来设置生产者内存缓冲区的大小，缓冲生产者发往服务器的消息，如果生产者发送速率大于服务器接受速率，那么会导致生产者内存空间不足，此时send方法要么阻塞，要么爬出异常，具体行为依赖block.on.buffer.full参数，0.9.0.0版本中被替换成了max.block.ms用来设置抛出异常之前可以阻塞一段时间。             
3. compression.type:消息压缩算法，snappy消耗较低的CPU并且有较为理想的压缩比和压缩性能，如果对于性能和网络带宽，这是一种比较理想的算法（Google发明的算法，Google是真牛逼），gzip算法耗费CPU资源比较多，但是提高了很高的压缩比，如果对于网络带宽有着很高的要求，那么这种算法比较适合。使用压缩可以降低网络传输开销和存储开销，这也往往是向kafka发送消息的瓶颈所在。                    
4. retries:生产者从服务器收到的错误消息有可能是临时的，当生产者收到服务器发来的错误消息，会启动重试机制，当充实了n（设置的值）次，还是收到错误消息，那么将会返回错误。生产者会在每次重试之间间隔100ms，不过可以通过retry.backoff.ms改变这个间隔。    
5. batch.size:当多个消息发往 同一个分区，生产者会将他们放进同一个批次，该参数指定了一个批次可以使用的内存大小，按照字节数进行计算，不是消息个数，当批次被填满，批次里面所有得消息将会被发送，半满的批次，甚至只包含一个消息也可能会被发送，所以即使把批次设置的很大，也不会造成延迟，只是占用的内存打了一些而已。但是设置的太小，那么生产者将会频繁的发送小，增加一些额外的开销。       
6. linger.ms:该参数指定了生产者在发送批次之前等待更多消息加入批次的时间。KafkaPr oduce 「会在批次填满或linger.ms达到上限时把批次发送出去。默认情况下，只要有可用的线程， 生产者就会把消息发送出去，就算批次里只有一个消息。把linger.ms设置成比0 大的数，让生产者在发送批次之前等待一会儿，使更多的消息加入到这个批次。虽然这样会增加延迟，但也会提升吞吐量（因为一次性发送更多的消息，每个消息的开销就变小了）        
7. client.id:服务器会根据该参数值来识别消息的来源，还可以用在日志配置以及配额指标里。
8. max.in.flight.requests.per.connection	:指定了生产者收到服务器响应之前可以发送多少个消息。它的值越高，将会消耗更多的内存，不过也会提升吞吐量。设置为1，可以保证消息是按照发送的顺序写入服务器。即使发生了重试。                                 
9. timeout.ms:指定了broker等待同步副本返回消息的时间，如果指定时间同步副本没有返回消息，那么将会返回错误。     
10. requests.timeout.ms:生产者发送数据时等待服务器返回响应的时间
11. metadata.fetch.timeout.ms:生产者在获取元数据（例如分区首领是谁？）时等待服务器的响应时间，如果响应时间接收不到消息，那么要么重试要么返回一个错误（抛出异常或者执行回调）
12. max.block.ms:该参数指定了在调用send()方法或使用partitionFor() 方法获取元数据时生产者的阻塞 时间。当生产者的发送缓冲区已满，或者没有可用的元数据时，这些方法就会阻塞。在阻塞时间达到max.block.ms 时，生产者会抛出超时异常。
13. max.request.size：控制生产者发送消息的大小，它可以指定单个消息的最大值，也可以指定单个请求里所有消息大小的总和。比如1MB，那么单个消息最大大小是1MB，同时如果消息大小是1KB，那么一次可以发送1000条消息。 另外broker也有接受消息最大的限制，message.max.bytes,(参考博主的broker配置文章)所以两边最好能够匹配。避免生产者发送消息被broker拒绝。                 
14. receive.buffer.bytes / send. buffer.bytes:
这两个参数分别指定了TCP socket 接收和发送数据包的缓冲区大小。如果它们被设为-1 , 就使用操作系统的默认值。如果生产者或消费者与broker 处于不同的数据中心，那么可以适当增大这些值，因为跨数据中心的网络一般都有比较高的延迟和比较低的带宽。


### 2.2 consul
#### 2.2.1 consul简介
Consul是用来分布式系统的服务发现和配置的。在eureka2.0闭源的情况下，consul是一个不错的替代品。
Consul提供了通过DNS或者HTTP接口的方式来注册服务和发现服务。一些外部的服务通过Consul很容易的找到它所依赖的服务。

#### 2.2.2 集群安装
此处测试使用docker-compose来安装集群。[docker-compose](https://github.com/CosmosNi/cosmos-tutorial/blob/master/cosmos-boot/cosmos-consul/docker-compose.yml)

#### 2.2.3 结合springboot
[cosmos-consul案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-boot/cosmos-consul)

配置参数详解 （以下参数需配置在 bootstrap.yml）
  - spring.cloud.consul.host：配置consul地址
  - spring.cloud.consul.port：配置consul端口
  - spring.cloud.consul.discovery.enabled：启用服务发现
  - spring.cloud.consul.discovery.register：启用服务注册
  - spring.cloud.consul.discovery.deregister：服务停止时取消注册
  - spring.cloud.consul.discovery.prefer-ip-address：表示注册时使用IP而不是hostname
  - spring.cloud.consul.discovery.health-check-interval：健康检查频率
  - spring.cloud.consul.discovery.health-check-path：健康检查路径
  - spring.cloud.consul.discovery.health-check-critical-timeout：健康检查失败多长时间后，取消注册
  - spring.cloud.consul.discovery.instance-id：服务注册标识

### 2.3 redis
#### redis存在的问题
   - 缓存穿透：是指查询一个数据库一定不存在的数据。正常的使用缓存的流程大致时，数据查询先进行缓存查询，如果key不存在或key已经失效，在对数据库进行查询，并把查询的对象，放入缓存。如果数据库查询对象为空，则不放进缓存。解决方案，对空值也做缓存，缓存周期设置较短一些。
   - 缓存雪崩：是指在某一个时间段，缓存集中过期失效。解决方案，根据不同情况，缓存不同周期。用锁/分布式锁或者队列串行访问
   - 缓存击穿：是指一个key非常热点，大并发集中对这个点进行访问，当这个key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库。解决方案，使用锁，单机用synchronized,lock等，分布式用分布式锁。缓存过期时间不设置，而是设置在key对应的value里。如果检测到存的时间超过过期时间则异步更新缓存。在value设置一个比过期时间t0小的过期时间值t1，当t1过期的时候，延长t1并做更新缓存操作。

## 3.comsmos-design
该模块主要用来介绍一些常用的设计模式，以及相关联的demo。




## 4.linux+docker
该模式主要记录一些linux以及docker的相关指令。

### 4.1 centos
#### 4.1.1 常见目录说明
/bin： 存放二进制可执行文件(ls,cat,mkdir等)，常用命令一般都在这里；
/etc： 存放系统管理和配置文件；
/home： 存放所有用户文件的根目录，是用户主目录的基点，比如用户user的主目录就是/home/user，可以用~user表示；
/usr ： 用于存放系统应用程序；
/opt： 额外安装的可选应用程序包所放置的位置。一般情况下，我们可以把tomcat等都安装到这里；
/proc： 虚拟文件系统目录，是系统内存的映射。可直接访问这个目录来获取系统信息；
/root： 超级用户（系统管理员）的主目录（特权阶级^o^）；
/sbin: 存放二进制可执行文件，只有root才能访问。这里存放的是系统管理员使用的系统级别的管理命令和程序。如ifconfig等；
/dev： 用于存放设备文件；
/mnt： 系统管理员安装临时文件系统的安装点，系统提供这个目录是让用户临时挂载其他的文件系统；
/boot： 存放用于系统引导时使用的各种文件；
/lib ： 存放着和系统运行相关的库文件 ；
/tmp： 用于存放各种临时文件，是公用的临时文件存储点；
/var： 用于存放运行时需要改变数据的文件，也是某些大文件的溢出区，比方说各种服务的日志文件（系统启动日志等。）等；
/lost+found： 这个目录平时是空的，系统非正常关机而留下“无家可归”的文件（windows下叫什么.chk）就在这里。

#### 4.1.2 常用指令
- cd usr： 切换到该目录下usr目录
- cd ..（或cd../）： 切换到上一层目录
- cd /： 切换到系统根目录
- cd ~： 切换到用户主目录
- cd -： 切换到上一个操作所在目录
- mkdir 目录名称： 增加目录
- ls或者ll（ll是ls -l的别名，ll命令可以看到该目录下的所有目录和文件的详细信息）：查看目录信息
- find 目录 参数： 寻找目录（查）
示例：
列出当前目录及子目录下所有文件和文件夹: find .
在/home目录下查找以.txt结尾的文件名:find /home -name "*.txt"
同上，但忽略大小写: find /home -iname "*.txt"
当前目录及子目录下查找所有以.txt和.pdf结尾的文件:find . \( -name "*.txt" -o -name "*.pdf" \)或find . -name "*.txt" -o -name "*.pdf"

- mv 目录名称 新目录名称： 修改目录的名称（改）
注意：mv的语法不仅可以对目录进行重命名而且也可以对各种文件，压缩包等进行 重命名的操作。mv命令用来对文件或目录重新命名，或者将文件从一个目录移到另一个目录中。后面会介绍到mv命令的另一个用法。
- mv 目录名称 目录的新位置： 移动目录的位置---剪切（改）
注意：mv语法不仅可以对目录进行剪切操作，对文件和压缩包等都可执行剪切操作。另外mv与cp的结果不同，mv好像文件“搬家”，文件个数并未增加。而cp对文件进行复制，文件个数增加了。
- cp -r 目录名称 目录拷贝的目标位置： 拷贝目录（改），-r代表递归拷贝
注意：cp命令不仅可以拷贝目录还可以拷贝文件，压缩包等，拷贝文件和压缩包时不 用写-r递归
- rm [-rf] 目录: 删除目录（删）
注意：rm不仅可以删除目录，也可以删除其他文件或压缩包，为了增强大家的记忆， 无论删除任何目录或文件，都直接使用rm -rf 目录/文件/压缩包
- touch 文件名称: 文件的创建（增）

- cat/more/less/tail 文件名称 文件的查看（查）
cat： 查看显示文件内容
more： 可以显示百分比，回车可以向下一行， 空格可以向下一页，q可以退出查看
less： 可以使用键盘上的PgUp和PgDn向上 和向下翻页，q结束查看
tail-10 ： 查看文件的后10行，Ctrl+C结束
注意：命令 tail -f 文件 可以对某个文件进行动态监控，例如tomcat的日志文件， 会随着程序的运行，日志会变化，可以使用tail -f catalina-2016-11-11.log 监控 文 件的变化

- vim 文件： 修改文件的内容（改）
vim编辑器是Linux中的强大组件，是vi编辑器的加强版，vim编辑器的命令和快捷方式有很多，但此处不一一阐述，大家也无需研究的很透彻，使用vim编辑修改文件的方式基本会使用就可以了。
在实际开发中，使用vim编辑器主要作用就是修改配置文件，下面是一般步骤：
vim 文件------>进入文件----->命令模式------>按i进入编辑模式----->编辑文件 ------->按Esc进入底行模式----->输入:wq/q! （输入wq代表写入内容并退出，即保存；输入q!代表强制退出不保存。）

- rm -rf 文件： 删除文件（删）
同目录删除：熟记 rm -rf 文件 即可

- 压缩文件的操作命令
Linux中的打包文件一般是以.tar结尾的，压缩的命令一般是以.gz结尾的。
而一般情况下打包和压缩是一起进行的，打包并压缩后的文件的后缀名一般.tar.gz。 命令：tar -zcvf 打包压缩后的文件名 要打包压缩的文件 其中：
z：调用gzip压缩命令进行压缩
c：打包文件
v：显示运行过程
f：指定文件名

比如：加入test目录下有三个文件分别是 :aaa.txt bbb.txt ccc.txt,如果我们要打包test目录并指定压缩后的压缩包名称为test.tar.gz可以使用命令：tar -zcvf test.tar.gz aaa.txt bbb.txt ccc.txt或：tar -zcvf test.tar.gz /test/

2）解压压缩包：
命令：tar [-xvf] 压缩文件
其中：x：代表解压
示例：
1 将/test下的test.tar.gz解压到当前目录下可以使用命令：tar -xvf test.tar.gz
2 将/test下的test.tar.gz解压到根目录/usr下:tar -xvf xxx.tar.gz -C /usr（- C代表指定解压的位置）

- 权限分配
操作系统中每个文件都拥有特定的权限、所属用户和所属组。
权限是操作系统用来限制资源访问的机制，在Linux中权限一般分为读(readable)、写(writable)和执行(excutable)，分为三组。分别对应文件的属主(owner)，属组(group)和其他用户(other)，通过这样的机制来限制哪些用户、哪些组可以对特定的文件进行什么样的操作。
通过 ls -l 命令我们可以 查看某个目录下的文件或目录的权限
- Linux 用户管理
Linux系统是一个多用户多任务的分时操作系统，任何一个要使用系统资源的用户，都必须首先向系统管理员申请一个账号，然后以这个账号的身份进入系统。
用户的账号一方面可以帮助系统管理员对使用系统的用户进行跟踪，并控制他们对系统资源的访问；另一方面也可以帮助用户组织文件，并为用户提供安全性保护。

- Linux用户管理相关命令:

- useradd 选项 用户名:添加用户账号
- userdel 选项 用户名:删除用户帐号
- usermod 选项 用户名:修改帐号
- passwd 用户名:更改或创建用户的密码
- passwd -S 用户名 :显示用户账号密码信息
- passwd -d 用户名: 清除用户密码
- useradd命令用于Linux中创建的新的系统用户。useradd可用来建立用户帐号。帐号建好之后，再用passwd设定帐号的密码．而可用userdel删除帐号。使用useradd指令所建立的帐号，实际上是保存在/etc/passwd文本文件中。

- passwd命令用于设置用户的认证信息，包括用户密码、密码过期时间等。系统管理者则能用它管理系统用户的密码。只有管理者可以指定用户名称，一般用户只能变更自己的密码。

4.7 Linux系统用户组的管理
每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同Linux 系统对用户组的规定有所不同，如Linux下的用户属于与它同名的用户组，这个用户组在创建用户时同时创建。

用户组的管理涉及用户组的添加、删除和修改。组的增加、删除和修改实际上就是对/etc/group文件的更新。

Linux系统用户组的管理相关命令:

groupadd 选项 用户组 :增加一个新的用户组
groupdel 用户组:要删除一个已有的用户组
groupmod 选项 用户组 : 修改用户组的属性
4.8 其他常用命令
pwd： 显示当前所在位置

grep 要搜索的字符串 要搜索的文件 --color： 搜索命令，--color代表高亮显示

ps -ef/ps -aux： 这两个命令都是查看当前系统正在运行进程，两者的区别是展示格式不同。如果想要查看特定的进程可以使用这样的格式：ps aux|grep redis （查看包括redis字符串的进程），也可使用 pgrep redis -a。

注意：如果直接用ps（（Process Status））命令，会显示所有进程的状态，通常结合grep命令查看某进程的状态。

kill -9 进程的pid： 杀死进程（-9 表示强制终止。）

先用ps查找进程，然后用kill杀掉

网络通信命令：

- 查看当前系统的网卡信息：ifconfig
- 查看与某台机器的连接情况：ping
- 查看当前系统的端口使用：netstat -an
- net-tools 和 iproute2 ： net-tools起源于BSD的TCP/IP工具箱，后来成为老版本Linux内核中配置网络功能的工具。但自2001年起，Linux社区已经对其停止维护。同时，一些Linux发行版比如Arch Linux和CentOS/RHEL 7则已经完全抛弃了net-tools，只支持iproute2。linux ip命令类似于ifconfig，但功能更强大，旨在替代它。更多详情请阅读如何在Linux中使用IP命令和示例

- shutdown： shutdown -h now： 指定现在立即关机；shutdown +5 "System will shutdown after 5 minutes":指定5分钟后关机，同时送出警告信息给登入用户。

- reboot： reboot： 重开机。reboot -w： 做个重开机的模拟（只有纪录并不会真的重开机）。



### 4.2 ubuntu

### 4.3 docker以及docker-compose

#### 4.3.1 安装及卸载（基于centos7）
 1. 卸载指令
    -    sudo yum remove docker docker-client docker-client-latest docker-common  docker-latest  docker-latest-logrotate docker-logrotate docker-selinux docker-engine-selinux docker-engine
 2. - yum install -y yum-utils device-mapper-persistent-data lvm2
    - yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    - yum makecache fast
    - rpm --import https://mirrors.aliyun.com/docker-ce/linux/centos/gpg
    - yum -y install docker-ce
    - systemctl enable docker
    - systemctl restart docker
 3. 设置开机启动
   - service docker start
   - chkconfig docker on
   - systemctl start docker.service
   - systemctl enable docker.service
 4. docker version（查看docker版本）
 5. 修改docker国内镜像，重启生效
     - vi  /etc/docker/daemon.json
    修改文件内容：
    {
  "registry-mirrors": [
    "https://registry.docker-cn.com"
      ]
   } 

或者使用如下命令修改镜像：
curl -sSL https://get.daocloud.io/daotools/set_mirror.sh | sh -s http://e7850958.m.daocloud.io

 6. 安装docker管理portainer
   - 拉取 Portainer 
  docker pull portainer/portainer
   - 单机运行
   docker run -d -p 9000:9000 \
    --restart=always \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --name portainer-web \
    portainer/portainer

#### 4.3.2 docker常用命令
docker常用命令：
- 拉取docker镜像：docker pull image_name
- 查看宿主机上的镜像，Docker镜像保存在/var/lib/docker目录下: docker images
- 删除镜像：docker rmi  docker.io/tomcat:7.0.77-jre7   或者  docker rmi b39c68b7af30
- 查看当前有哪些容器正在运行：docker ps
- 查看所有容器：docker ps -a
- 启动、停止、重启容器命令：
  - docker start container_name/container_id
  - docker stop container_name/container_id
  - docker restart container_name/container_id
- 后台启动一个容器后，如果想进入到这个容器，可以使用attach命令：docker attach container_name/container_id
- 删除容器的命令：docker rm container_name/container_id
- 删除所有停止的容器：docker rm $(docker ps -a -q)
- 查看当前系统Docker信息：docker info
- 从Docker hub上下载某个镜像:docker pull centos:latest
                           docker pull centos:latest
- 查找Docker Hub上的nginx镜像：docker search nginx

#### 4.3.3 docker-compose
##### 第一步 安装
```
sudo curl -L "https://github.com/docker/compose/releases/download/1.23.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```
##### 第二步 修改 docker-compose 命令的权限
```
chmod +x /usr/local/bin/docker-compose
```
##### 第三步 验证是否安装成功
```
docker-compose --version
```
##### 第三步 启动 docker-compose
当前目录下需要有 docker-compose.yml文件 docker-compose up -d


