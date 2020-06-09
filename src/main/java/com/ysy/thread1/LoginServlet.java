package com.ysy.thread1;

/**
 * @ClassName LoginServlet
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/9 11:13
 **/
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;
    synchronized public static void doPost(String username,String password){
        try {
            usernameRef = username;
            if (username.equals("a")){
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username = " + usernameRef + " password = " + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class AlLogin extends Thread{
    @Override
    public void run(){
        LoginServlet.doPost("a","aa");
    }
}

class BLogin extends  Thread{
    @Override
    public void run(){
        LoginServlet.doPost("b","bb");
    }
}

class LoginRun{
    public static void main(String[] args) {
        AlLogin a = new AlLogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
