package com.ysy.jvm;

/**
 * @ClassName Mytest5
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/15 16:28
 * jcmd（从jdk1.7开始加入）
 * 1、可以查看jvm的启动参数
 * 2、jcmd pid help列出当前进程可以执行的操作
 * jps查看java进程号
 * jstack 获得线程相关的堆栈信息
 * jmc：java mission control
 * jfr：java飞行记录器
 * jhat：对堆存储进分析
 *
 **/
public class Mytest5 {
    public static void main(String[] args) {
        for (; ;){
            System.out.println("hello xiaoxiang");
        }
    }
}
