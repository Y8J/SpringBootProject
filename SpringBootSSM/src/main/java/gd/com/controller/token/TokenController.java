package gd.com.controller.token;

import gd.com.utils.tokenutils.DateTimeUtils;
import gd.com.utils.tokenutils.ResponseUtils;
import gd.com.utils.tokenutils.TokenEntity;
import gd.com.utils.tokenutils.TokenMessageDigest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;



@Controller
public class TokenController {

	/**
	 * 抽奖操作凭证生成
	 * @param mobile
	 * @param request
	 * @param response
	 * @param model
	 * @throws JSONException 
	 */
	@ResponseBody
	@RequestMapping("/sign.do")
	public  TokenEntity lotterySign(String userName,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			
			TokenEntity tentity=new TokenEntity();
			TokenMessageDigest tmdigest = TokenMessageDigest.getInstance();
			String timestamp=""+new Date().getTime();//时间戳
			String nonce=""+DateTimeUtils.getRandomCode(9);//随机数
			String token=tmdigest.token(timestamp, nonce);//验证令牌
			tentity.setCode("T");
			tentity.setMsg("生成成功！");
			tentity.setNonce(nonce);
			tentity.setTimestamp(timestamp);
			StringBuffer ticketBuffer=new StringBuffer();
			ticketBuffer.append(userName).append(",").append(timestamp).append(",").append(nonce).append(",").append(token);
			tentity.setToken(ticketBuffer.toString());//token==>mobile,timestamp,nonce,token
			tentity.setMobile(userName);
			
			HttpSession session = request.getSession();
			session.setAttribute("token_session", tentity);
			return tentity;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
    //校验操作请求合法性和用户信息有效性
	public boolean vaildOp(String mobile,String sign,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException{
		try {
			JSONObject reJson=new JSONObject();
			//抽奖操作有效校验开始
			TokenEntity tentity= new TokenEntity();
			if (StringUtils.isBlank(sign)) {
				reJson.put("status", 1);
				reJson.put("msg", "非法请求1..");
				ResponseUtils.renderJson(response, reJson.toString());
				return false;
			}
			String[] arrT=(sign.trim()).split(",");//token==>mobile,timestamp,nonce,token
			if (arrT.length!=4) {
				reJson.put("status", 1);
				reJson.put("msg", "非法请求1..");
				ResponseUtils.renderJson(response, reJson.toString());
				return false;
			}
			if (!mobile.equals(arrT[0])) {
				reJson.put("status", 1);
				reJson.put("msg", "非法请求1..");
				ResponseUtils.renderJson(response,reJson.toString());
				return false;
			}
			tentity.setMobile(arrT[0]);
			tentity.setTimestamp(arrT[1]);
			tentity.setNonce(arrT[2]);
			tentity.setToken(arrT[3]);
			if (StringUtils.isBlank(tentity.getNonce())||StringUtils.isBlank(tentity.getTimestamp())||StringUtils.isBlank(tentity.getToken())) {
				reJson.put("status", 1);
				reJson.put("msg", "非法请求1..");
				ResponseUtils.renderJson(response,reJson.toString());
				return false;
			}
			TokenMessageDigest tmdigest = TokenMessageDigest.getInstance();
			if (!tmdigest.validate(tentity.getToken(), tentity.getTimestamp(), tentity.getNonce())) {
				reJson.put("status", 1);
				reJson.put("msg", "非法请求1..");
				ResponseUtils.renderJson(response, reJson.toString());
				return false;
			}
			Date date=new Date();
			if ((date.getTime()-Long.parseLong(tentity.getTimestamp()))/1000>60) {//token 60秒有效期
				reJson.put("status", 1);
				reJson.put("msg", "非法请求1..");
				ResponseUtils.renderJson(response, reJson.toString());
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
	
	
	@RequestMapping("yansign.do")
	public void sendPrizeCD(String userName,String smsCode,String sign,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			JSONObject reJson=new JSONObject();
			//抽奖操作合法性校验
			boolean flag=vaildOp(userName,sign,request,response,model);
			if (!flag) {
				return;
			}
			reJson.put("status", 0);
			reJson.put("msg", "验证通过！");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
