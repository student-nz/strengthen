package cn.tx.test;

public class TestUser {

    private int count;

    public void test(int a) {

        count = count + a;

    }

    public User initUser(int age, String name) {

        User user = new User();

        user.setAge(age);

        user.setName(name);

        return user;

    }

    public static void testAdd(){
        int i = 0;
        i = i++;
        System.out.println(i);
    }

    public static void main(String[] args) {
        new TestUser().test(1);
    }
}


class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}