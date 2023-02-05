package com.yj.nz;

public class Demo02SimpleHeap {
    private int id;

    public Demo02SimpleHeap(int id) {
        this.id = id;
    }

    public void show() {

        System.out.println("My ID is " + id);
    }

    public static void main(String[] args) {
        Demo02SimpleHeap s1 = new Demo02SimpleHeap(1);
        Demo02SimpleHeap s2 = new Demo02SimpleHeap(2);
        s1.show();
        s2.show();
    }
}