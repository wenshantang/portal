package cn.aresoft.cms.portal.fhcf.controller;

import cn.aresoft.fhcf.common.cache.LoginCache;
import cn.aresoft.fhcf.common.cache.UserCache;
import com.puff.framework.annotation.Inject;

public class BaseController {

	@Inject
	private UserCache userCache;
	@Inject
	private LoginCache loginCache;

//	protected User getUBasic() {
//		String login_sign = PuffContext.findCookieValue("login_sign");
//		String cust_id = loginCache.get(login_sign);
//		if (cust_id == null) {
//			String openid = (String) PuffContext.getSession().getAttribute("openId");
//			cust_id = userCache.get(openid);
//		}
//		return userCache.get(cust_id);
//	}

}
