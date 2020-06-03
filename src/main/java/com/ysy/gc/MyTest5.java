package com.ysy.gc;

/**
 * @ClassName MyTest5
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/19 11:55
 *
 *-verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
 **/
public class MyTest5 {



    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] MyAlloc1 = new byte[size * 4];
        System.out.println("hello xiaoxiang1");

        byte[] MyAlloc2 = new byte[size * 4];
        System.out.println("hello xiaoxiang2");

        byte[] MyAlloc3 = new byte[size * 4];
        System.out.println("hello xiaoxiang3");

        byte[] MyAlloc4 = new byte[size * 4];
        System.out.println("hello xiaoxiang4");
    }

}
