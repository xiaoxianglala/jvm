package com.ysy.gc;

/**
 * @ClassName MyTest2
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/19 10:08
 * -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=4194304
 *
 *-XX:PretenureSizeThreshold=4194304 当新创建的对象比改阈值大则不会在新生代创建
 * -XX:+UserSeriaGC 使用SeriaGC该垃圾回收器
 * java -XX:+PrintCommandLineFlags -version 在命令行输入，打印出jdk版本号及jvm默认信息
 **/
public class MyTest2 {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc = new byte[5 * size];

        try {
            Thread.sleep(1000000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
