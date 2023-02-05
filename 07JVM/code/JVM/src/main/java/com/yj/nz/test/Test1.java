package cn.tx.test;

public class Test1 {

    public static int add(){

        int i = 10;
        int j = 20;
        int z = i +j;
        return z;
    }

    public static void test(){
        int i = 0;
        i = i++;
        System.out.println(i);
    }

    public static void test2(){
        int c = 0;
        for (int i = 0; i < 100; i++) {
            c = c++;
        }
        System.out.println(c);
    }

    public static void test1(){
        int i = 0;
        i = ++i;
        System.out.println(i);
    }

    public static void main(String[] args) {
        test();
    }
}
