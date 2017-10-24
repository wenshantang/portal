package cn.aresoft.cms.portal.fhcf.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.aresoft.cms.portal.fhcf.util.WebUtil;
import cn.aresoft.common.model.RetCode;
import cn.aresoft.common.model.RetMsg;
import cn.aresoft.fhcf.common.cache.LoginCache;
import cn.aresoft.fhcf.common.cache.UserCache;

import com.puff.core.Puff;
import com.puff.framework.utils.StringUtil;
import com.puff.ioc.BeanFactory;
import com.puff.web.interceptor.Interceptor;
import com.puff.web.mvc.DispatcherExecutor;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.RedirectView;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;

/**
 * 
 * 判断用户是否登录 controller拦截
 * 
 */
public class AccountLoginInterceptor implements Interceptor {

	protected LoginCache loginCache = BeanFactory.getBean("loginCache");
	protected UserCache userCache = BeanFactory.getBean("userCache");

	@Override
	public void intercept(DispatcherExecutor executor) {
		String login_sign = PuffContext.findCookieValue("login_sign");
		String openid = (String) PuffContext.getSession().getAttribute("openId");
		if (StringUtil.notEmpty(login_sign)||StringUtil.notEmpty(openid)) {
			String mobile = loginCache.get(login_sign);
			if (mobile == null) {
//				String openid = (String) PuffContext.getSession().getAttribute("openId");
//				mobile = CacheUtil.get(Constant.CacheRegion.WEIXIN_SIGN, openid);
			}
			if (StringUtil.notEmpty(mobile)) {
//				User uBasic = userCache.get(mobile);
//				PuffContext.setAttribute("uBasic", uBasic);
				executor.execute();
				return;
			}
		}
		if (WebUtil.isAjax()) {
			RetMsg msg = RetMsg.error(RetCode.SESSION_TIMEOUT, "登录超时");
			PuffContext.getResponse().setHeader("sessionstatus", "timeout");
			PuffContext.getResponse().setHeader("ctx", Puff.getContextPath());
			executor.setResult(ViewFactory.json(msg));
		} else {
			String target = PuffContext.getRequest().getRequestURL().toString();
			String target1 = PuffContext.getRequest().getQueryString();
			if(StringUtil.notEmpty(target1)){
				target= target+"?"+target1;
			}
			try {
				View view = new RedirectView("/login?goto=" + URLEncoder.encode(target, "UTF-8"), true);
				executor.setResult(view);
			} catch (UnsupportedEncodingException e) {
				executor.setResult(ViewFactory.redirect("/login"));
			}
		}
	}
}