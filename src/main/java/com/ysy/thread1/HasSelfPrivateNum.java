package com.ysy.thread1;

/**
 * @ClassName HasSelfPrivateNum
 * @Description 线程安全的，方法内部的的变量为线程安全的，因为方法内部的变量即局部变量保存在java虚拟机栈上
 *              而虚拟机栈是线程私有的。
 * @Author ysy
 * @Date 2020/6/9 15:01
 **/
public class HasSelfPrivateNum {
    //实例变量，存在于堆空间中，所有线程共享，存在安全问题
    int num = 0;
    //解决实例变量存在的安全问题
   synchronized public void addI(String username){
        try {
            //方法内的局部变量
            //int num = 0;
            if (username.equals("a")){
                num =100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            }else {
                num =200;
                System.out.println("b set over");
            }
            System.out.println(username + " num = " + num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ThreadA extends Thread{
    private HasSelfPrivateNum  numRef;
    public ThreadA(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run(){
        super.run();
        numRef.addI("a");
    }
}

class ThreadB extends Thread{
    private HasSelfPrivateNum numRef;
    public ThreadB(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run(){
        super.run();
        numRef.addI("b");
    }
}

class HasSelfRun{
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(numRef);
        threadA.start();
        ThreadB threadB = new ThreadB(numRef);
        threadB.start();
    }
}
