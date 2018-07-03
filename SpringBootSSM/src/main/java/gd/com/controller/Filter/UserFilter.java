package gd.com.controller.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

/**
 * 过滤器的实现
 * Servlet Filter implementation class ServletFilter
 * 注解注册过滤器：实现 javax.servlet.Filter接口 
 * filterName 是过滤器的名字
 * urlPatterns 是需要过滤的请求 ，这里只过滤servlet/* 下面的所有请求
 */
//@Component //就是把这个类自动注入到IOC容器中
@WebFilter(filterName="userFilter",urlPatterns="/user/*")
public class UserFilter implements Filter {

	   /**
     * Default constructor. 
     */
    public void ServletFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        System.out.println("过滤器被销毁。");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        System.out.println("过滤器正在执行...");
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("初始化过滤器。");
    }


}
