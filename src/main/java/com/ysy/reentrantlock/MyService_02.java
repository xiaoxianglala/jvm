package com.ysy.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService_02
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/11 16:36
 **/
public class MyService_02 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void await(){
        try {
            lock.lock();
            System.out.println(" await时间为 " + System.currentTimeMillis());
            condition.await();
            System.out.println("end");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signal(){
        try {
            lock.lock();
            System.out.println("signal时间为★" + System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}

class Thread_02A extends Thread{
    private MyService_02 service_02;
    public Thread_02A(MyService_02 service_02){
        super();
        this.service_02 = service_02;
    }
    @Override
    public void run(){
        service_02.await();
    }
}

class Run_02{
    public static void main(String[] args) throws InterruptedException {
        MyService_02 service_02 = new MyService_02();
        Thread_02A thread_02A = new Thread_02A(service_02);
        thread_02A.start();
        Thread.sleep(2000);
        service_02.signal();
    }
}
