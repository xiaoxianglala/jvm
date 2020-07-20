package com.ysy.thread1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadLocalUsage02
 * @Description TODO
 * @Author ysy
 * @Date 2020/7/20 11:58
 **/
public class ThreadLocalUsage02 {
    public static ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10);

    static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String date(int seconds){
        Date date = new Date(1000 * seconds);

        return DATE_FORMAT.format(date);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            THREAD_POOL.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage02().date(finalI);
                    System.out.println(date);
                }
            });
        }
        THREAD_POOL.shutdown();
    }
}
