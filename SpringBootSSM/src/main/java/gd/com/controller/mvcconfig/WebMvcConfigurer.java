package gd.com.controller.mvcconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author yangjing
 * 编写拦截器配置文件主类 WebMvcConfigurer  
 * 此类必须继承  WebMvcConfigurerAdapter 类，
 * 并重写其中的方法  addInterceptors   
 * 并且在主类上加上注解  @Configuration 
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	/**
	 * java配置跨域springboot配置Cors跨域
	 */
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("http://192.168.89.89")
                .allowedMethods("GET", "POST","DELETE")
                .allowCredentials(false).maxAge(3600);
    }
	
	 /**
     * <p>Title:</p>
     * <p>Description:重写增加自定义拦截器的注册，某一个拦截器需要先注册进来，才能工作
     * return:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	
    	// addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
    	//拦截器1
        //registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**");
	
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        //接口sign 安全认证拦截接口
      /* registry.addInterceptor(new InterfaceInterceptor()).addPathPatterns("/**")
                                                     .excludePathPatterns("/sign.do");*/
	
        super.addInterceptors(registry);
    }
}
