package com.test.线程集合Test.Thread;

public class WaitAndNotify {
    //轮询标志位
    private static boolean stop = false;
    //监视器对应的对象
    private static Object monitor = new Object();
    //等待线程
    static class WaitThread implements Runnable{
        @Override
        public void run() {
            synchronized(monitor){
                //循环检测标志位是否变更
                while(!stop){
                    System.out.println("等待线程获取到锁");
                    try {
                        //标志位未变更，进行等待
                        System.out.println("等待线程在此等待");
                        monitor.wait();
                        System.out.println("等待线程获取到锁开始任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒后获取到对象的监视器之后执行的代码
                System.out.println("Thread "+Thread.currentThread().getName()+" is awakened at first time");
                stop = false;
            }
            //休眠1秒之后，线程角色转换为唤醒线程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //与上述代码相反的逻辑
            synchronized(monitor){
                while(stop){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                monitor.notify();
                stop = true;
                System.out.println("Thread "+ Thread.currentThread().getName()+" notifies the waitted thread at first time");
            }
        }
    }
    //通知线程
    static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (monitor){
                while(stop){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stop = true;
                monitor.notify();
                System.out.println("Thread "+ Thread.currentThread().getName()+" notifies the waitted thread at first time");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitor){
                while(!stop){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread "+Thread.currentThread().getName()+" is awakened at first time");
            }
        }
    }
    public static void main(String[] args){
        Thread waitThread = new Thread(new WaitThread());
        waitThread.setName("waitThread");
        Thread notifyThread = new Thread(new NotifyThread());
        notifyThread.setName("notifyThread");
        waitThread.start();
        notifyThread.start();
    }
}
