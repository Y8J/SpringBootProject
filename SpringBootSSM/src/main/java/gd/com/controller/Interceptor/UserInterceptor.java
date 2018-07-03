package gd.com.controller.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器的实现
 * @author yangjing
 * 1、preHandle  方法会在请求处理之前进行调用（Controller方法调用之前）
 * 2、postHandle  请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
 * 3、afterCompletion  在整个请求结束之后被调用，也就是在DispatcherServlet 
 *    渲染了对应的视图之后执行（主要是用于进行资源清理工作）
 */
public class UserInterceptor implements HandlerInterceptor {

	/**
	  * 在请求处理之前进行调用（Controller方法调用之前）
	  */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	 /**
	   * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	   */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
     * 在请求处理之前进行调用（Controller方法调用之前）调用,
     *  返回true 则放行， false 则将直接跳出方法
     */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		String ip = getIpAddr(arg0);
		System.out.println("拦截器获取的Ip地址为:"+ip);
		
		return true;
	}
	
	/**
     * 获取访问的ip地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
