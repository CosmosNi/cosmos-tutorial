package com.cosmos.base.juc;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 无锁Vector实现
 *
 * @param <E>
 */
public class LockFreeVector<E> {

    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private final int N_BUCKET = 30;
    private final int FIRST_BUCKET = 1;
    private final AtomicReference<Descriptor<E>> descriptor;

    public LockFreeVector(AtomicReferenceArray<AtomicReferenceArray<E>> buckets) {
        this.buckets = new AtomicReferenceArray<>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<>(FIRST_BUCKET));
        descriptor = new AtomicReference<>(new Descriptor<>(0, null));
    }

    public void push_back(E e) {
        Descriptor<E> desc;
        Descriptor<E> newd;
        do {
            desc = descriptor.get();
            desc.completeWrite();

            int pos = desc.size + FIRST_BUCKET;
            int zeroNumpos = Integer.numberOfLeadingZeros(pos);
            int bucketInd = zeroNumpos - zeroNumpos;
            if (buckets.get(bucketInd) == null) {
                int newLen = 2 * buckets.get(bucketInd - 1).length();
                buckets.compareAndSet(bucketInd, null, new AtomicReferenceArray<>(newLen));
            }
            int idx = (0x80000000 >> zeroNumpos) ^ pos;
            newd = new Descriptor<E>(desc.size + 1, new WriteDescriptor<E>(buckets.get(bucketInd), idx, null, e));

        } while (!descriptor.compareAndSet(desc, newd));
        descriptor.get().completeWrite();
    }


    static class Descriptor<E> {
        public int size;
        volatile WriteDescriptor<E> writeop;

        public Descriptor(int size, WriteDescriptor<E> writeop) {
            this.size = size;
            this.writeop = writeop;
        }

        public void completeWrite() {
            WriteDescriptor<E> tmpOp = writeop;
            if (tmpOp != null) {
                tmpOp.doIt();
                writeop = null;
            }
        }
    }

    static class WriteDescriptor<E> {
        public E oldV;
        public E newV;
        public AtomicReferenceArray<E> addr;
        public int addr_ind;

        public WriteDescriptor(AtomicReferenceArray<E> addr, int addr_ind, E oldV, E newV) {
            this.addr = addr;
            this.addr_ind = addr_ind;
            this.oldV = oldV;
            this.newV = newV;

        }

        public void doIt() {
            addr.compareAndSet(addr_ind, oldV, newV);
        }
    }
}
