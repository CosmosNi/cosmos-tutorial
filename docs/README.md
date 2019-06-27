222222222222222
## 1.cosmos-base
主要存放jdk的相关基础知识。包括juc，lambda，stream等相关知识。

### 1.1 lambda表达式
- [案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-base/src/main/java/com/cosmos/base/lambda)
#### 1.1.1简单示例
lambda：
- 布尔表达式：(List<String> list)->list.isEmpty();
- 创建对象：()->new Apple(10)
- 消费一个对象：(Apple a)->{System.out.println(a.getName())}
- 从一个对象中选择/抽取：(String s)->s.length();
- 组合两个值：(int a,int b)->a*b;
- 比较两个对象：(Apple a1,Apple a2)-> a1.getWeight().compareTo(a2.getWeight);

#### 1.1.2 java8中的函数接口
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

### 1.2 Stream流
#### 1.2.1 定义及特点
定义：从支持数据处理操作的源生成元素的序列。

详细定义：
1. 元素序列：就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序值。集合讲的是数据，流讲的是计算。
2. 源：流会使用一个提供数据的源，如集合，数组或输入/输出资源。
3. 数据处理操作：流的数据处理功能类似于数据库的操作，以及函数式编程语言中的常用操作。流操作可以顺序执行，也可并行执行。

流操作特点：
1. 流水线：很多操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大的流水线。流水线的操作可以看做对数据源进行数据库式查询。
2. 内部迭代：与使用迭代显式迭代的集合不同，流的迭代操作是在背后进行的。
3. 流只能遍历一次。

#### 1.2.2 Stream常用方法
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



### 1.3 JUC常用组件
- [案例](https://github.com/CosmosNi/cosmos-tutorial/tree/master/cosmos-base/src/main/java/com/cosmos/base/juc)
#### 1.3.1 CountDownLatch闭锁
1. 确保一个计算不被执行，直到它需要的资源初始化。
2. 确保一个服务不会开始，直到它依赖的其他服务都已经开始。
3. 等待，直到活动的所有部分都为继续处理做好充分准备。
    CountDownLatch是一个灵活的闭锁实现。允许一个或多个线程等待一个事件集的发生。闭锁状态包括一个计数器，初始化为一个整数。
用来表现需要等待的事件数。countDown方法针对计数器做减操作，表示一个事件已经发生了，而await方法等待计数器达到零，此时所有需要等待的事件都已经发生。如果计数器入口时值为非零，await会一直堵塞直到计数器为零，或者等待线程中断以及超时。

 
#### 1.3.2 CyclicBarrier同步屏障
类似于闭锁。与闭锁不同之处在于，所有的线程必须同时到达关卡点，才能继续处理。
闭锁等待的是事件；而同步屏障等待的是其他的线程。
常用示例比如：可将一个任务分割成多个子部分，然后再整合。


     
#### 1.3.3 Semaphore计数信号量
- 用来控制能够同时访问某特定资源的活动的数量。
- 计数信号量可以用来实现资源池或者给一个容器限定边界。
- 一个Semaphore管理一个有效的许可，许可的除湿量通过构造函数传递给semaphore活动能够获得许可（只要还有剩余许可），并在使用之后释放许可，如果没有可用的许可，则acquire会被堵塞，直到有可用的为止。
- 常见的信号量使用即数据库连接池。


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
此处测试使用docker-compose来安装集群。[docker-compose](../cosmos-boot/cosmos-consul/docker-compose.yml)

#### 2.2.3 结合springboot
[cosmos-consul案例](../cosmos-boot/cosmos-consul)

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

