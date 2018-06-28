package gd.com.controller.admin;

import gd.com.config.PersonConfig;
import gd.com.pojo.Permission;
import gd.com.pojo.User;
import gd.com.service.UserService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

@RequestMapping("user")
@Controller
public class UserAct {

	@Autowired
	private UserService userservice;

    @RequestMapping("/userpage")
    public String index(Model model,User user, 
    		            @RequestParam(defaultValue = "1") Integer pageNum, 
    		            @RequestParam(defaultValue = "3") Integer pageSize) {
        PageInfo<User> pageInfo = userservice.findAll(user, pageNum, pageSize);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("users", pageInfo.getList());

        return "userTable";
    }
	
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
	
	
	@Autowired
	private PersonConfig personConfig;
	/**
	 * SpringBoot页面jsp跳转
	 * @param request
	 * @param response
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@RequestMapping("mapperuserhtml.html")
	public String  mapperUserHtml(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,Integer pageNo,Integer pageSize,User user){
		PersonConfig p = personConfig;
		String msg = p.getName()+"---"+p.getAge()+"---"+p.getBoss();
		model.addAttribute("msg", msg);
		System.out.println(p.getName());
		List<User> pagehelperUserList = userservice.pagehelperUserList(user, pageNo, pageSize);
		model.addAttribute("user", pagehelperUserList);
		return "admin/user";
	}
	
	/**
	 * SpringBoot页面jsp跳转
	 * @param request
	 * @param response
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findPermissionByUser.do")
	public List<Permission>  findPermissionByUser(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,User user){
		
		List<Permission> pagehelperUserList = userservice.findPermissionByUserId(user);
		
		return pagehelperUserList;
	}
	
}
