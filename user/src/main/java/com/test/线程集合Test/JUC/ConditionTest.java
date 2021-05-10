package com.test.线程集合Test.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/16 23:52
 * @package com.test
 */

public class ConditionTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditiona = lock.newCondition();
    private static Condition conditionb = lock.newCondition();

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t======== 进入");
            try {
                conditiona.await();
                System.out.println(Thread.currentThread().getName() + "\t======== 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "\t======== 释放锁");
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t======== 进入");
            try {
                conditionb.await();
                System.out.println(Thread.currentThread().getName() + "\t======== 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "\t======== 释放锁");
                lock.unlock();
            }
        }, "C").start();

        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(1000);
                conditiona.signalAll();
                conditionb.signalAll();
                System.out.println(Thread.currentThread().getName() + "\t============ 通知");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "\t======== 释放锁");
                lock.unlock();
            }
        }, "B").start();


    }


}
