package com.ysy.jvm;

/**
 * @ClassName Mytest2
 * @Description TODO
 * @Author ysy
 * @Date 2020/5/15 11:32
 * 演示虚拟机栈溢出  (设置栈大小：-Xss100k )
 * 工具jconsole也可以监视进程
 **/
public class Mytest2 {
    private int length;

    public int getLength() {
        return length;
    }

    public void test(){
        this.length ++;
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        test();
    }

    public static void main(String[] args) {
        Mytest2 mytest2 = new Mytest2();
        try {
            mytest2.test();
        }catch (Throwable e){
            System.out.println(mytest2.getLength());
            e.printStackTrace();
        }
    }
}
