package com.ysy.thread1;

/**
 * @ClassName MyObject
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/9 15:44
 **/
public class MyObject {

    synchronized public void methodA(){
        try {
            System.out.println("beging methodA threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ThreadA2 extends Thread{
    private MyObject object;
    public ThreadA2(MyObject object){
        super();
        this.object = object;
    }

    @Override
    public void run(){
        super.run();
        object.methodA();
    }
}

class ThreadB2 extends Thread{
    private MyObject object;
    public ThreadB2(MyObject object){
        super();
        this.object = object;
    }

    @Override
    public void run(){
        super.run();
        object.methodA();
    }
}

class ObjectRun{
    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA2 threadA2 = new ThreadA2(object);
        threadA2.setName("A");
        ThreadB2 threadB2 = new ThreadB2(object);
        threadB2.setName("B");
        threadA2.start();
        threadB2.start();
    }
}