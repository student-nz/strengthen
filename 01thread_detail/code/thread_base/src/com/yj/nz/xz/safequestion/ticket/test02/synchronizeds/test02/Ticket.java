package com.yj.nz.xz.safequestion.ticket.test02.synchronizeds.test02;

public class Ticket {
    public static void main(String[] args) {
        SynchronizedCode t1 = new SynchronizedCode();
        SynchronizedCode t2 = new SynchronizedCode();

        t1.setName("窗口一");
        t2.setName("窗口二");

        t1.start();
        t2.start();
    }
}
