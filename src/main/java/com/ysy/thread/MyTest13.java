package com.ysy.thread;

import javafx.fxml.LoadException;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyTest13
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/8 17:24
 **/
public class MyTest13 {
    public boolean transfer(final Account from,final Account to,int amount) throws InterruptedException, LoadException {
        final Account[] accounts = new Account[]{from,to};
        Arrays.sort(accounts);

        if (accounts[0].monitor.tryLock(1, TimeUnit.SECONDS)){
            try {
                if (accounts[1].monitor.tryLock(1,TimeUnit.SECONDS)){
                   try {
                       if (from.withdraw(amount)){
                           to.deposit(amount);
                           return true;
                       }else {
                           return false;
                       }
                   }finally {
                       accounts[1].monitor.unlock();
                   }
                }
            }finally {
                accounts[0].monitor.unlock();
            }
        }
        throw new LoadException("Unable to acquire locks on the accounts");
    }

    public static void main(String[] args) throws InterruptedException {
        Account from = new Account(2000);
        Account to = new Account(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyTest13().transfer(from,to,100);
                } catch (InterruptedException e) {
                } catch (LoadException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyTest13().transfer(to,from,300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (LoadException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(2000);
        System.out.println("from " + from.getBalance());
        System.out.println("to " + to.getBalance());
    }
}

class Account implements Comparable<Account>{

    private int balance;
    public final Lock monitor = new ReentrantLock();

    public Account(final int initialBalance){
        this.balance = initialBalance;
    }
    public int getBalance(){
        return this.balance;
    }
    @Override
    public int compareTo(Account o) {
        return new Integer(hashCode()).compareTo(o.hashCode());
    }

    public void deposit(final int amount){
        monitor.lock();
        try {
            if (amount > 0) balance += amount;
        }finally {
            monitor.unlock();
        }
    }

    public boolean withdraw(final int amount){
        try {
            monitor.lock();
            if (amount > 0 && balance >= amount){
                balance -= amount;
                return true;
            }
            return false;
        }finally {
            monitor.unlock();
        }
    }
}
