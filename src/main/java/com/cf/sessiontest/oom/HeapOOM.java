package com.cf.sessiontest.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出
 * 大对象会被直接分配到老年代，所谓的大对象是指需要大量连续存储空间的对象，最常见的一种大对象就是大数组
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        HeapOOM[] j = new HeapOOM[999999999];
    }
}
