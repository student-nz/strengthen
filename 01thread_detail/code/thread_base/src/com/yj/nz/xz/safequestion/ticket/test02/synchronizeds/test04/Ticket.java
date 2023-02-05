package com.yj.nz.xz.safequestion.ticket.test02.synchronizeds.test04;

/*
    卖票案例
 */
public class Ticket {
    public static void main(String[] args) {
        NotSynchronizedStaticMethod st = new NotSynchronizedStaticMethod();

        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
