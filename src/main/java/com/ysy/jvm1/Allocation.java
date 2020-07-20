package com.ysy.jvm1;

/**
 * @ClassName Allocation
 * @Description -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 jvm内存为20M,不动态扩展，新生代10M，eden区站新生代的8/10
 * -XX:PretenureSizeThreshold=3145728 设置当对象大于3M时直接分配在老年代中
 * @Author ysy
 * @Date 2020/7/14 11:37
 **/
public class Allocation {

    private static final int _1MB = 1024 * 1024;

    public static void testAllocation(){
        byte[] allocation1, allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        Allocation.testAllocation();
    }
}
