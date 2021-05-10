package com.test.线程集合Test.ThreadLocal;


public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> num = new ThreadLocal<Integer>();
        ThreadLocal<String> string = new ThreadLocal<String>();
        ThreadLocal a = new ThreadLocal<>();
        // 创建一号线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在一号线程中将ThreadLocal变量设置为1
                num.set(1);
                string.set("a");
                a.set(123);
                System.out.println("一号线程中ThreadLocal变量中保存的值为：" + num.get());
                System.out.println("一号线程中ThreadLocal变量中保存的值为：" + string.get());
                System.out.println("一号线程中ThreadLocal变量中保存的值为：" + a.get());
            }
        }).start();

        // 创建二号线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                num.set(2);
                string.set("b");
                a.set("abc");
                System.out.println("二号线程中ThreadLocal变量中保存的值为：" + num.get());
                System.out.println("一号线程中ThreadLocal变量中保存的值为：" + string.get());
                System.out.println("一号线程中ThreadLocal变量中保存的值为：" + a.get());
            }
        }).start();

        //为了让一二号线程执行完毕，让主线程睡500ms
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("主线程中ThreadLocal变量中保存的值：" + num.get());
    }
}

