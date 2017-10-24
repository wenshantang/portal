package cn.aresoft.cms.portal.fhcf.interceptor;

import com.puff.framework.utils.IdentityUtil;
import com.puff.framework.utils.StringUtil;
import com.puff.web.interceptor.Interceptor;
import com.puff.web.mvc.DispatcherExecutor;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.ViewFactory;
import cn.aresoft.common.model.RetCode;
import cn.aresoft.common.model.RetMsg;

public class FormSubmitTokenInterceptor implements Interceptor {

	@Override
	public void intercept(DispatcherExecutor executor) {
		String server_token = PuffContext.removeSessionAttr("server_token");
		String client_token = PuffContext.getRequest().getHeader("Puff-ClientToken");
		if (StringUtil.empty(server_token) || StringUtil.empty(client_token) || !server_token.equals(client_token)) {
			if (PuffContext.ajax()) {
				RetMsg msg = RetMsg.error(RetCode.ILLEGAL_SUBMIT, "非法表单提交申请！");
				PuffContext.getResponse().setHeader("illegal_submit", "yes");
				executor.setResult(ViewFactory.json(msg));
			} else {
				throw new IllegalArgumentException("非法表单提交申请！");
			}
		} else {
			executor.execute();
		}
		String token = IdentityUtil.uuid32();
		PuffContext.setSessionAttribute("server_token", token);
		PuffContext.getResponse().setHeader("server_token", token);

	}

}
