package com.cosmos.base.array;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Iterator;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.array
 * @ClassName: SimpleLinkedList
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/19 13:35
 * @Version: 1.0
 */
public class SimpleLinkedList<T> implements Iterable {
    private Node<T> firstNode;

    private Node<T> lastNode;

    private int size = 0;

    public SimpleLinkedList() {
    }

    public void addFirst(T element) {
        final Node f = firstNode;
        Node newNode = new Node<>(null, f, element);
        firstNode = newNode;
        if (f == null) {
            lastNode = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(T element) {
        final Node l = lastNode;
        Node newNode = new Node(l, null, element);
        lastNode = newNode;
        if (l == null) {
            firstNode = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean remove(T element) {
        if (element == null) {
            for (Node x = firstNode; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = firstNode; x != null; x = x.next) {
                if (x.item.equals(element)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private void unlink(Node<T> element) {
        if (element.prev == null) {
            firstNode = element.next;
        } else {
            element.prev.next = element.next;
            element.prev = null;
        }
        if (element.next == null) {
            lastNode = element.prev;
        } else {
            element.next.prev = element.prev;
            element.next = null;
        }
        element.item = null;
        size--;
    }


    @Data
    @AllArgsConstructor
    private class Node<T> {

        Node<T> prev;
        Node<T> next;
        T item;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        if (!isEmpty()) {
            Node current = firstNode;
            while (current != null) {
                System.out.print(current.item + "\t");
                current = current.next;
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new NodeIterator<T>(firstNode);
    }

    public boolean contain(T element) {
        if (element == null) {
            for (Node x = firstNode; x != null; x = x.next) {
                if (x.item == null) {
                    return true;
                }
            }
        } else {
            for (Node x = firstNode; x != null; x = x.next) {
                if (x.item.equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public class NodeIterator<T> implements Iterator<T> {

        private Node node;

        public NodeIterator(Node node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {

            return node.next != null;
        }

        @Override
        public T next() {
            node = node.next;

            return (T) node.item;
        }
    }


    public static void main(String[] args) {
        SimpleLinkedList<Integer> simpleLinkList = new SimpleLinkedList();
        for (int i = 0; i < 10; i++) {
            simpleLinkList.addFirst(i);
        }

//        simpleLinkList.display();
        for (int i = 1; i < 10; i++) {
            simpleLinkList.addLast(i);
        }
        simpleLinkList.remove(0);

        simpleLinkList.display();
        System.out.println(simpleLinkList.contain(0));

        Iterator<Integer> iterator = simpleLinkList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.print(next + "\t");
        }
        System.out.println();
        for (Object data : simpleLinkList) {
            System.out.print(data + "\t");
        }
    }
}
