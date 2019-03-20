package com.cf.sessiontest.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        System.out.println(5&1);
        System.out.println((5>>>1)&1);
        System.out.println((5>>>2)&1);
        System.out.println((5>>>3)&1);
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();

        HashMap hashMap = new HashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TreeMap treeMap = new TreeMap();


        HashSet hashSet = new HashSet();

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        TreeSet treeSet = new TreeSet();
        treeMap.put("","");

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("","");


    }

    static class AA{}
}
