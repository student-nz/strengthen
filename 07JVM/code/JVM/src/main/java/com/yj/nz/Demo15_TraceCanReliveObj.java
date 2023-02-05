package com.yj.nz;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class Demo15_TraceCanReliveObj {

    public static Demo15_TraceCanReliveObj obj;
    //定义引用队列
    static ReferenceQueue<Demo15_TraceCanReliveObj> phantomQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<Demo15_TraceCanReliveObj> objt = null;
                    try {
                        //从引用队列中移除元素并接收
                        objt = (PhantomReference<Demo15_TraceCanReliveObj>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //能接收到说明垃圾回收器回收了对象
                    if (objt != null) {
                        System.out.println("Demo15_TraceCanReliveObj is delelte by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanRelive0bj finalize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanRelive0bj";
    }

    public static void main(String[] args) throws InterruptedException {
        //创建并且启动线程
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        //实例化引用队列
        phantomQueue = new ReferenceQueue<Demo15_TraceCanReliveObj>();
        //创建强引用对象
        obj = new Demo15_TraceCanReliveObj();
        //创建虚引用对象，虚引用对象, 但是还没有进入到队列
        PhantomReference<Demo15_TraceCanReliveObj> phantomRef = new PhantomReference<Demo15_TraceCanReliveObj>(obj, phantomQueue);
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj是null");
        } else {
            System.out.println("obj可用");
        }
        System.out.println("第2次gc");
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj是null");
        } else {
            System.out.println("obj可用");
        }
    }
}
