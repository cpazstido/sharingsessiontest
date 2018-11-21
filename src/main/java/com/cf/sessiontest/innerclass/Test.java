package com.cf.sessiontest.innerclass;

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.setName("cf");
        Outer.FirstInner firstInner = outer.new FirstInner();
        Outer.FirstInner.SecondInner secondInner = firstInner.new SecondInner();
        Outer.FirstInner.SecondInner.ThirdInner thirdInner = secondInner.new ThirdInner();
        System.out.println("");
    }
}
