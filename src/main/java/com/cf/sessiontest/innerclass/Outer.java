package com.cf.sessiontest.innerclass;

public class Outer {//this$0

    public class FirstInner {//this$1

        public class SecondInner {//this$2

            public class ThirdInner {
            }
        }
    }

    public class FirstInner2{
        public class SecondInner {//this$2

            public class ThirdInner {
            }
        }
    }

    private String name;

    public Outer(){
        this.name = "default";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
