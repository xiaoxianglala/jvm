package com.ysy.thread1;

/**
 * @ClassName MyThread4
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/9 11:04
 **/
public class MyThread4 extends Thread {
    private int count = 5;
    //加同步锁解决安全问题，synchronized可以对任意对象以及方法加锁，而加锁的这段代码称为“互斥区”或“临界区”
    //如果一个线程要执行同步方法里面的代码时，就会尝试的去获得锁，如果获得就去执行，如果获取不了，就是一直去尝试获取，直到拿到为止
    @Override
    synchronized public void run(){
       super.run();
       count--;
        System.out.println("由 " + Thread.currentThread().getName() + " 计算，count = " + count);
    }
}

class Run4{
    public static void main(String[] args) {
        MyThread4 thread4 = new MyThread4();
        Thread a = new Thread(thread4,"A");
        Thread b = new Thread(thread4,"B");
        Thread c = new Thread(thread4,"C");
        Thread d = new Thread(thread4,"D");
        Thread e = new Thread(thread4,"D");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
