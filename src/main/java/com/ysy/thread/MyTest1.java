package com.ysy.thread;

/**
 * @ClassName MyTest1
 * @Description 继承Tread类
 * @Author ysy
 * @Date 2020/6/3 16:14
 **/
public class MyTest1 extends Thread{

    @Override
    public void run(){
        System.out.println("MyTest Thread is running!!!!");
    }

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();
        myTest1.start();

        //匿名子类
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread is running");
            }
        };
        thread.start();
    }
}
