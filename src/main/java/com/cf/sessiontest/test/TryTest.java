package com.cf.sessiontest.test;

public class TryTest {
    public static void main(String[] args) {
        System.out.println(new TryTest().test());;
    }

    static int test()
    {
        int x = 1;
        try
        {
            x++;
            return x;
        }
        finally
        {
            ++x;
        }
    }
}
