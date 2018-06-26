package gd.com.service;

import gd.com.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

	/**
	 * 分页查询用户列表
	 */
	public List<User> pagehelperUserList(User user,Integer pageNo,Integer pageSize);
	
	/**
	 * MyBatis 本身XML配置文件查询数据库数据
	 * @return
	 */
	public List<Map<Object,Object>>  UserXmlHash();
	
	/**
	 * MyBatis 本身XML配置文件查询数据库数据 关联User,bean
	 * @return
	 */
	public List<User>  UserXmlBean();
}
