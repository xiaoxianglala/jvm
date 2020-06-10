package com.ysy.thread1;

/**
 * @ClassName Service
 * @Description synchronized加在静态方法上是给类class上锁，对类的所有对象实例起作用。而加在非静态方法上是给对象上锁
 * @Author ysy
 * @Date 2020/6/10 15:13
 **/
public class Service {
    synchronized public static void prinrtA() {
        try {
            System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void prinrtB() {

        System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入printB");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开printB");
    }

}

class PrintThreadA extends Thread{
    @Override
    public void run(){
        Service.prinrtA();
    }
}

class PrintThreadB extends Thread{
    @Override
    public void run(){
        Service.prinrtB();
    }
}

class PrintRun{
    public static void main(String[] args) {
        PrintThreadA printThreadA = new PrintThreadA();
        printThreadA.setName("A");
        printThreadA.start();
        PrintThreadB printThreadB = new PrintThreadB();
        printThreadB.setName("B");
        printThreadB.start();
    }
}