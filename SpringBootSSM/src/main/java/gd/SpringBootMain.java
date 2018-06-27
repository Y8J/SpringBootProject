package gd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主函数入口
 * @author yangjing
 *
 */
//
/*@MapperScan(basePackages = "gd.com.mapper") //与dao层的@Mapper二选一写上即可(主要作用是扫包)
@EnableTransactionManagement//开启事务管理
@SpringBootApplication*/
public class SpringBootMain {

	/*public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class, args);
	}*/
}
