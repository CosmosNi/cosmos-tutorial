///*
// * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */
//
//package com.cosmos.base.array;
//
//import java.util.*;
//import java.util.function.Consumer;
//
///**
// * @Author: Cosmos
// * @program: data-structure
// * @Description: 双向链表的实现
// * 实现Iterable，才能够使用增强for循环
// * 链表与数组一样，都作为数据的基本存储结构，
// * 但是在存储原理上二者是不同的。
// * 在数组中，数据是存储在一段连续的内存空间中，我们可以通过下标来访问数组中的元素；
// * 而在链表中，元素是存储在不同的内存空间中，前一个元素的位置维护了后一个元素在内存中的地址，
// * 在Java中，就是前一个元素维护了后一个元素的引用。
// * 适合增删改
// * @Date: Create in 2018-12-06 10:41
// * @Modified By：
// */
//
//
//public class LinkedList<E>
//        extends AbstractSequentialList<E>
//        implements List<E>, Deque<E>, Cloneable, java.io.Serializable {
//    transient int size = 0;
//
//    /**
//     * 标记起始节点
//     */
//    transient Node<E> first;
//
//    /**
//     * 标记结束节点
//     */
//    transient Node<E> last;
//
//    /**
//     * Constructs an empty list.
//     */
//    public LinkedList() {
//    }
//
//    /**
//     * 初始化集合
//     *
//     * @param c
//     */
//    public LinkedList(Collection<? extends E> c) {
//        this();
//        addAll(c);
//    }
//
//    /**
//     * 将元素链接到头
//     *
//     * @param e
//     */
//    private void linkFirst(E e) {
//        final Node<E> f = first;
//        final Node<E> newNode = new Node<>(null, e, f);
//        first = newNode;
//        //当初始节点为空时，新增的节点同时也为最后节点
//        //当初始节点不为空时，则原初始元素的前一个节点为新节点
//        if (f == null)
//            last = newNode;
//        else
//            f.prev = newNode;
//        size++;
//        modCount++;
//    }
//
//    /**
//     * 将元素链接到尾部
//     */
//    void linkLast(E e) {
//        final Node<E> l = last;
//        final Node<E> newNode = new Node<>(l, e, null);
//        last = newNode;
//        if (l == null)
//            first = newNode;
//        else
//            l.next = newNode;
//        size++;
//        modCount++;
//    }
//
//    /**
//     * 将元素链接到指定元素之前
//     */
//    void linkBefore(E e, Node<E> succ) {
//        // assert succ != null;
//        final Node<E> pred = succ.prev;
//        //初始化节点，前节点为pred，后节点为succ
//        final Node<E> newNode = new Node<>(pred, e, succ);
//        //将succ的前节点放置新节点
//        succ.prev = newNode;
//        //如果前元素为null，则说明该节点为起始节点
//        if (pred == null)
//            first = newNode;
//        else
//            pred.next = newNode;
//        size++;
//        modCount++;
//    }
//
//    /**
//     * 删除头节点
//     */
//    private E unlinkFirst(Node<E> f) {
//        // assert f == first && f != null;
//        final E element = f.item;
//        final Node<E> next = f.next;
//        f.item = null;
//        f.next = null; // help GC
//        first = next;
//        if (next == null)
//            last = null;
//        else
//            next.prev = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    /**
//     * 删除尾节点
//     */
//    private E unlinkLast(Node<E> l) {
//        // assert l == last && l != null;
//        final E element = l.item;
//        final Node<E> prev = l.prev;
//        l.item = null;
//        l.prev = null; // help GC
//        last = prev;
//        if (prev == null)
//            first = null;
//        else
//            prev.next = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    /**
//     * 删除某个节点
//     */
//    E unlink(Node<E> x) {
//        // assert x != null;
//        final E element = x.item;
//        final Node<E> next = x.next;
//        final Node<E> prev = x.prev;
//
//        //当前节点为null时，说明该节点为头结点
//        if (prev == null) {
//            first = next;
//        } else {
//            prev.next = next;
//            x.prev = null;
//        }
//        //当后节点为null时，说明该节点为尾结点
//        if (next == null) {
//            last = prev;
//        } else {
//            next.prev = prev;
//            x.next = null;
//        }
//
//        x.item = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    /**
//     * 获取头结点
//     */
//    public E getFirst() {
//        final Node<E> f = first;
//        if (f == null)
//            throw new NoSuchElementException();
//        return f.item;
//    }
//
//    /**
//     * 获取尾节点
//     *
//     * @return
//     */
//    public E getLast() {
//        final Node<E> l = last;
//        if (l == null)
//            throw new NoSuchElementException();
//        return l.item;
//    }
//
//    /**
//     * 移除头结点
//     *
//     * @return
//     */
//    public E removeFirst() {
//        final Node<E> f = first;
//        if (f == null)
//            throw new NoSuchElementException();
//        return unlinkFirst(f);
//    }
//
//    /**
//     * 移除尾节点
//     *
//     * @return
//     */
//    public E removeLast() {
//        final Node<E> l = last;
//        if (l == null)
//            throw new NoSuchElementException();
//        return unlinkLast(l);
//    }
//
//    /**
//     * 将元素添加到头结点
//     *
//     * @param e
//     */
//    public void addFirst(E e) {
//        linkFirst(e);
//    }
//
//    /**
//     * 将元素添加到尾节点
//     *
//     * @param e
//     */
//    public void addLast(E e) {
//        linkLast(e);
//    }
//
//    /**
//     * 是否包含元素
//     *
//     * @param o
//     * @return
//     */
//    public boolean contains(Object o) {
//        return indexOf(o) != -1;
//    }
//
//    /**
//     * 集合长度
//     *
//     * @return
//     */
//    public int size() {
//        return size;
//    }
//
//    /**
//     * 添加元素。默认添加到最后面
//     *
//     * @param e
//     * @return
//     */
//    public boolean add(E e) {
//        linkLast(e);
//        return true;
//    }
//
//    /**
//     * 移除元素
//     *
//     * @param o
//     * @return
//     */
//    public boolean remove(Object o) {
//        //元素为null，则查询item为null的元素，移除节点
//        if (o == null) {
//            for (Node<E> x = first; x != null; x = x.next) {
//                if (x.item == null) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        } else {
//            for (Node<E> x = first; x != null; x = x.next) {
//                if (o.equals(x.item)) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean addAll(Collection<? extends E> c) {
//        return addAll(size, c);
//    }
//
//    /**
//     * 添加集合
//     *
//     * @param index
//     * @param c
//     * @return
//     */
//    public boolean addAll(int index, Collection<? extends E> c) {
//        checkPositionIndex(index);
//
//        Object[] a = c.toArray();
//        int numNew = a.length;
//        if (numNew == 0)
//            return false;
//
//        //用于记录前节点和后节点
//        Node<E> pred, succ;
//
//        if (index == size) {
//            succ = null;
//            pred = last;
//        } else {
//            succ = node(index);
//            pred = succ.prev;
//        }
//
//        for (Object o : a) {
//            @SuppressWarnings("unchecked") E e = (E) o;
//            Node<E> newNode = new Node<>(pred, e, null);
//            //如果前节点为null，说明新增的节点为头结点，则将其设置为头结点
//            if (pred == null)
//                first = newNode;
//            else
//                pred.next = newNode;
//            //前节点为当前节点，进行下一次遍历
//            pred = newNode;
//        }
//        //succ ==null说明无后节点，最后的节点即为集合中最后的节点
//        if (succ == null) {
//            last = pred;
//        } else {
//            pred.next = succ;
//            succ.prev = pred;
//        }
//
//        size += numNew;
//        modCount++;
//        return true;
//    }
//
//    /**
//     * Removes all of the elements from this list.
//     * The list will be empty after this call returns.
//     */
//    public void clear() {
//        // Clearing all of the links between nodes is "unnecessary", but:
//        // - helps a generational GC if the discarded nodes inhabit
//        //   more than one generation
//        // - is sure to free memory even if there is a reachable Iterator
//        for (Node<E> x = first; x != null; ) {
//            Node<E> next = x.next;
//            x.item = null;
//            x.next = null;
//            x.prev = null;
//            x = next;
//        }
//        first = last = null;
//        size = 0;
//        modCount++;
//    }
//
//
//    // Positional Access Operations
//
//    /**
//     * Returns the element at the specified position in this list.
//     *
//     * @param index index of the element to return
//     * @return the element at the specified position in this list
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public E get(int index) {
//        checkElementIndex(index);
//        return node(index).item;
//    }
//
//    /**
//     * Replaces the element at the specified position in this list with the
//     * specified element.
//     *
//     * @param index   index of the element to replace
//     * @param element element to be stored at the specified position
//     * @return the element previously at the specified position
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public E set(int index, E element) {
//        checkElementIndex(index);
//        Node<E> x = node(index);
//        E oldVal = x.item;
//        x.item = element;
//        return oldVal;
//    }
//
//    /**
//     * Inserts the specified element at the specified position in this list.
//     * Shifts the element currently at that position (if any) and any
//     * subsequent elements to the right (adds one to their indices).
//     *
//     * @param index   index at which the specified element is to be inserted
//     * @param element element to be inserted
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public void add(int index, E element) {
//        checkPositionIndex(index);
//
//        if (index == size)
//            linkLast(element);
//        else
//            linkBefore(element, node(index));
//    }
//
//    /**
//     * Removes the element at the specified position in this list.  Shifts any
//     * subsequent elements to the left (subtracts one from their indices).
//     * Returns the element that was removed from the list.
//     *
//     * @param index the index of the element to be removed
//     * @return the element previously at the specified position
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public E remove(int index) {
//        checkElementIndex(index);
//        return unlink(node(index));
//    }
//
//    /**
//     * Tells if the argument is the index of an existing element.
//     */
//    private boolean isElementIndex(int index) {
//        return index >= 0 && index < size;
//    }
//
//    /**
//     * Tells if the argument is the index of a valid position for an
//     * iterator or an add operation.
//     */
//    private boolean isPositionIndex(int index) {
//        return index >= 0 && index <= size;
//    }
//
//    /**
//     * Constructs an IndexOutOfBoundsException detail message.
//     * Of the many possible refactorings of the error handling code,
//     * this "outlining" performs best with both server and client VMs.
//     */
//    private String outOfBoundsMsg(int index) {
//        return "Index: " + index + ", Size: " + size;
//    }
//
//    private void checkElementIndex(int index) {
//        if (!isElementIndex(index))
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//
//    private void checkPositionIndex(int index) {
//        if (!isPositionIndex(index))
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//
//    /**
//     * Returns the (non-null) Node at the specified element index.
//     */
//    Node<E> node(int index) {
//        // assert isElementIndex(index);
//
//        if (index < (size >> 1)) {
//            Node<E> x = first;
//            for (int i = 0; i < index; i++)
//                x = x.next;
//            return x;
//        } else {
//            Node<E> x = last;
//            for (int i = size - 1; i > index; i--)
//                x = x.prev;
//            return x;
//        }
//    }
//
//    // Search Operations
//
//    /**
//     * Returns the index of the first occurrence of the specified element
//     * in this list, or -1 if this list does not contain the element.
//     * More formally, returns the lowest index {@code i} such that
//     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
//     * or -1 if there is no such index.
//     *
//     * @param o element to search for
//     * @return the index of the first occurrence of the specified element in
//     * this list, or -1 if this list does not contain the element
//     */
//    public int indexOf(Object o) {
//        int index = 0;
//        if (o == null) {
//            for (Node<E> x = first; x != null; x = x.next) {
//                if (x.item == null)
//                    return index;
//                index++;
//            }
//        } else {
//            for (Node<E> x = first; x != null; x = x.next) {
//                if (o.equals(x.item))
//                    return index;
//                index++;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * Returns the index of the last occurrence of the specified element
//     * in this list, or -1 if this list does not contain the element.
//     * More formally, returns the highest index {@code i} such that
//     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
//     * or -1 if there is no such index.
//     *
//     * @param o element to search for
//     * @return the index of the last occurrence of the specified element in
//     * this list, or -1 if this list does not contain the element
//     */
//    public int lastIndexOf(Object o) {
//        int index = size;
//        if (o == null) {
//            for (Node<E> x = last; x != null; x = x.prev) {
//                index--;
//                if (x.item == null)
//                    return index;
//            }
//        } else {
//            for (Node<E> x = last; x != null; x = x.prev) {
//                index--;
//                if (o.equals(x.item))
//                    return index;
//            }
//        }
//        return -1;
//    }
//
//    // Queue operations.
//
//    /**
//     * Retrieves, but does not remove, the head (first element) of this list.
//     *
//     * @return the head of this list, or {@code null} if this list is empty
//     * @since 1.5
//     */
//    public E peek() {
//        final Node<E> f = first;
//        return (f == null) ? null : f.item;
//    }
//
//    /**
//     * Retrieves, but does not remove, the head (first element) of this list.
//     *
//     * @return the head of this list
//     * @throws NoSuchElementException if this list is empty
//     * @since 1.5
//     */
//    public E element() {
//        return getFirst();
//    }
//
//    /**
//     * Retrieves and removes the head (first element) of this list.
//     *
//     * @return the head of this list, or {@code null} if this list is empty
//     * @since 1.5
//     */
//    public E poll() {
//        final Node<E> f = first;
//        return (f == null) ? null : unlinkFirst(f);
//    }
//
//    /**
//     * Retrieves and removes the head (first element) of this list.
//     *
//     * @return the head of this list
//     * @throws NoSuchElementException if this list is empty
//     * @since 1.5
//     */
//    public E remove() {
//        return removeFirst();
//    }
//
//    /**
//     * Adds the specified element as the tail (last element) of this list.
//     *
//     * @param e the element to add
//     * @return {@code true} (as specified by {@link Queue#offer})
//     * @since 1.5
//     */
//    public boolean offer(E e) {
//        return add(e);
//    }
//
//    // Deque operations
//
//    /**
//     * Inserts the specified element at the front of this list.
//     *
//     * @param e the element to insert
//     * @return {@code true} (as specified by {@link Deque#offerFirst})
//     * @since 1.6
//     */
//    public boolean offerFirst(E e) {
//        addFirst(e);
//        return true;
//    }
//
//    /**
//     * Inserts the specified element at the end of this list.
//     *
//     * @param e the element to insert
//     * @return {@code true} (as specified by {@link Deque#offerLast})
//     * @since 1.6
//     */
//    public boolean offerLast(E e) {
//        addLast(e);
//        return true;
//    }
//
//    /**
//     * Retrieves, but does not remove, the first element of this list,
//     * or returns {@code null} if this list is empty.
//     *
//     * @return the first element of this list, or {@code null}
//     * if this list is empty
//     * @since 1.6
//     */
//    public E peekFirst() {
//        final Node<E> f = first;
//        return (f == null) ? null : f.item;
//    }
//
//    /**
//     * Retrieves, but does not remove, the last element of this list,
//     * or returns {@code null} if this list is empty.
//     *
//     * @return the last element of this list, or {@code null}
//     * if this list is empty
//     * @since 1.6
//     */
//    public E peekLast() {
//        final Node<E> l = last;
//        return (l == null) ? null : l.item;
//    }
//
//    /**
//     * Retrieves and removes the first element of this list,
//     * or returns {@code null} if this list is empty.
//     *
//     * @return the first element of this list, or {@code null} if
//     * this list is empty
//     * @since 1.6
//     */
//    public E pollFirst() {
//        final Node<E> f = first;
//        return (f == null) ? null : unlinkFirst(f);
//    }
//
//    /**
//     * Retrieves and removes the last element of this list,
//     * or returns {@code null} if this list is empty.
//     *
//     * @return the last element of this list, or {@code null} if
//     * this list is empty
//     * @since 1.6
//     */
//    public E pollLast() {
//        final Node<E> l = last;
//        return (l == null) ? null : unlinkLast(l);
//    }
//
//    /**
//     * Pushes an element onto the stack represented by this list.  In other
//     * words, inserts the element at the front of this list.
//     *
//     * <p>This method is equivalent to {@link #addFirst}.
//     *
//     * @param e the element to push
//     * @since 1.6
//     */
//    public void push(E e) {
//        addFirst(e);
//    }
//
//    /**
//     * Pops an element from the stack represented by this list.  In other
//     * words, removes and returns the first element of this list.
//     *
//     * <p>This method is equivalent to {@link #removeFirst()}.
//     *
//     * @return the element at the front of this list (which is the top
//     * of the stack represented by this list)
//     * @throws NoSuchElementException if this list is empty
//     * @since 1.6
//     */
//    public E pop() {
//        return removeFirst();
//    }
//
//    /**
//     * Removes the first occurrence of the specified element in this
//     * list (when traversing the list from head to tail).  If the list
//     * does not contain the element, it is unchanged.
//     *
//     * @param o element to be removed from this list, if present
//     * @return {@code true} if the list contained the specified element
//     * @since 1.6
//     */
//    public boolean removeFirstOccurrence(Object o) {
//        return remove(o);
//    }
//
//    /**
//     * Removes the last occurrence of the specified element in this
//     * list (when traversing the list from head to tail).  If the list
//     * does not contain the element, it is unchanged.
//     *
//     * @param o element to be removed from this list, if present
//     * @return {@code true} if the list contained the specified element
//     * @since 1.6
//     */
//    public boolean removeLastOccurrence(Object o) {
//        if (o == null) {
//            for (Node<E> x = last; x != null; x = x.prev) {
//                if (x.item == null) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        } else {
//            for (Node<E> x = last; x != null; x = x.prev) {
//                if (o.equals(x.item)) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 迭代器
//     * @param index
//     * @return
//     */
//    public ListIterator<E> listIterator(int index) {
//        checkPositionIndex(index);
//        return new ListItr(index);
//    }
//
//    private class ListItr implements ListIterator<E> {
//        private Node<E> lastReturned;
//        private Node<E> next;
//        private int nextIndex;
//        private int expectedModCount = modCount;
//
//        ListItr(int index) {
//            // assert isPositionIndex(index);
//            next = (index == size) ? null : node(index);
//            nextIndex = index;
//        }
//
//        public boolean hasNext() {
//            return nextIndex < size;
//        }
//
//        public E next() {
//            checkForComodification();
//            if (!hasNext())
//                throw new NoSuchElementException();
//
//            lastReturned = next;
//            next = next.next;
//            nextIndex++;
//            return lastReturned.item;
//        }
//
//        public boolean hasPrevious() {
//            return nextIndex > 0;
//        }
//
//        public E previous() {
//            checkForComodification();
//            if (!hasPrevious())
//                throw new NoSuchElementException();
//
//            lastReturned = next = (next == null) ? last : next.prev;
//            nextIndex--;
//            return lastReturned.item;
//        }
//
//        public int nextIndex() {
//            return nextIndex;
//        }
//
//        public int previousIndex() {
//            return nextIndex - 1;
//        }
//
//        public void remove() {
//            checkForComodification();
//            if (lastReturned == null)
//                throw new IllegalStateException();
//
//            Node<E> lastNext = lastReturned.next;
//            unlink(lastReturned);
//            if (next == lastReturned)
//                next = lastNext;
//            else
//                nextIndex--;
//            lastReturned = null;
//            expectedModCount++;
//        }
//
//        public void set(E e) {
//            if (lastReturned == null)
//                throw new IllegalStateException();
//            checkForComodification();
//            lastReturned.item = e;
//        }
//
//        public void add(E e) {
//            checkForComodification();
//            lastReturned = null;
//            if (next == null)
//                linkLast(e);
//            else
//                linkBefore(e, next);
//            nextIndex++;
//            expectedModCount++;
//        }
//
//        public void forEachRemaining(Consumer<? super E> action) {
//            Objects.requireNonNull(action);
//            while (modCount == expectedModCount && nextIndex < size) {
//                action.accept(next.item);
//                lastReturned = next;
//                next = next.next;
//                nextIndex++;
//            }
//            checkForComodification();
//        }
//
//        final void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//    }
//
//    private static class Node<E> {
//        E item;
//        Node<E> next;
//        Node<E> prev;
//
//        Node(Node<E> prev, E element, Node<E> next) {
//            this.item = element;
//            this.next = next;
//            this.prev = prev;
//        }
//    }
//
//    /**
//     * @since 1.6
//     */
//    public Iterator<E> descendingIterator() {
//        return new DescendingIterator();
//    }
//
//    /**
//     * Adapter to provide descending iterators via ListItr.previous
//     */
//    private class DescendingIterator implements Iterator<E> {
//        private final ListItr itr = new ListItr(size());
//
//        public boolean hasNext() {
//            return itr.hasPrevious();
//        }
//
//        public E next() {
//            return itr.previous();
//        }
//
//        public void remove() {
//            itr.remove();
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private LinkedList<E> superClone() {
//        try {
//            return (LinkedList<E>) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new InternalError(e);
//        }
//    }
//
//    /**
//     * Returns a shallow copy of this {@code LinkedList}. (The elements
//     * themselves are not cloned.)
//     *
//     * @return a shallow copy of this {@code LinkedList} instance
//     */
//    public Object clone() {
//        LinkedList<E> clone = superClone();
//
//        // Put clone into "virgin" state
//        clone.first = clone.last = null;
//        clone.size = 0;
//        clone.modCount = 0;
//
//        // Initialize clone with our elements
//        for (Node<E> x = first; x != null; x = x.next)
//            clone.add(x.item);
//
//        return clone;
//    }
//
//    /**
//     * Returns an array containing all of the elements in this list
//     * in proper sequence (from first to last element).
//     *
//     * <p>The returned array will be "safe" in that no references to it are
//     * maintained by this list.  (In other words, this method must allocate
//     * a new array).  The caller is thus free to modify the returned array.
//     *
//     * <p>This method acts as bridge between array-based and collection-based
//     * APIs.
//     *
//     * @return an array containing all of the elements in this list
//     * in proper sequence
//     */
//    public Object[] toArray() {
//        Object[] result = new Object[size];
//        int i = 0;
//        for (Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.item;
//        return result;
//    }
//
//    /**
//     * Returns an array containing all of the elements in this list in
//     * proper sequence (from first to last element); the runtime type of
//     * the returned array is that of the specified array.  If the list fits
//     * in the specified array, it is returned therein.  Otherwise, a new
//     * array is allocated with the runtime type of the specified array and
//     * the size of this list.
//     *
//     * <p>If the list fits in the specified array with room to spare (i.e.,
//     * the array has more elements than the list), the element in the array
//     * immediately following the end of the list is set to {@code null}.
//     * (This is useful in determining the length of the list <i>only</i> if
//     * the caller knows that the list does not contain any null elements.)
//     *
//     * <p>Like the {@link #toArray()} method, this method acts as bridge between
//     * array-based and collection-based APIs.  Further, this method allows
//     * precise control over the runtime type of the output array, and may,
//     * under certain circumstances, be used to save allocation costs.
//     *
//     * <p>Suppose {@code x} is a list known to contain only strings.
//     * The following code can be used to dump the list into a newly
//     * allocated array of {@code String}:
//     *
//     * <pre>
//     *     String[] y = x.toArray(new String[0]);</pre>
//     * <p>
//     * Note that {@code toArray(new Object[0])} is identical in function to
//     * {@code toArray()}.
//     *
//     * @param a the array into which the elements of the list are to
//     *          be stored, if it is big enough; otherwise, a new array of the
//     *          same runtime type is allocated for this purpose.
//     * @return an array containing the elements of the list
//     * @throws ArrayStoreException  if the runtime type of the specified array
//     *                              is not a supertype of the runtime type of every element in
//     *                              this list
//     * @throws NullPointerException if the specified array is null
//     */
//    @SuppressWarnings("unchecked")
//    public <T> T[] toArray(T[] a) {
//        if (a.length < size)
//            a = (T[]) java.lang.reflect.Array.newInstance(
//                    a.getClass().getComponentType(), size);
//        int i = 0;
//        Object[] result = a;
//        for (Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.item;
//
//        if (a.length > size)
//            a[size] = null;
//
//        return a;
//    }
//
//    private static final long serialVersionUID = 876323262645176354L;
//
//    /**
//     * Saves the state of this {@code LinkedList} instance to a stream
//     * (that is, serializes it).
//     *
//     * @serialData The size of the list (the number of elements it
//     * contains) is emitted (int), followed by all of its
//     * elements (each an Object) in the proper order.
//     */
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws java.io.IOException {
//        // Write out any hidden serialization magic
//        s.defaultWriteObject();
//
//        // Write out size
//        s.writeInt(size);
//
//        // Write out all elements in the proper order.
//        for (Node<E> x = first; x != null; x = x.next)
//            s.writeObject(x.item);
//    }
//
//    /**
//     * Reconstitutes this {@code LinkedList} instance from a stream
//     * (that is, deserializes it).
//     */
//    @SuppressWarnings("unchecked")
//    private void readObject(java.io.ObjectInputStream s)
//            throws java.io.IOException, ClassNotFoundException {
//        // Read in any hidden serialization magic
//        s.defaultReadObject();
//
//        // Read in size
//        int size = s.readInt();
//
//        // Read in all elements in the proper order.
//        for (int i = 0; i < size; i++)
//            linkLast((E) s.readObject());
//    }
//
//    /**
//     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
//     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
//     * list.
//     *
//     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
//     * {@link Spliterator#ORDERED}.  Overriding implementations should document
//     * the reporting of additional characteristic values.
//     *
//     * @return a {@code Spliterator} over the elements in this list
//     * @implNote The {@code Spliterator} additionally reports {@link Spliterator#SUBSIZED}
//     * and implements {@code trySplit} to permit limited parallelism..
//     * @since 1.8
//     */
//    @Override
//    public Spliterator<E> spliterator() {
//        return new LLSpliterator<E>(this, -1, 0);
//    }
//
//    /**
//     * A customized variant of Spliterators.IteratorSpliterator
//     */
//    static final class LLSpliterator<E> implements Spliterator<E> {
//        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
//        static final int MAX_BATCH = 1 << 25;  // max batch array size;
//        final LinkedList<E> list; // null OK unless traversed
//        Node<E> current;      // current node; null until initialized
//        int est;              // size estimate; -1 until first needed
//        int expectedModCount; // initialized when est set
//        int batch;            // batch size for splits
//
//        LLSpliterator(LinkedList<E> list, int est, int expectedModCount) {
//            this.list = list;
//            this.est = est;
//            this.expectedModCount = expectedModCount;
//        }
//
//        final int getEst() {
//            int s; // force initialization
//            final LinkedList<E> lst;
//            if ((s = est) < 0) {
//                if ((lst = list) == null)
//                    s = est = 0;
//                else {
//                    expectedModCount = lst.modCount;
//                    current = lst.first;
//                    s = est = lst.size;
//                }
//            }
//            return s;
//        }
//
//        public long estimateSize() {
//            return (long) getEst();
//        }
//
//        public Spliterator<E> trySplit() {
//            Node<E> p;
//            int s = getEst();
//            if (s > 1 && (p = current) != null) {
//                int n = batch + BATCH_UNIT;
//                if (n > s)
//                    n = s;
//                if (n > MAX_BATCH)
//                    n = MAX_BATCH;
//                Object[] a = new Object[n];
//                int j = 0;
//                pojo {
//                    a[j++] = p.item;
//                } while ((p = p.next) != null && j < n);
//                current = p;
//                batch = j;
//                est = s - j;
//                return Spliterators.spliterator(a, 0, j, Spliterator.ORDERED);
//            }
//            return null;
//        }
//
//        public void forEachRemaining(Consumer<? super E> action) {
//            Node<E> p;
//            int n;
//            if (action == null) throw new NullPointerException();
//            if ((n = getEst()) > 0 && (p = current) != null) {
//                current = null;
//                est = 0;
//                pojo {
//                    E e = p.item;
//                    p = p.next;
//                    action.accept(e);
//                } while (p != null && --n > 0);
//            }
//            if (list.modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//
//        public boolean tryAdvance(Consumer<? super E> action) {
//            Node<E> p;
//            if (action == null) throw new NullPointerException();
//            if (getEst() > 0 && (p = current) != null) {
//                --est;
//                E e = p.item;
//                current = p.next;
//                action.accept(e);
//                if (list.modCount != expectedModCount)
//                    throw new ConcurrentModificationException();
//                return true;
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
//        }
//    }
//
//}
