package cn.aresoft.cms.portal.accessIntercept;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.aresoft.cms.common.AccessIntercept;
import cn.aresoft.cms.common.model.CmsTopic;
import cn.aresoft.cms.portal.cache.LoginCache;
import cn.aresoft.cms.portal.cache.UserCache;
import cn.aresoft.common.model.RetCode;
import cn.aresoft.common.model.RetMsg;

import com.puff.core.Puff;
import com.puff.framework.utils.StringUtil;
import com.puff.ioc.BeanFactory;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;

/**
 * 
 * 判断用户是否登录 网站栏目拦截
 */
public class AccountLoginAccessIntercept implements AccessIntercept {

	protected LoginCache loginCache = BeanFactory.getBean("loginCache");
	protected UserCache userCache = BeanFactory.getBean("userCache");

	@Override
	public View intercept(HttpServletRequest request, HttpServletResponse response, CmsTopic cmsTopic) {
		String login_sign = PuffContext.findCookieValue("login_sign");
		String open_id = PuffContext.getSessionAttribute("openId");//获取session里的openid
		if (StringUtil.notEmpty(login_sign)) {
			String mobile = loginCache.get(login_sign);
			if (StringUtil.notEmpty(mobile)) {
//				User uBasic = userCache.get(mobile);
//				if(uBasic!=null){
//					request.setAttribute("uBasic", uBasic);
//					return null;
//				}
			}
		}
		//微信自动登录
		if(StringUtil.notEmpty(open_id)){
//			User uBasic = userService.queryByOpenid(open_id);
//			if(uBasic!=null&&StringUtil.notEmpty(uBasic.getCert_code())){
//				String loginString = automaticLogin(uBasic,open_id,login_sign);//自动登录
//				if("seccess".equals(loginString)){
//					request.setAttribute("uBasic", uBasic);
//					return null;
//				}
//			}
		}
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
			RetMsg msg = RetMsg.error(RetCode.SESSION_TIMEOUT, "登录超时");
			response.setHeader("sessionstatus", "timeout");
			response.setHeader("ctxPath", Puff.getContextPath());
			return ViewFactory.json(msg);
		} else {
			String target = request.getRequestURL().toString();
			String target1 = PuffContext.getRequest().getQueryString();
			if (StringUtil.notEmpty(target1)) {
				target = target + "?" + target1;
			}
			int indexOf = target.indexOf("/mi");
			if (indexOf != -1) {
				try {
					return ViewFactory.redirect("/mi/login?goto=" + URLEncoder.encode(target, "UTF-8"));
					
				} catch (UnsupportedEncodingException e) {
					return ViewFactory.redirect("/mi/login");
				}
			} else {
				try {
					return ViewFactory.redirect("/login?goto=" + URLEncoder.encode(target, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					return ViewFactory.redirect("/login");
				}
			}
		}
	}
	/**
	 * 如果已绑定将自动登录
	 */
//	public String automaticLogin(User user,String openid,String login_sign){
//			 String mobile = user.getMobile();
//	         JSONObject obj = JSONObject.parseObject(InterfaceApiUtil.loginsessionkey(user.getCert_code(), user.getCert_type()));
//	         if ("0000".equals(obj.getString("code"))) {
//	        	 if(StringUtil.notEmpty(login_sign)){
//	        		 PuffContext.failureCookie("login_sign");
//	        	 }
//	        	 obj = JSONObject.parseObject(obj.getString("data"));
//	        	 userCache.cache(mobile, user,1500);
//				 login_sign = IdentityUtil.uuid32();
//				 loginCache.cache(openid, user.getMobile(),1500);
//		         loginCache.cache(login_sign, mobile, 1500);
//		         PuffContext.addCookie("login_sign", login_sign, 1500);
//		         sessionkeyCache.cache(mobile, obj.get("sessionkey"), 1500);
//		         return "seccess";
//	        }else{
//	        	return "fail";
//	        }
//	}
}