package com.ysy.extthread;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyStack
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/11 11:35
 **/
public class MyStack {
    private List list = new ArrayList();

    synchronized public void push() {
        try {
            if (list.size() == 1) {
                this.wait();
            }
            list.add("anyString=" + Math.random());
            this.notify();
            System.out.println("push=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public String pop() {
        String returnValue = "";
        try {
            if (list.size() == 0) {
                System.out.println("pop操作中的：" + Thread.currentThread().getName() + "线程呈wait状态");
                this.wait();
            }
            returnValue = " " + list.get(0);
            list.remove(0);
            this.notify();
            System.out.println("pop=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

class P_1 {
    private MyStack myStack;

    public P_1(MyStack myStack) {
        super();
        this.myStack = myStack;
    }

    public void pushService() {
        myStack.push();
    }
}


class C_1 {
    private MyStack myStack;

    public C_1(MyStack myStack) {
        super();
        this.myStack = myStack;
    }

    public void popService() {
        System.out.println("pop=" + myStack.pop());
    }
}

class P_Thread extends Thread{
    private P_1 p_1;
    public P_Thread(P_1 p_1){
        super();
        this.p_1 = p_1;
    }

    @Override
    public void run(){
        while (true){
            p_1.pushService();
        }
    }
}

class C_Thread extends Thread{
    private C_1 c_1;
    public C_Thread(C_1 c_1){
        super();
        this.c_1 = c_1;
    }

    @Override
    public void run(){
        while (true){
            c_1.popService();
        }
    }
}


class P_CRun{
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        P_1 p_1 = new P_1(myStack);
        C_1 c_1 = new C_1(myStack);
        P_Thread p_thread = new P_Thread(p_1);
        C_Thread c_thread = new C_Thread(c_1);
        p_thread.start();
        c_thread.start();
    }
}

