package com.yj.nz.d2_api.d1_currentthread;

public class CurrentthreadDemo {
    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
