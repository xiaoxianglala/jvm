package com.ysy.reentrantlock;

import com.ysy.extthread.MyStack;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/11 15:44
 **/
public class MyService {
    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
        }
        lock.unlock();
    }
}

class MyThread extends Thread{
    private MyService myService;
    public MyThread(MyService myService){
        super();
        this.myService = myService;
    }
    @Override
    public void run(){
        myService.testMethod();
    }
}

class Run{
    public static void main(String[] args) {
        MyService service = new MyService();
        MyThread a1 = new MyThread(service);
        MyThread a2 = new MyThread(service);
        MyThread a3 = new MyThread(service);
        MyThread a4 = new MyThread(service);
        MyThread a5 = new MyThread(service);
        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
    }
}