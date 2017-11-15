package cn.aresoft.cms.portal.accessIntercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.aresoft.cms.common.AccessIntercept;
import cn.aresoft.cms.common.model.CmsTopic;

import com.puff.web.view.View;
//import cn.aresoft.fhcf.common.model.system.User;

/**
 * 
 * 判断用户是否实名认证栏目拦截
 */
public class RealNameInterceptor implements AccessIntercept {

	@Override
	public View intercept(HttpServletRequest request, HttpServletResponse response, CmsTopic cmsTopic) {
//		User u = PuffContext.getAttribute("uBasic");
//		if (StringUtil.empty(u.getCert_code())) {
//			boolean isMobile = WebUtil.isMobileDevice(PuffContext.getUserAgentStr());
//			if (isMobile) {
//				return ViewFactory.redirect("/mi/tzyhkjm?goto=banklist");
//			} else {
//				return ViewFactory.redirect("/openaccount/choosebank/remind");
//			}
//		} else {
			return null;
//		}
	}
}