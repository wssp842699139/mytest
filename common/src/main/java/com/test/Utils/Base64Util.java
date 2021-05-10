package com.test.Utils;

import org.apache.commons.codec.binary.Base64;

/**
 * 项目的base64工具,规范引用的包
 * @author slp
 * @date 2020/4/18 10:30
 */
public class Base64Util {
    /**
     * 编码
     * @param data
     * @return
     */
     public static String encode(byte[] data) {
         return Base64.encodeBase64String(data);
     }

    /**
     * 解码
     * @param base64Str
     * @return
     */
    public static byte[] decode(String base64Str){
         return Base64.decodeBase64(base64Str);
    }
}
