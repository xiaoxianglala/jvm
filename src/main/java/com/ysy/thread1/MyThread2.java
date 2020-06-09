package com.ysy.thread1;

/**
 * @ClassName MyThread2
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/9 10:43
 **/
public class MyThread2 extends Thread {
    private int count = 5;
    public MyThread2(String name){
        super();
        //设置线程名
        this.setName(name);
    }
    @Override
    public void run(){
        super.run();
        while (count > 0){
            count--;
            System.out.println("由 " + Thread.currentThread().getName() + " 计算，count = " + count);
        }
    }
}

class Run1{
    public static void main(String[] args) {
        MyThread2 a = new MyThread2("A");
        MyThread2 b = new MyThread2("B");
        MyThread2 c = new MyThread2("C");
        a.start();
        b.start();
        c.start();
    }
}
