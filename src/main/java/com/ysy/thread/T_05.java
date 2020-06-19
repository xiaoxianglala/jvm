package com.ysy.thread;

import com.ysy.extthread.T;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T_05
 * @Description 两个线程交替打印
 * @Author ysy
 * @Date 2020/6/19 14:17
 **/
public class T_05 {
    private boolean flag = false;

    public void printNum() {
        for (int i = 0; i < 36; i++) {
            synchronized (this) {
                if (flag) {
                    try {
                        this.wait();
                        System.out.println("----");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i);
                flag = true;
                this.notify();
            }
        }
    }

    public void printStr() {
        for (int i = 0; i < 26; i++) {
            synchronized (this) {
                if (!flag) {
                    try {
                        this.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println((char) (i + 'a'));
                flag = false;
                this.notify();
            }
        }
    }

    public static void main(String[] args) {
        T_05 t_05 = new T_05();

        new Thread(() -> {
            t_05.printStr();
        }).start();
        new Thread(() -> {
            t_05.printNum();
        }).start();
    }
}
