package com.ysy.thread1;

/**
 * @ClassName MyThread0
 * @Description 线程的执行不是按照代码的顺序执行的，而是随机的执行
 * @Author ysy
 * @Date 2020/6/9 10:12
 **/
public class MyThread0 extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test0 {
    public static void main(String[] args) {
        try {
            MyThread0 myThread0 = new MyThread0();
            myThread0.setName("myThread");
            myThread0.start();
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
