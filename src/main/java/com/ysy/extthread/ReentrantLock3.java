package com.ysy.extthread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLock3
 * @Description 公平锁：等待时间长的线程先执行
 *              竞争锁：多个线程一起竞争一个锁
 *              竞争锁相对效率高
 * @Author ysy
 * @Date 2020/6/17 10:58
 **/
public class ReentrantLock3 extends Thread {
    private static ReentrantLock lock = new ReentrantLock(true); //参数为true表示为公平锁，请对比输出结果

    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        ReentrantLock3 rl=new ReentrantLock3();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        th1.start();
        th2.start();
    }
}
