package com.ysy.extthread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName T_03
 * @Description 读写锁（用于读多写少，读读共享，读写互斥，写读互斥，写写互斥）
 * @Author ysy
 * @Date 2020/6/19 10:20
 **/
public class T_03 {
    private Lock lock = new ReentrantLock();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void read(){
       /* readWriteLock.readLock().lock();*/
        lock.lock();
        try {
            System.out.println("read!!!!!");
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            /*readWriteLock.readLock().unlock();*/
        }
    }

    public void write(){
        /*readWriteLock.writeLock().lock();*/
        lock.lock();
        try {
            System.out.println("write");
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
           /* readWriteLock.writeLock().unlock();*/
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T_03 t_03 = new T_03();

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    t_03.read();
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    t_03.write();
                }
            }).start();
        }
    }
}
