package gd.com.controller.admin;

import gd.com.pojo.User;
import gd.com.service.UserService;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@RequestMapping(value="userpda")
@Controller
public class UserPutDeleteCliAct {

	@Autowired
	private UserService userservice;
	
	    
	    @ResponseBody
	    @RequestMapping(value="/{pageNo}/{pageSize}",method = RequestMethod.PUT)
		public List<User> pagehelperUserListPut(HttpServletRequest request,HttpServletResponse response,
				                                ModelMap model,@PathVariable("pageNo")Integer pageNo,
				                               @PathVariable("pageSize") Integer pageSize,User user){
			
			List<User> pagehelperUserList = userservice.pagehelperUserList(user, pageNo, pageSize);
			return pagehelperUserList;
		}
	    
	    
	    @ResponseBody
	    @RequestMapping(value="/{pageNo}/{pageSize}",method = RequestMethod.DELETE)
		public List<User> pagehelperUserListDele(HttpServletRequest request,HttpServletResponse response,
				                                 ModelMap model,@PathVariable("pageNo")Integer pageNo,
				                                 @PathVariable("pageSize")Integer pageSize,User user){
			
			List<User> pagehelperUserList = userservice.pagehelperUserList(user, pageNo, pageSize);
			return pagehelperUserList;
		}
}
