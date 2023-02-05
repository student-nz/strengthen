package com.yj.nz;

import java.nio.ByteBuffer;

public class Demo023_DirectBufferOOM {
    public static void main(String args[]) {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer.allocateDirect(1024 * 1024*6);
            System.out.println(i);
            //System.gc();

        }
    }
}