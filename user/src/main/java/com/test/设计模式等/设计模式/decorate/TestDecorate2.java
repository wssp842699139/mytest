package com.test.设计模式等.设计模式.decorate;

import lombok.Data;

import java.io.*;

@Data
public class TestDecorate2 implements TestDecorate {

    private TestDecorate testDecorate;

    public TestDecorate2(TestDecorate testDecorate) {
        this.testDecorate = testDecorate;
    }

    @Override
    public void test() {
        System.out.println("方法前置增强");
        testDecorate.test();
        System.out.println("方法后置增强");
        //InputStream inputStream = new InputStream();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("aa"));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            int read = bufferedInputStream.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
