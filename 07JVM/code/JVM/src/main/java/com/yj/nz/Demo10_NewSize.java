package cn.tx;

import java.io.IOException;

public class NewSizeDemo {
    public static void main(String[] args) throws IOException {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}