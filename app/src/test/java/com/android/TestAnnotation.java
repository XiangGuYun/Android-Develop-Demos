package com.android;

public class TestAnnotation {

    public @interface JavaTag{
        String name();
        int age();
    }

    public @interface JavaTagWithArray{
        public String[] value();
    }

    public @interface JavaTagWithArray1{
        public String[] books();
    }

}
