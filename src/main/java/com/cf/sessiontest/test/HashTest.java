package com.cf.sessiontest.test;

import com.cf.sessiontest.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class HashTest {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        User user = new User("1","a",2);
        map.put(user,"1");
        System.out.println(map.get(new User("1","a",2)));
        Map map1 = new Hashtable();
        map1.put(null,"a");
        new TreeMap();
        new ConcurrentHashMap<>();
    }
}
