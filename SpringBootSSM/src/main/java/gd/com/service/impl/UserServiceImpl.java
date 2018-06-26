package gd.com.service.impl;

import gd.com.mapper.UserMapper;
import gd.com.mapper.UserMapperXml;
import gd.com.pojo.User;
import gd.com.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {

	//通用Mapper查询数据
	@Autowired
	private UserMapper usermapper;
	
	//Mybatis的XML查询数据
	@Autowired
	private UserMapperXml userMapperXml;
	
	@Override
	public List<User> pagehelperUserList(User user, Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> select = usermapper.select(user);
		return select;
	}
	
	@Override
	public List<Map<Object,Object>>  UserXmlHash(){
		
		return userMapperXml.queryTestMap();
	}

	@Override
	public List<User> UserXmlBean() {
		List<User> queryTestPojo = userMapperXml.queryTestPojo();
		return queryTestPojo;
	}

}
