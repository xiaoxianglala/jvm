package com.ysy.thread1;

/**
 * @ClassName DealThread
 * @Description 线程死锁
 * @Author ysy
 * @Date 2020/6/10 15:51
 **/
public class DealThread implements Runnable {
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();
    public void setFlag(String username){
        this.username = username;
    }
    @Override
    public void run() {
        if (username.equals("a")){
            synchronized (lock1){
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("按lock1->lock2代码顺序执行了");
                }
            }
        }
        if (username.equals("b")){
            synchronized (lock2){
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("按lock2->lock1代码顺序执行了");
                }
            }
        }
    }
}

class DealRun{
    public static void main(String[] args) {
        try {
            DealThread dealThread1 = new DealThread();
            dealThread1.setFlag("a");
            Thread thread1 = new Thread(dealThread1);
            thread1.start();
            Thread.sleep(1000);
            dealThread1.setFlag("b");
            Thread thread2 = new Thread(dealThread1);
            thread2.start();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
