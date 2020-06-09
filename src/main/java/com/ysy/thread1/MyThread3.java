package com.ysy.thread1;

/**
 * @ClassName MyThread3
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/9 10:53
 **/
public class MyThread3 extends Thread {
    int count = 5;

    @Override
    public void run(){
        super.run();
        //count--分为三步：（1）取得原有的count值；（2）计算count-1；（3）对count再赋值；这三个步骤如果有多个线程访问一定会出现非线程安全问题
        count--;
        System.out.println("由 " + Thread.currentThread().getName() + " 计算，count = " + count);
    }
}
class Run3{
    public static void main(String[] args) {
        MyThread3 thread3 = new MyThread3();
        Thread a = new Thread(thread3,"A");
        Thread b = new Thread(thread3,"B");
        Thread c = new Thread(thread3,"C");
        Thread d = new Thread(thread3,"D");
        Thread e = new Thread(thread3,"D");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}