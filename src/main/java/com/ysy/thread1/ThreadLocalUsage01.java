package com.ysy.thread1;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ThreadLocalUsage01
 * @Description TODO
 * @Author ysy
 * @Date 2020/7/20 11:45
 **/
public class ThreadLocalUsage01 {

    private String date(int seconds){
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage01().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage01().date(1000);
                System.out.println(date);
            }
        }).start();
    }
}
