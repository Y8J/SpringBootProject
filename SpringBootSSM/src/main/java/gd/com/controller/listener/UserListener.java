package gd.com.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * SpringBoot监听器的使用
 * @author yangjing
 *
 */
@WebListener
public class UserListener implements ServletContextListener {

	/**
	 * 当Servlet容器终止Web应用时调用该方法。
	 * 在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		System.out.println("监听器的contextDestroyed方法");
		
	}

	/**
	 * 当Servlet容器启动Web应用时调用该方法。在调用完该方法之后，容器再对Filter初始化，
	 * 并且对那些在Web应用启动时就需要被初始化的Servlet进行初始化。
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		System.out.println("监听器的contextInitialized方法");
	}

}
