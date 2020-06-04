package com.ysy.thread;

/**
 * @ClassName MyTest5
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/3 16:45
 **/
public class MyTest5 {
    private static boolean done;

    public static void main(String[] args) throws InterruptedException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (!done) { i   ++;}
                        System.out.println("Done!");
                    }
                }
        ).start();

        //获得操作系统信息
        System.out.println("OS: " + System.getProperty("os.name"));
        Thread.sleep(2000);
        done = true;
        System.out.println("flag done set to true");
        //获得处理器核心数
        //线程数=处理器核心数/(1-阻塞系数)  阻塞系数的取值范围为0到1，计算密集型任务阻塞系统为0，IO密集型任务阻塞系数接近于1
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
