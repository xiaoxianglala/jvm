package com.ysy.thread;

/**
 * @ClassName MyTest2
 * @Description 实现Runnable接口
 * @Author ysy
 * @Date 2020/6/3 16:24
 **/
public class MyTest2 implements Runnable {

    @Override
    public void run() {
        System.out.println("MyTest2 Runnable is running");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyTest2());
        thread.run();

        //匿名类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable is running");
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.run();
    }
}
