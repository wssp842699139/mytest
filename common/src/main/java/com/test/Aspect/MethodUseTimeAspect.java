package com.test.Aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * @author wy
 */
@Aspect
@Slf4j
@Component
public class MethodUseTimeAspect {

    @Pointcut(value = "@annotation(com.test.annotation.MethodUseTime)")
    public void access() {}

    @Around("access()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        JSONObject params=new JSONObject(parameterNames.length,true);
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i],JSON.toJSON(args[i]));
        }
        String joinPointName = joinPoint.toLongString();

        //log.info("{} >>> args:{}", joinPointName, params.entrySet());
        long start = System.currentTimeMillis();
        try {
            Object res=joinPoint.proceed();
            //log.info("{} >>> return:{}", joinPointName, JSONObject.toJSON(res));
            return res;
        }finally {
            System.out.println(System.currentTimeMillis()-start);
            log.info("{} >>> used time:{}ms",joinPointName,System.currentTimeMillis()-start);
        }

    }
}
