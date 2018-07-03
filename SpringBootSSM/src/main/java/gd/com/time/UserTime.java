package gd.com.time;

import gd.com.pojo.User;
import gd.com.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//把普通bean实例化到spring容器中，相当于配置文件中的
@Component
public class UserTime {
	@Autowired
	private UserService userservice;
	
	/**
	 * 定时查询用户名
	 */
	@Scheduled(cron = "0/3 * * * * *")
	public void queryUsertimer(){
		List<User> findAll = userservice.pagehelperUserList(null, 1, 10);
		System.out.println(findAll.get(0).getName());
		System.out.println(findAll.get(1).getName());
		System.out.println(findAll.get(2).getName());
		System.out.println(findAll.get(3).getName());
		System.out.println(findAll.get(4).getName());
		System.out.println(findAll.get(5).getName());
	}
}
