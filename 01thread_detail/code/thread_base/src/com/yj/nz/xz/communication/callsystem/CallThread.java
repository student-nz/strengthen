package com.yj.nz.xz.communication.callsystem;

public class CallThread extends Thread{
    @Override
    public void run() {
        // 不断的打入电话
        while (true){
            CallSystem.call();
        }
    }
}
