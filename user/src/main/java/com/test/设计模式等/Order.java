package com.test.设计模式等;

import java.util.Arrays;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/18 16:42
 * @package com.test.excel
 */
public class Order {
    //排序算法测试
    public static void main(String[] args) {
        int[] array = {10, 45, 34, 27, 6, 9, 33, 56, 78, 35, 90, 107};
        //冒泡排序
        System.out.println(Arrays.toString(maopao(array)));
        System.out.println(Arrays.toString(selection(array)));
    }

    //冒泡排序算法
    public static int[] maopao(int[] array) {
        //数组的长度
        // int length = array.length;
        //外层遍历  总共需要遍历length-1次
        //内层遍历 第j个数需要与length-1-j个数进行比较
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - j; j++) {
                if (array[j] > array[j + 1]) {
                    int a = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = a;
                }
            }
        }
        return array;
    }

    //选择排序算法
    public static int[] selection(int[] array) {
        //外层也是经过了length-1次的遍历
        //内层遍历的时候找出未排序的所有的最小的数值
        for (int i = 0; i < array.length; i++) {
            //第j个索引的元素 会去比较 length -1 -j 次
            int minIndex = i;
            for(int j=i;j<array.length-1;j++){
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            //更换最小的元素到i位子
            int a = array[minIndex];
            array[minIndex] = array[i];
            array[i] = a;
        }
        return array;
    }
}
