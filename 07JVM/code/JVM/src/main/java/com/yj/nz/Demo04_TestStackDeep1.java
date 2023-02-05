package com.yj.nz;

public class Demo04TestStackDeep1 {

    public void localvar1(){
        int a = 0;
        System.out.println(a);
        int b= 0;
    }

    public void localvar2(){
        {
            int a = 0;
            System.out.println(a);
        }
        int b= 0;
    }


    public static void main(String args[]) {
        Demo04TestStackDeep1 t = new Demo04TestStackDeep1();
        t.localvar1();
        t.localvar2();
    }
}