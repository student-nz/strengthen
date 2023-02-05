package com.yj.nz.xz.safequestion.ticket.test02.synchronizeds.test03;

public class Ticket {
    public static void main(String[] args) {
        SynchronizedStaticMethod mr = new SynchronizedStaticMethod();

        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        t1.setName("窗口一");
        t2.setName("窗口二");

        t1.start();
        t2.start();
    }
}
