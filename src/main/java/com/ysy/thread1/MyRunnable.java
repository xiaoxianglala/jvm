package com.ysy.thread1;

/**
 * @ClassName MyRunnable
 * @Description 实现runnable接口开启线程，当一个类已经继承了领一个类的时候，就需要去实现runnable接口
 *              java是单继承的
 * @Author ysy
 * @Date 2020/6/9 10:30
 **/
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("运行中。。。。");
    }
}


class Run{
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println("运行结束！");
    }
}
