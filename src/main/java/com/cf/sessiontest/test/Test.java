package com.cf.sessiontest.test;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("javax.servlet.Servlet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
