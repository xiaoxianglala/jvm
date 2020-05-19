package com.ysy.gc;

/**
 * @ClassName MyTest4
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/19 10:48
 * -verbose:gc -Xms20M -Xmx50M -XX:TargetSurvivorRatio=60 -XX:+PrintTenuringDistribution -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseParNewGC -XX:MaxTenuringThreshold=3
 **/
public class MyTest4 {

    public static void main(String[] args)throws InterruptedException {
        byte[] byte1 = new byte[512 * 1024];
        byte[] byte2 = new byte[512 * 1024];

        myGc();
        Thread.sleep(1000);
        System.out.println("hello xiaoxiang1");

        myGc();
        Thread.sleep(1000);
        System.out.println("hello xiaoxiang2");

        myGc();
        Thread.sleep(1000);
        System.out.println("hello xiaoxiang3");

        myGc();
        Thread.sleep(1000);
        System.out.println("hello xiaoxiang4");

        byte[] byte3 = new byte[1024 * 1024];
        byte[] byte4 = new byte[1024 * 1024];
        byte[] byte5 = new byte[1024 * 1024];

        myGc();
        Thread.sleep(1000);
        System.out.println("hello xiaoxiang5");

        myGc();
        Thread.sleep(1000);
        System.out.println("hello xiaoxiang6");

        System.out.println("hello xiaoxiang");

    }

    private static void myGc(){
        for (int i =0; i < 40; i++){
            byte[] byteArray = new byte[1024 *1024];
        }
    }
}
