package gd.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
@PropertySource("classpath:config/person.yml")
@Component
//如果在配置文件中为spring.c1.name=ccccc1 有统一的前名，可以perfix提取出来，然后剩下的name名字会自动注入类中所对应的name
public class PersonConfig {

	@Value("${name}")
	private String name;
	@Value("${age}")
	private String age;
	@Value("${boss}")
	private String boss;
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getBoss() {
		return boss;
	}



	public void setBoss(String boss) {
		this.boss = boss;
	}


	public void speack(){    
	      System.out.println("lastName:"+name+"------"+"age:"+age+"----boss:"+boss);    
	}  
}
