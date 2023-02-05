package cn.tx;

public class CanReliveObj {

    public static CanReliveObj obj;

    /**
     * finalize
     * @throws Throwable
     *
     * finalize只能被调用一次
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanRelive0bj finalize called");
        //对象的复活
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanRelive0bj";
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();
        obj = null;
        System.gc();

        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj是null");
        } else {
            System.out.println("obj可用");
            System.out.println("第2次gc");
            //继续把引用设置成null，让对象编程匿名对象
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

}
