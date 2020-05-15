package com.ysy.jvm;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyTest1
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/15 10:22
 * 堆：jvm管理的最大的内存空间，与堆相关的一个重要概念是垃圾收集器，现代几乎所有的垃圾收集器采用的都是分代收集算法
 * 线程私有：虚拟机栈、程序计算器、本地方法栈
 * 线程共享：堆、方法区、
 *
 * -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError (最大堆和最小堆分配为5m，相同不会自动扩展，打应错误信息)
 * 直接在后台输入jvisualvm，打开java visualvm工具
 **/
public class MyTest1 {
    public static void main(String[] args) {
        List<MyTest1> list = new ArrayList<>();
        for (; ;){
            list.add(new MyTest1());
            System.gc();
        }
    }
}
