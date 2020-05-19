package com.ysy.gc;

/**
 * @ClassName MyTest3
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/19 10:29
 * -XX:MaxTenuringThreshold=5 设置晋升到老年代最大的阈值，当对象在新生代的年龄大于5时就会一定进入到老年代，但是小于也可能进入
 * 改参数的默认值为15,（在jvm中改值由4个bit来表示，为111：即十进制的15）
 * -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5 -XX:+PrintTenuringDistribution
 **/
public class MyTest3 {

    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[3 * size];
        byte[] myAlloc4 = new byte[3 * size];

        System.out.println("hello xiaoxiang");


    }
}
