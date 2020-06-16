package com.ysy.extthread;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName T
 * @Description 可重入锁-继承
 * @Author ysy
 * @Date 2020/6/16 11:05
 **/
public class T {
    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("m end");
    }
}

class TT extends T{

    @Override
    synchronized void m(){
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}

class TRun{
    public static void main(String[] args) {
        new TT().m();
    }
}
