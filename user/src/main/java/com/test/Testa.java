package com.test;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/3 3:17
 * @package com.test
 */
public class Testa {
    public static void main(String[] args) throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "测试");
        map.put("b", "f");
        map.put("c", "3");
        // map转str
        String str1 = map.toString();
        System.out.println(str1);
        //map转json
        String s = JSONObject.toJSONString(map);
        System.out.println(s);

        //json转map
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", "测试");
        jsonObject.put("b", "f");
        jsonObject.put("c", "3");
        Map hashMap = JSONObject.parseObject(s, HashMap.class);
        System.out.println(hashMap.toString());
        System.out.println(hashMap.get("a"));
        Map map1 = jsonObject.toJavaObject(Map.class);
        System.out.println(map1.toString());
        System.out.println(map1.get("a"));
    }
}