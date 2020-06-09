package com.ysy.thread1;

/**
 * @ClassName PublicVar
 * @Description 脏读
 * @Author ysy
 * @Date 2020/6/9 16:19
 **/
public class PublicVar {
    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username,String password){
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setVaule method thread name = " + Thread.currentThread().getName() + " username = "
            + username + " password = " + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //脏读出现的原因是由于改方法没有进行同步，所以可以在任意时刻进行调用。解决方法：synchronized
    synchronized public void getValue(){
        System.out.println("getVaule method thread name = "
        + Thread.currentThread().getName() + " username = " + username
        + " password = " + password);
    }
}

class ThreadVarA extends Thread{
    private PublicVar publicVar;
    public ThreadVarA(PublicVar publicVar){
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run(){
        super.run();
        publicVar.setValue("B","BB");
    }
}

class VarRun{
    public static void main(String[] args) {
        try {
            PublicVar publicVarRef = new PublicVar();
            ThreadVarA threadVarA = new ThreadVarA(publicVarRef);
            threadVarA.start();
            Thread.sleep(2000);
            publicVarRef.getValue();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}