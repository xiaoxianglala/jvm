package com.ysy.thread1;

/**
 * @ClassName HasSelfPrivateNum1
 * @Description 多个对象多个锁
 * @Author ysy
 * @Date 2020/6/9 15:23
 **/
public class HasSelfPrivateNum1 {
    private int num = 0;
    synchronized public void addI(String username){
        try {
            if (username.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            }else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num = " + num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ThreadA1 extends Thread{
    private HasSelfPrivateNum1 numRef;
    public ThreadA1(HasSelfPrivateNum1 numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run(){
        super.run();
        numRef.addI("a");
    }
}

class ThreadB1 extends Thread{
    private HasSelfPrivateNum1 numRef;
    public ThreadB1(HasSelfPrivateNum1 numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run(){
        super.run();
        numRef.addI("b");
    }
}


class HasSelfRun1{
    public static void main(String[] args) {
        //多个对象多个锁，打印结果为异步的，各个线程拿各自对象的锁
        HasSelfPrivateNum1 numRef1A = new HasSelfPrivateNum1();
        HasSelfPrivateNum1 numRef1B = new HasSelfPrivateNum1();
        ThreadA1 threadA1 = new ThreadA1(numRef1A);
        threadA1.start();
        ThreadB1 threadB1 = new ThreadB1(numRef1B);
        threadB1.start();
    }
}