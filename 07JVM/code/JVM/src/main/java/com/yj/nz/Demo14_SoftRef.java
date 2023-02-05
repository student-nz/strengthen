package cn.tx;

import java.lang.ref.SoftReference;

public class SoftRef {
    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return " [id=" + String.valueOf(id) + ", name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        User u = new User(1, "geym");
        //创建u的软引用
        SoftReference<User> userSoftRef = new SoftReference<User>(u);
        //让我们的User对象变成匿名对象
        u = null;
        //通过软引用来获得对象
        System.out.println(userSoftRef.get());
        //调用垃圾回收器 ，由于软引用的存在，对象没有被回收
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());
        //分配一块大的空间
        byte[] b = new byte[1024 * 935 * 7];
        System.gc();
        System.out.println(userSoftRef.get());
    }
}
