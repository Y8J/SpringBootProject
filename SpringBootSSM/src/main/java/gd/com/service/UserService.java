package gd.com.service;

import gd.com.pojo.Permission;
import gd.com.pojo.User;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

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

	/**
	 * 分页数据携带显示
	 * @param user
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<User> findAll(User user, Integer pageNo, Integer pageSize);
	
	/**
	 * 根据用户查询用户所有权限
	 */
	public List<Permission> findPermissionByUserId(User user);
}
