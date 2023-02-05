package cn.tx.gc;

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {
        System.out.println("=====GCDemo,Hello====");
        try {
            String str = "GCDemo";
            while (true){
                str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
                str.intern();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
