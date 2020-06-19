package com.ysy.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName T_04
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/19 11:13
 **/
public class T_04 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5){//如果等于5，停止改线程
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //唤醒t线程
        LockSupport.unpark(t);
    }
}
