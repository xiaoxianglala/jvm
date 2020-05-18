package com.ysy.gc;

/**
 * @ClassName MyTest1
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/18 12:32
 * -verbose:gc (输出详细的垃圾回收日志)
 * -Xms20M 堆容量最小值
 * -Xmx20M 堆容量最打值
 * -Xmn10M 新生代容量为10M
 * -XX:+PrintGCDetails 打印垃圾回收详细信息
 * -XX:SurvivorRatio=8 空间比例饿为1；8
 **/
public class MyTest1 {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[3 * size];
        byte[] myAlloc4 = new byte[3 * size];


        System.out.println("hello xiaoxiang");
    }
}
