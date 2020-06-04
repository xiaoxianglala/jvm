package com.ysy.thread;

/**
 * @ClassName MyTest4
 * @Description
 * @Author ysy
 * @Date 2020/6/3 16:39
 **/
public class MyTest4 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("Thread-" + i){
                public void run(){
                    System.out.println(getName() + " running");
                }
            }.start();
        }
    }
}
