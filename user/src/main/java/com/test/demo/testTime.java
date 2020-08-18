package com.test.demo;


import java.util.*;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/6/15 15:48
 * @package com.test.demo
 */
public class testTime {

    public static void main(String[] args) {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        Map<String, Object> map4 = new HashMap<String, Object>();
        map.put("id","8"); map.put("a","fdf");
        map2.put("id","4");map2.put("a","fdf");
        map3.put("id","7");map3.put("a","fdf");
        map4.put("id","9");map4.put("a","fdf");
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        Collections.sort(list,new Comparator<Map<String, Object>>(){
            @Override
            public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
                return Long.valueOf(arg0.get("id").toString()).compareTo (Long.valueOf(arg1.get("id").toString()));
            }
        });
        System.out.println(list);
    }



}