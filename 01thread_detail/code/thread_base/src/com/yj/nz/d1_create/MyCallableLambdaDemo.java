package com.yj.nz.d1_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName:MyCallableLambdaDemo
 * Package:PACKAGE_NAME
 * Description:描述
 *
 * @Date:2022/7/15 15:19
 * @Author:NieZheng
 * @Version:1.0
 */
public class MyCallableLambdaDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 开始运行...");
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int total = 0;
                for (int i = 0; i < 100; ++ i) {
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("任务取消了");
                        return total;
                    }
                    total += i;
                    System.out.println(Thread.currentThread().getName() + " total = " + total);
                }
                return total;
            }
        });

        Thread th = new Thread(task);
        th.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        task.cancel(true); // 取消任务

        int num = task.get(); // 获取线程运行完成后的值
        System.out.println(num);
    }
}
