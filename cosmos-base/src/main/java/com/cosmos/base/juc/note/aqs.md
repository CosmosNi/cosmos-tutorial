## AQS相关源码记录
AQS内部维护一个Node的等待节点。是在CLH（自旋锁，提供先来先到的服务）的基础上的变体。每个node维护一个（volatile int waitStatus）节点状态。

```
node节点源码如下：
static final class Node {
        //节点在共享模式下的标记
        static final Node SHARED = new Node();
        //节点在独占锁模式下的标记
        static final Node EXCLUSIVE = null;
        //标志着线程被取消
        static final int CANCELLED =  1;
        //标志着后继线程(即队列中此节点之后的节点)需要被阻塞.
        static final int SIGNAL    = -1;
        //标志着线程在Condition条件上等待阻塞
        static final int CONDITION = -2;
        //标志着下一个acquireShared方法线程应该被允许。(用于共享锁)
        static final int PROPAGATE = -3;
        /** 1、CANCELLED，值为1 。场景：当该线程等待超时或者被中断，需要从同步队列中取消等待，则该线程被置1，即被取消（这里该线程在取消之前是等待状态）。节点进入了取消状态则不再变化.
        2、SIGNAL，值为-1。场景：后继的节点处于等待状态，当前节点的线程如果释放了同步状态或者被取消（当前节点状态置为-1），将会通知后继节点，使后继节点的线程得以运行；只要前继结点释放锁，就会通知标识为SIGNAL状态的后继结点的线程执行。
        3、CONDITION，值为-2。场景：节点处于等待队列中，节点线程等待在Condition上，当其他线程对Condition调用了signal()方法后，该节点从等待队列中转移到同步队列中，加入到对同步状态的获取中；
        4、PROPAGATE，值为-3。场景：表示下一次的共享状态会被无条件的传播下去；
        5、INITIAL，值为0，初始状态。
        **/
        volatile int waitStatus;

        //前驱节点
        volatile Node prev;
        //后继结点      
        volatile Node next;
        //获取同步状态的线程,进入此节点队列的线程
        volatile Thread thread;
        //等待节点的后继节点。如果当前节点是共享的，那么这个字段是一个SHARED常量，也就是说节点类型（独占和共享）和等待队列中的后继节点共用一个字段。
        Node nextWaiter;

        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        //返回前驱节点
        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {   
        }
        //Used by addWaiter
        Node(Thread thread, Node mode) {
            this.nextWaiter = mode;
            this.thread = thread;
        }
        // Used by Condition
        Node(Thread thread, int waitStatus) {
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }
```
AQS基础源码：
```
// 当前状态值是否等于期望值，如果等于，则更新为给定的更新值
protected final boolean compareAndSetState(int expect, int update) {
    // See below for intrinsics setup to support this
    return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}
```



### AQS独占锁源码：
1. 获取锁
   ```
   public final void acquire(int arg) {
     //尝试获取锁资源，失败则创建等待队列
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
   ```

2. 等待队列的创建
```
//为当前线程和排队模式创建等待节点
private Node addWaiter(Node mode) {
    Node node = new Node(Thread.currentThread(), mode);
    //标记尾节点
    Node pred = tail;
    //这里为了提搞性能，首先执行一次快速入队操作，即直接尝试将新节点加入队尾
    if (pred != null) {
       //设置前驱节点
        node.prev = pred;
       //这里根据CAS的逻辑，即使并发操作也只能有一个线程成功并返回，其余的都要执行后面的入队操作。即enq()方法
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    //插入节点到队列中
    enq(node);
    return node;
}
```

3. 入队操作
```
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        //如果队列还没有初始化，则进行初始化，即创建一个空的头节点
        if (t == null) { // Must initialize
          //同样是CAS，只有一个线程可以初始化头结点成功，其余的都要重复执行循环体
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
           //新创建的节点指向队列尾节点，毫无疑问并发情况下这里会有多个新创建的节点指向队列尾节点
            node.prev = t;
            //基于这一步的CAS，不管前一步有多少新节点都指向了尾节点，这一步只有一个能真正入队成功，其他的都必须重新执行循环体
            if (compareAndSetTail(t, node)) {
                t.next = node;
                //该循环体唯一退出的操作，就是入队成功（否则就要无限重试）
                return t;
            }
        }
    }
}
```

4. 将节点加入等待队列后，独占锁的获取
```
final boolean acquireQueued(final Node node, int arg) {
        //锁资源获取失败标记位
        boolean failed = true;
        try {
            //等待线程被中断标记位
            boolean interrupted = false;
            //这个循环体执行的时机包括新节点入队和队列中等待节点被唤醒两个地方
            for (;;) {
                //获取当前节点的前置节点
                final Node p = node.predecessor();
                //如果前置节点就是头结点，则尝试获取锁资源
                if (p == head && tryAcquire(arg)) {
                    //将当前节点设置为头结点
                    setHead(node);
                    p.next = null; //帮助GC
                    //表示锁资源成功获取
                    failed = false;
                    //返回中断标记，表示当前节点是被正常唤醒还是被中断唤醒
                    return interrupted;
                }
                //如果没有获取锁成功，则进入挂起逻辑
                //无论被中断或者正常唤醒，都会进行重新获取锁。成功则释放，失败则挂起
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            //最后会分析获取锁失败处理逻辑
            if (failed)
                cancelAcquire(node);
        }
    }
```

5. 是否要阻塞当前线程
 ```
//首先说明一下参数，node是当前线程的节点，pred是它的前置节点
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        //获取前置节点的waitStatus
        int ws = pred.waitStatus;
        //如果处于等待唤醒状态，则返回为true进行唤醒
        if (ws == Node.SIGNAL)
            return true;
        if (ws > 0) {
          //循环查找没有被取消的前节点，直到到达头结点
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
          //根据waitStatus的取值限定，这里waitStatus的值只能是0或者PROPAGATE，那么我们把前置节点的waitStatus设为Node.SIGNAL然后重新进入该方法进行堵塞。
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }
    ```

6. 堵塞线程
    ```
    private final boolean parkAndCheckInterrupt() {
      //堵塞线程
    LockSupport.park(this);
    //被唤醒之后，返回中断标记，即如果是正常唤醒则返回false，如果是由于中断醒来，就返回true
    return Thread.interrupted();
}
```

7. 锁资源失败后的处理
    ```
    //当前失败的节点
  private void cancelAcquire(Node node) {
        if (node == null)
            return;

        node.thread = null;

        Node pred = node.prev;
        // 跳过所有已经取消的前置节点，跟上面的那段跳转逻辑类似
        while (pred.waitStatus > 0)
            node.prev = pred = pred.prev;

        //取得的未取消节点的后一节点
        Node predNext = pred.next;
        //把当前节点waitStatus置为取消，这样别的节点在处理时就会跳过该节点
        node.waitStatus = Node.CANCELLED;

        //如果当前是尾节点，则直接删除，即出队
        if (node == tail && compareAndSetTail(node, pred)) {
            compareAndSetNext(pred, predNext, null);
        } else {
            int ws;
            if (pred != head &&
                ((ws = pred.waitStatus) == Node.SIGNAL ||
                 (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                pred.thread != null) {
                Node next = node.next;
                //如果当前节点的前置节点不是头节点且它后面的节点等待它唤醒（waitStatus小于0），如果当前节点的后继节点没有被取消就把前置节点跟后置节点进行连接
                if (next != null && next.waitStatus <= 0)
                    compareAndSetNext(pred, predNext, next);
            } else {
                unparkSuccessor(node);
            }

            node.next = node; // help GC
        }
    }

    ```

8. 锁释放
 ```
public final boolean release(int arg) {
  // 调用tryRelease方法，尝试去释放锁，由子类具体实现
    if (tryRelease(arg)) {
        Node h = head;
        //如果队列头节点的状态不是0，那么队列中就可能存在需要唤醒的等待节点。
        //
        if (h != null && h.waitStatus != 0)
            // 唤醒线程
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```

9. 线程唤醒
 ```
 private void unparkSuccessor(Node node) {

        int ws = node.waitStatus;
        //如果当前节点的状态小于0，则将该节点置为初始状态，标识节点已完成
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);
        Node s = node.next;
              // 如果下一个节点为null，或者状态是已取消，那么就要寻找下一个非取消状态的节点
        if (s == null || s.waitStatus > 0) {
            s = null;
            // 从队列尾向前遍历，直到遍历到node节点
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
           //唤醒线程
            LockSupport.unpark(s.thread);
    }

 ```

### AQS共享锁源码：
1.释放共享锁
```   
private void doAcquireShared(int arg) {
      // 创建一个共享锁节点
       final Node node = addWaiter(Node.SHARED);
       boolean failed = true;
       try {
           boolean interrupted = false;
           for (;;) {
              // 获取下一节点
               final Node p = node.predecessor();
               //前节点为头结点
               if (p == head) {
                 //尝试释放共享锁
                   int r = tryAcquireShared(arg);
                   if (r >= 0) {
                        //获取锁后的唤醒操作
                       setHeadAndPropagate(node, r);
                       p.next = null; // help GC
                       //如果是被中断唤醒，则设置中断标记为
                       if (interrupted)
                           selfInterrupt();
                       failed = false;
                       return;
                   }
               }
               //挂起逻辑同独占锁
               if (shouldParkAfterFailedAcquire(p, node) &&
                   parkAndCheckInterrupt())
                   interrupted = true;
           }
       } finally {
           if (failed)
           //失败逻辑桶独占锁
               cancelAcquire(node);
       }
   }


```
2. 线程唤醒
```   
private void setHeadAndPropagate(Node node, int propagate) {
        //记录头结点以便检查
        Node h = head;
       //设置当前节点为新的头节点
        setHead(node);
        //节点满足如下条件则需要进行唤醒线程
        if (propagate > 0 || h == null || h.waitStatus < 0 ||
            (h = head) == null || h.waitStatus < 0) {
            Node s = node.next;
           //如果节点s是空或者共享模式节点，那么就要唤醒共享锁上等待的线程
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }
  ```     

3. 具体的唤醒逻辑
  ```    
private void doReleaseShared() {
       for (;;) {
           Node h = head;
           //从头结点开始唤醒线程
           if (h != null && h != tail) {
               int ws = h.waitStatus;
               //后继节点需要被唤醒
               if (ws == Node.SIGNAL) {
                  // 将点h的状态设置成0，如果设置失败，就继续循环，再试一次
                   if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                       continue;            // loop to recheck cases
                     //执行唤醒操作
                   unparkSuccessor(h);
               }
               //如果后续节点不需要被唤醒，则设置当前节点为PROPAGATE确保以后可以传递
               else if (ws == 0 &&
                        !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                   continue;                // loop on failed CAS
           }
           //头结点没有发生变化，则表示设置完成，退出循环
           if (h == head)                   // loop if head changed
               break;
       }
   }

  ```    

```
//唤醒node节点的后继节点
private void unparkSuccessor(Node node) {
      //节点的等待状态
       int ws = node.waitStatus;
       //置零当前线程所在的结点状态，允许失败
       if (ws < 0)
           compareAndSetWaitStatus(node, ws, 0);
      //后继节点
       Node s = node.next;
       //后继结点为空或者状态大于0，则无需处理
       if (s == null || s.waitStatus > 0) {
           s = null;
           //从尾节点遍历，找到下一个可唤醒的节点
           for (Node t = tail; t != null && t != node; t = t.prev)
               if (t.waitStatus <= 0)
                   s = t;
       }
       if (s != null)
          //唤醒线程
           LockSupport.unpark(s.thread);
   }
   ```
