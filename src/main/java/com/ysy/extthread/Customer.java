package com.ysy.extthread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Customer
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/15 17:44
 **/
public class Customer implements Runnable {
    private CountDownLatch latch;
    private String name;

    public Customer(CountDownLatch latch,String name){
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");
            Random random = new Random();

            System.out.println(format.format(new Date()) + name + "出发去饭店");
            Thread.sleep((long) (random.nextDouble() * 3000) + 1000);
            System.out.println(format.format(new Date()) + name + "到达饭店");
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Waitress implements Runnable{

    private CountDownLatch latch;
    private String name;

    public Waitress(CountDownLatch latch,String name){
        this.latch = latch;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");
            System.out.println(format.format(new Date()) + name + "等待顾客");
            //一直等待到countLatch为0，才开始去执行
            //latch.await();
            //等待1秒之后，不再等待就去执行了
            latch.await(1, TimeUnit.SECONDS);
            System.out.println(format.format(new Date()) + name + "开始上菜");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class T_0Run{
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        List<Thread> threads = new ArrayList<Thread>(3);
        threads.add(new Thread(new Customer(latch,"李四")));
        threads.add(new Thread(new Customer(latch,"王五")));
        threads.add(new Thread(new Customer(latch,"张三")));

        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(1000);
        new Thread(new Waitress(latch,"大红")).start();

        //其实就是为了让主程序最后执行，join方法就是等调用其线程执行之后，再执行
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("我是大佬");
    }
}