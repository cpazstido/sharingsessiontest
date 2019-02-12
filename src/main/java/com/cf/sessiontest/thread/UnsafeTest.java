package com.cf.sessiontest.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import static sun.nio.ch.IOStatus.normalize;

public class UnsafeTest {
    static class Validation {
        private int MAX_SIZE = 10;
        public boolean sizeValidate() {
            return 20 < MAX_SIZE;
        }
    }

    static long toAddress(Object obj) {
        Object[] array = new Object[] {obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(getUnsafe().getInt(array, baseOffset));
    }

    static Object fromAddress(long address) {
        Object[] array = new Object[] {null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }

    public static Unsafe getUnsafe(){
        Unsafe unsafe = null;
        try {
            // 通过反射得到theUnsafe对应的Field对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置该Field为可访问
            field.setAccessible(true);
            // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    public static void main(String[] args) throws Exception{

        Validation v = new Validation();
        long address = toAddress(v);
        System.out.println(address);
        AtomicInteger atomicInteger = new AtomicInteger();
    }
}
