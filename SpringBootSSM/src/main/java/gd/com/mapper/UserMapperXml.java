package gd.com.mapper;

import gd.com.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapperXml {

	public List<Map<Object,Object>> queryTestMap();
	
	public List<User> queryTestPojo();
}
