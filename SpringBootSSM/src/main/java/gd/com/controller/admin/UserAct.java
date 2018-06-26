package gd.com.controller.admin;

import gd.com.pojo.User;
import gd.com.service.UserService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("user")
@Controller
public class UserAct {

	@Autowired
	private UserService userservice;
	
	@ResponseBody
	@RequestMapping("page")
	public List<User> pagehelperUserList(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,Integer pageNo,Integer pageSize,User user){
		
		List<User> pagehelperUserList = userservice.pagehelperUserList(user, pageNo, pageSize);
		return pagehelperUserList;
	}
	
	@ResponseBody
	@RequestMapping("xmlhash")
	public List<Map<Object,Object>>  userListXml(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,Integer pageNo,Integer pageSize,User user){
		
		List<Map<Object,Object>> pagehelperUserList = userservice.UserXmlHash();
		return pagehelperUserList;
	}
	
	@ResponseBody
	@RequestMapping("xmlbean")
	public List<User>  userListXmlBean(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,Integer pageNo,Integer pageSize,User user){
		
		List<User> pagehelperUserList = userservice.UserXmlBean();
		return pagehelperUserList;
	}
	
	@RequestMapping("mapperUserHtml.html")
	public String  mapperUserHtml(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,Integer pageNo,Integer pageSize,User user){
		
		List<User> pagehelperUserList = userservice.pagehelperUserList(user, pageNo, pageSize);
		model.addAttribute("user", pagehelperUserList);
		return "admin/user";
	}
}