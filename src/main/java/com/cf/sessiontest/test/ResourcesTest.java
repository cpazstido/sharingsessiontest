package com.cf.sessiontest.test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.stream.Stream;

public class ResourcesTest {
    public static void main(String[] args) throws IOException {
        System.out.println(ResourcesTest.class.getResource("META-INF/spring.schemas"));
        System.out.println(ResourcesTest.class.getResource("/META-INF/spring.schemas"));

        System.out.println("************************************************************************");
        Enumeration<URL> urls = ResourcesTest.class.getClassLoader().getResources("META-INF/spring.schemas");
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url.toString());
        }
        System.out.println(ResourcesTest.class.getClassLoader().getResource("META-INF/spring.schemas"));
    }
}
