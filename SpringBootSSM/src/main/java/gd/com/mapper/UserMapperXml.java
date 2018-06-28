package gd.com.mapper;

import gd.com.pojo.Permission;
import gd.com.pojo.Role;
import gd.com.pojo.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapperXml {

	public List<Map<Object,Object>> queryTestMap();
	
	public List<User> queryTestPojo();
	
	/**
	 * 根据用户查询用户所有权限
	 */
	public List<Permission> findPermissionByUserId(User user);
	
	/**
	 * 根据用户查询用户所有角色
	 */
	public List<Role> findRoleByUserId(User user);
}
