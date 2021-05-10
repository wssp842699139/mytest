package com.test.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
@Order(1)
public class LoginFilter implements Filter {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("开始初始化请求");
        //初始化  读取所有的注解
        Map<String, Object> beansWithAnnotationMap = applicationContext.getBeansWithAnnotation(Controller.class);
        Set<Map.Entry<String, Object>> entries = beansWithAnnotationMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Class<?> clazz = entry.getValue().getClass().getSuperclass();
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            if (null != requestMapping){
                String s = requestMapping.value()[0];
                System.out.println("s:"+s);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        //favicon 不请求 否则给人执行两次的错觉
        String requestURI = httpServletRequest.getRequestURI();
       // System.out.println("请求URI" + requestURI);
        if(!requestURI.contains("favicon.ico")){
            //System.out.println("进来了过滤器");
            //System.out.println(requestURI);
            filterChain.doFilter(servletRequest,servletResponse);
            //System.out.println("退出了过滤器");
        }
    }

    @Override
    public void destroy() {
        System.out.println("第一个filter后置接口");
    }
}
