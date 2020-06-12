package com.ysy.reentrantlock;

/**
 * @ClassName MyObject
 * @Description 饿汉模式-存在多线程的安全问题，当多线程去调用饿汉模式产生对象时，可能会获得不同的对象，这就和单例模式违背了
 *              解决：1）synchronized进行同步但是效率很低；2）使用部分代码加锁
 * @Author ysy
 * @Date 2020/6/11 18:18
 **/
public class MyObject {
    private static MyObject myObject;

    private MyObject() {
    }

    /*synchronized public static MyObject getInstance() {
        try {
            if (null != myObject) {

            } else {
                Thread.sleep(3000);
                myObject = new MyObject();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        public static MyObject getInstance() {
            try {
                if (null != myObject) {

                } else {
                    Thread.sleep(3000);
                    //对部分代码加锁，不进行判断的话会产生安全问题
                    synchronized (MyObject.class){
                        //解决安全问题，进行双检查
                        if (myObject == null){
                            myObject = new MyObject();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return myObject;
    }
}

class Thread_03 extends Thread{
    @Override
    public void run(){
        System.out.println(MyObject.getInstance().hashCode());
    }
}

class Run_03{
    public static void main(String[] args) {
        Thread_03 a = new Thread_03();
        Thread_03 b = new Thread_03();
        Thread_03 c = new Thread_03();
        a.start();
        b.start();
        c.start();
    }
}
