package com.ysy.thread1;

/**
 * @ClassName ObjectService
 * @Description 使用同步代码块
 * @Author ysy
 * @Date 2020/6/9 17:26
 **/
public class ObjectService {
    public void serviceMethod(){
        try {
            synchronized (this){
                System.out.println("beging time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("end  end = " + System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ServiceThreadA extends Thread{
    private ObjectService objectService;
    public ServiceThreadA(ObjectService objectService){
        super();
        this.objectService = objectService;
    }

    @Override
    public void run(){
        objectService.serviceMethod();
    }
}

class ServiceThreadB extends Thread{
    private ObjectService objectService;
    public ServiceThreadB(ObjectService objectService){
        super();
        this.objectService = objectService;
    }

    @Override
    public void run(){
        objectService.serviceMethod();
    }
}

class ServiceRun{
    public static void main(String[] args) {
        ObjectService objectService = new ObjectService();
        ServiceThreadA serviceThreadA = new ServiceThreadA(objectService);
        serviceThreadA.setName("A");
        serviceThreadA.start();
        ServiceThreadB  serviceThreadB = new ServiceThreadB(objectService);
        serviceThreadB.setName("B");
        serviceThreadB.start();
    }
}