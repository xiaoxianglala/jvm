package com.ysy.extthread;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyList
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/10 17:33
 **/
public class MyList {
    private static List list = new ArrayList();

    public static void add() {
        list.add("xiaoxiang");
    }

    public static int size() {
        return list.size();
    }
}

class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                if (MyList.size() != 5) {
                    System.out.println("wait begin " + System.currentTimeMillis());
                    lock.wait();
                }
                System.out.println("wait end " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    MyList.add();
                    if (MyList.size() == 5) {
                        lock.notify();
                        System.out.println("已经发出通知");
                    }
                    System.out.println("添加了 " + (i + 1) + " 个元素!");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run1 {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            ThreadA threadA = new ThreadA(lock);
            threadA.start();
            ThreadA.sleep(50);
            ThreadB threadB = new ThreadB(lock);
            threadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}