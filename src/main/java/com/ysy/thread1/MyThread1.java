package com.ysy.thread1;

/**
 * @ClassName MyThread1
 * @Description 线程的执行不是按照start的顺序去执行的
 * @Author ysy
 * @Date 2020/6/9 10:21
 **/
public class MyThread1 extends Thread {
    private int i;
    public MyThread1(int i){
        super();
        this.i = i;
    }

    @Override
    public void run(){
        System.out.println(i);
    }
}

class Test1{
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1(1);
        MyThread1 myThread2 = new MyThread1(2);
        MyThread1 myThread3 = new MyThread1(3);
        MyThread1 myThread4 = new MyThread1(4);
        MyThread1 myThread5 = new MyThread1(5);
        MyThread1 myThread6 = new MyThread1(6);
        MyThread1 myThread7 = new MyThread1(7);

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        myThread6.start();
        myThread7.start();
    }
}
