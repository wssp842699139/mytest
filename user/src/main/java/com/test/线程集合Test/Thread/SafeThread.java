package com.test.线程集合Test.Thread;

public class SafeThread extends Thread {
    private static int count = 0;
    private String name;

    public SafeThread(String name){
        this.name = name;
    }

    @Override
    public void run() {

            for (int i = 0; i < 100; i++) {
                System.out.println(this.name);
                count();
            }

    }

    private  void  count() {
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        SafeThread threadSafe1 = new SafeThread("第一个线程");
        SafeThread threadSafe2 = new SafeThread("第二个线程");
        Thread thread1 = new Thread(threadSafe1);
        Thread thread2 = new Thread(threadSafe2);
        thread1.start();
        System.out.println("准备执行线程1join");
        thread1.join();
        thread2.start();
        System.out.println("准备执行线程2join");
        thread2.join();
        System.out.println(count);
    }
}
