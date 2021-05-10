package com.test.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "seconedFilter",urlPatterns = "/*")
@Order(2)
public class SeconedFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("进入第二个过滤的前置接口");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        //favicon 不请求 否则给人执行两次的错觉
        String requestURI = httpServletRequest.getRequestURI();
       // System.out.println("请求URI" + requestURI);
        if(!requestURI.contains("favicon.ico")){
           // System.out.println("进来第二个过滤器");
          // System.out.println(requestURI);
            filterChain.doFilter(servletRequest,servletResponse);
          //  System.out.println("退出了第二个过滤器");
        }
    }

    @Override
    public void destroy() {
        System.out.println("第二个filter后置接口");
    }
}
