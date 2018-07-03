package gd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import gd.com.controller.Filter.UserFilter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "gd.com.mapper")
@SpringBootApplication
@EnableTransactionManagement//开启事务管理
//在启动类加入注解@ServletComponentScan,即可完成@WebServlet、@WebFilter和@WebListener自动注册
@ServletComponentScan
//定时器功能的
@EnableScheduling
public class SmallsystemApplication  extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(SmallsystemApplication.class, args);
    }
    

    /**
     * 过滤器注入
     * @return
     */
    @Bean
    public FilterRegistrationBean filterCodeRegistration() {  
        FilterRegistrationBean registration = new FilterRegistrationBean();  
        registration.setFilter(new UserFilter());
        //设定匹配的路径
        registration.addUrlPatterns("/user/*");
        //设定加载的顺序
        registration.setOrder(2);  
        return registration;  
    }
    
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
  
}