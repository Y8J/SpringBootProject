package gd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "gd.com.mapper")
@SpringBootApplication
@EnableTransactionManagement//开启事务管理
public class SmallsystemApplication  extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(SmallsystemApplication.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}