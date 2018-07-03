package gd.com.controller.Interceptor;

import gd.com.utils.tokenutils.ResponseUtils;
import gd.com.utils.tokenutils.TokenEntity;
import gd.com.utils.tokenutils.TokenMessageDigest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class InterfaceInterceptor implements HandlerInterceptor {

	/**
	  * 在请求处理之前进行调用（Controller方法调用之前）
	  */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	 /**
	   * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	   */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 所有接口sign认证
     * 在请求处理之前进行调用（Controller方法调用之前）调用,
     *  返回true 则放行， false 则将直接跳出方法
     */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		HttpSession session = arg0.getSession();
		
		TokenEntity tentity = (TokenEntity) session.getAttribute("token_session");
		if(tentity==null){
			arg1.sendRedirect("http://www.baidu.com");
			return false;
		}
		String userName = tentity.getMobile();
		String sign = tentity.getToken();
		//抽奖操作合法性校验
		boolean flag=vaildOp(userName,sign,arg0,arg1,null);
		return flag;
	}
	
	
	//校验操作请求合法性和用户信息有效性
	public boolean vaildOp(String mobile,String sign,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException{
		try {
			JSONObject reJson=new JSONObject();
			//抽奖操作有效校验开始
			TokenEntity tentity= new TokenEntity();
			if (StringUtils.isBlank(sign)) {
				
				return false;
			}
			String[] arrT=(sign.trim()).split(",");//token==>mobile,timestamp,nonce,token
			if (arrT.length!=4) {
				
				return false;
			}
			if (!mobile.equals(arrT[0])) {
				
				return false;
			}
			tentity.setMobile(arrT[0]);
			tentity.setTimestamp(arrT[1]);
			tentity.setNonce(arrT[2]);
			tentity.setToken(arrT[3]);
			if (StringUtils.isBlank(tentity.getNonce())||StringUtils.isBlank(tentity.getTimestamp())||StringUtils.isBlank(tentity.getToken())) {
			
				return false;
			}
			TokenMessageDigest tmdigest = TokenMessageDigest.getInstance();
			if (!tmdigest.validate(tentity.getToken(), tentity.getTimestamp(), tentity.getNonce())) {
				
				return false;
			}
			Date date=new Date();
			if ((date.getTime()-Long.parseLong(tentity.getTimestamp()))/1000>60) {//token 60秒有效期
			
				return false;
			}
			//抽奖操作有效校验结束
			return true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
