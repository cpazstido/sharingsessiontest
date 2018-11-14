package com.cf.sessiontest.stream;

import com.cf.sessiontest.optional.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        // 1. Individual values
        Stream stream = Stream.of("a","b","c");

        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);

        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        //大写转换并循环打印
//        List<String> upperCase = Stream.of(strArray).map(String::toUpperCase).collect(Collectors.toList());
//        upperCase.stream().forEach(str -> System.out.println(str));

        //大写转换
//        List<String> peekOps = Stream.of(new String[]{"one","two","three"})
//                .peek(word-> System.out.println(word))
//                .map(String::toUpperCase)
//                .peek(word-> System.out.println(word))
//                .collect(Collectors.toList());

//        // 1. Array
//        String[] strArray1 = stream.toArray(String[]::new);
//        // 2. Collection
//        List<String> list1 = stream.collect(Collectors.toList());
//        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
//        Set set1 = stream.collect(Collectors.toSet());
//        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
//        // 3. String
//        String str = stream.collect(Collectors.joining()).toString();

        //随机生成100个User
//        Stream.generate(new UserSupplier())
//                .limit(100)
//                .forEach(u-> System.out.println(u.getName()+":"+u.getAge()));

        //对随机生成的100个User分组
        Map<Integer,List<User>> users = Stream.generate(new UserSupplier())
                .limit(100)
                .collect(Collectors.groupingBy(User::getAge));
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer,List<User>> entry = (Map.Entry)it.next();
            Integer age = entry.getKey();
            System.out.println("年龄为:"+age+"的用户有"+entry.getValue().size()+"个");

        }

    }

    private static class UserSupplier implements Supplier<User>{
        private int index = 0;
        private Random random = new Random();
        @Override
        public User get() {
            return new User(index++,"user"+index,random.nextInt(30));
        }
    }
}
