package com.test.demo;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.test.Utils.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/6 19:45
 * @package com.test.demo
 */
public class Test2 {

    public static void main(String[] args) {
        long time = new Date().getTime();
        long l = System.currentTimeMillis();
        System.out.println(time);
        System.out.println(l);
    }

}
