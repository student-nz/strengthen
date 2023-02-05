package com.yj.nz;

public class Demo08GCTest {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100 ; i++) {
            new Object();
        }
        System.gc();

    }
}
