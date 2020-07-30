package com.test.demo;


import com.test.Student;
import com.test.Utils.MD5Utils;
import com.test.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/6/4 15:01
 * @package com.test.demo
 */
@RestController
public class TestController {

    // public static Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);
    @Value("${corpsecret}")
    private String corpsecret;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    TestService testService;


    private static Integer nowCount = 10000;




    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
       return "success";
    }



    @RequestMapping("/test")
    public String test(@RequestParam(value = "name") String name, @RequestBody String body) {
        System.out.println("跳转过来了");
        System.out.println(name);
        System.out.println(body);
        return "success";
    }

    /**
     * 减少，库存等等
     *
     * @return
     */
    @GetMapping("/redis/reduce")
    public String lockTest() {
        if (nowCount <= 0) {
            System.out.println("------库存不足------");
            return "------库存不足------";
        }
        if (redisUtils.setIfAbsent(String.valueOf(nowCount), "true")) {
            redisUtils.expire(String.valueOf(nowCount), 1, TimeUnit.SECONDS);
            Integer count = nowCount;
            --nowCount;
            redisUtils.delete(String.valueOf(count));
            System.out.println("----库存剩余----" + nowCount);
            return String.valueOf(nowCount);
        } else {
            System.out.println("系统繁忙");
            return "系统繁忙";
        }
    }

    @RequestMapping("/testTransactional")
    public void testTransactional(
            //@RequestParam("ids") List<Integer> ids,
            @RequestParam("username") String username,
            @RequestParam("address") String address,
            HttpServletRequest request
            ){
        //System.out.println(ids.toString());
        Enumeration<String> parameterNames = request.getParameterNames();
        List<String> parList = new ArrayList<>();

        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            parList.add(s);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("fsfsfsgset");
        Collections.sort(parList);
        for (String s : parList) {
            String[] parameterValues = request.getParameterValues(s);
            List<String> list = Arrays.asList(parameterValues);
            System.out.println("添加的key为 :" +s + "添加的value为 : "+list.toString());
            if (!CollectionUtils.isEmpty(list)){
                for (String s1 : list) {
                    stringBuffer.append(s1);
                }
            }
        }
        System.out.println("第一次拼接的String参数为 : " +stringBuffer);

        String md5Encode = MD5Utils.MD5Encode(stringBuffer.toString(), "utf-8");
        System.out.println("第一次加密的MD5为 :" + md5Encode);

        String par2 = md5Encode+"hhjgjfjrtyhfg";
        System.out.println("第二次拼接的String参数为 : " +par2);

        String md5Encode1 = MD5Utils.MD5Encode(par2, "utf-8");
        System.out.println("第二次加密后的MD5为 :" + md5Encode1);
    }

    @RequestMapping("/testInsert")
    public void testInsert(
            @RequestBody Student student
    ){
        System.out.println(student.toString());
        testService.insertStudent(student);
}

    @RequestMapping("/testUpdate")
    public void testUpdate(
            @RequestBody Student student
    ){
        testService.updateStudent(student);
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
