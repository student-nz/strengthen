package cn.tx;

import java.util.HashMap;
import java.util.Map;

public class PretenureSizeThreshold {
    public static final int _1K = 1024;

    public static void main(String args[]) {
        Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
            map.put(i, b);
        }
    }
}