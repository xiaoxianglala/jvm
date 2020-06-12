package com.ysy.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService_01
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/11 15:56
 **/
public class MyService_01 {
    private Lock lock = new ReentrantLock();

    public void methodA(){
        try {
            lock.lock();
            System.out.println("methodA beging ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("methodA end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void methodB(){
        try {
            lock.lock();
            System.out.println("methodB beging ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("methodB end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class Thread_01A extends Thread{
    private MyService_01 service_01;
    public Thread_01A(MyService_01 service_01){
        super();
        this.service_01 = service_01;
    }

    @Override
    public void run(){
        service_01.methodA();
    }
}

class Thread_01AA extends Thread{
    private MyService_01 service_01;
    public Thread_01AA(MyService_01 service_01){
        super();
        this.service_01 = service_01;
    }

    @Override
    public void run(){
        service_01.methodA();
    }
}

class Thread_01B extends Thread{
    private MyService_01 service_01;
    public Thread_01B(MyService_01 service_01){
        super();
        this.service_01 = service_01;
    }

    @Override
    public void run(){
        service_01.methodB();
    }
}

class Thread_01BB extends Thread{
    private MyService_01 service_01;
    public Thread_01BB(MyService_01 service_01){
        super();
        this.service_01 = service_01;
    }

    @Override
    public void run(){
        service_01.methodB();
    }
}

class Run_01{
    public static void main(String[] args) {
        MyService_01 service_01 = new MyService_01();
        Thread_01A a = new Thread_01A(service_01);
        a.setName("A");
        a.start();
        Thread_01AA aa = new Thread_01AA(service_01);
        aa.setName("AA");
        aa.start();
        Thread_01B b = new Thread_01B(service_01);
        b.setName("B");
        b.start();
        Thread_01BB bb = new Thread_01BB(service_01);
        bb.setName("BB");
        bb.start();
    }
}