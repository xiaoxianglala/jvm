package com.ysy.thread;

/**
 * @ClassName MyTest3
 * @Description 设置线程名
 * @Author ysy
 * @Date 2020/6/3 16:31
 **/
public class MyTest3 implements Runnable {
    @Override
    public void run() {
        System.out.println("MyTest runnable is running");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyTest3(),"Thread-1");
        thread.run();
        System.out.println("current thread name is " + thread.getName());
    }
}
