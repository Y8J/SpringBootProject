package gd.com.service.impl;

import gd.com.mapper.UserMapper;
import gd.com.mapper.UserMapperXml;
import gd.com.pojo.User;
import gd.com.service.UserService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

	@Override
	public PageInfo<User> findAll(User user, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Example example = new Example(User.class);
        if (!StringUtils.isEmpty(user.getName())) {
            example.createCriteria().andEqualTo("name", user.getName());
        }
        if (!StringUtils.isEmpty(user.getMobile())) {
            example.createCriteria().andEqualTo("mobile", user.getMobile());
        }
        List<User> users = usermapper.selectByExample(example);
        return new PageInfo<>(users);
	}

}
