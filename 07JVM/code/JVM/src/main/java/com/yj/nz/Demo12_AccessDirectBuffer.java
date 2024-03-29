package cn.tx;

import java.nio.ByteBuffer;

public class AccessDirectBuffer {
    public void directAccess() {
        long starttime = System.currentTimeMillis();
        //分配直接内存一个缓冲区
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }

        long endtime = System.currentTimeMillis();
        System.out.println("testDirectWrite:" + (endtime - starttime));
    }

    public void bufferAccess() {
        long starttime = System.currentTimeMillis();
        //分配一个堆中的缓冲区
        ByteBuffer b = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("testBufferwrite:" + (endtime - starttime));
    }

    public static void main(String[] args) {
        AccessDirectBuffer alloc = new AccessDirectBuffer();
        alloc.bufferAccess();
        alloc.directAccess();

        alloc.bufferAccess();
        alloc.directAccess();
    }
}