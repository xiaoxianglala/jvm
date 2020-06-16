package com.ysy.extthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T_02
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/16 12:20
 **/
public class T_02 {
    private  List<Integer> lists = new ArrayList<Integer>();

     public void add(){
        for (int i = 0; i < 10; i++) {
            lists.add(i);
            System.out.println("添加的数据为：" + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void size(){
        while (true){
            if (lists.size() == 5){

                System.out.println("已经有五个数了； " + lists.size());
                break;
            }
        }
    }
}

class Thread_02A extends Thread{
    private T_02 t_02;

    public Thread_02A(T_02 t_02){
        this.t_02 = t_02;
    }
    @Override
    public void run(){
        t_02.add();
    }
}

class Thread_02B extends Thread{
    private T_02 t_02;

    public Thread_02B(T_02 t_02){
        this.t_02 = t_02;
    }
    @Override
    public void run(){
        t_02.size();
    }
}

class T_02Run{
    public static void main(String[] args) {

        T_02 t_02 = new T_02();
        new Thread_02A(t_02).start();
        new Thread_02B(t_02).start();
    }
}
