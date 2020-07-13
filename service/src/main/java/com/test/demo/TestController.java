package com.test.demo;


import com.test.Utils.DistributedLockUtil;
import com.test.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/6/4 15:01
 * @package com.test.demo
 */
@RestController
public class TestController{

   // public static Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);
   @Value("${corpsecret}")
   private String corpsecret;

   @Autowired
    RedisUtils redisUtils;

    private static Integer nowCount = 10000;



   @RequestMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
       //DistributedLockUtil.lock(Test2.class.getName());
       return "hello 正在测试啊";
    }

    @RequestMapping("/test")
    public String test(@RequestParam(value = "name") String name , @RequestBody String body){
        System.out.println("跳转过来了");
        System.out.println(name);
        System.out.println(body);
        return "success";
    }

    /**
     * 减少，库存等等
     * @return
     */
    @GetMapping("/redis/reduce")
    public String lockTest(){
        if(nowCount<=0){
            System.out.println("------库存不足------");
            return "------库存不足------";
        }
        if(redisUtils.setIfAbsent(String.valueOf(nowCount), "true")){
            redisUtils.expire(String.valueOf(nowCount),1, TimeUnit.SECONDS);
            Integer count = nowCount;
            --nowCount;
            redisUtils.delete(String.valueOf(count));
            System.out.println("----库存剩余----"+nowCount);
            return String.valueOf(nowCount);
        }else{
            System.out.println("系统繁忙");
            return "系统繁忙";
        }
    }




    public String urlEncode(String url) throws UnsupportedEncodingException {
        if (url == null) {
            return null;
        }

        final String reserved_char = ";/?:@=&";
        String ret = "";
        for (int i = 0; i < url.length(); i++) {
            String cs = String.valueOf(url.charAt(i));
            if (reserved_char.contains(cs)) {
                ret += cs;
            } else {
                ret += URLEncoder.encode(cs, "utf-8");
            }
        }
        return ret.replace("+", "%20");
    }
}
