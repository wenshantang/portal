package cn.aresoft.cms.portal.accessIntercept;

import java.util.List;

import cn.aresoft.common.model.RetCode;
import cn.aresoft.common.model.RetMsg;

import com.puff.framework.utils.IdentityUtil;
import com.puff.framework.utils.StringUtil;
import com.puff.web.interceptor.Interceptor;
import com.puff.web.mvc.DispatcherExecutor;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.ViewFactory;

public class FormSubmitTokenInterceptor implements Interceptor {
	
	@SuppressWarnings("unchecked")
	@Override
	public void intercept(DispatcherExecutor executor) {
		String server_token="";
		List<String> tokenList=(List<String>) PuffContext.getSession().getAttribute("token_list");//token list
		String client_token = PuffContext.getRequest().getHeader("Puff-ClientToken");
		if(tokenList!=null&&tokenList.size()>0){
			if(tokenList.contains(client_token)){//缓存token列表中存在请求的token，则请求通过
				server_token=client_token;
				tokenList.remove(client_token);// 移除已经提交的token,防止重复提交
			}
		}
		if (StringUtil.empty(server_token) || StringUtil.empty(client_token) || !server_token.equals(client_token)) {
			if (PuffContext.ajax()) {
				RetMsg msg = RetMsg.error(RetCode.ILLEGAL_SUBMIT, "不可重复提交申请！");
				PuffContext.getResponse().setHeader("illegal_submit", "yes");
				executor.setResult(ViewFactory.json(msg));
			} else {
				throw new IllegalArgumentException("非法表单提交申请！");
			}
		} else {
			executor.execute();
		}
		String token = IdentityUtil.uuid32();
		tokenList.add(token);//保存token到列表zhong
		if(tokenList.size()>=1000){//超过1000的时候删除首次加入的元素
			tokenList.remove(0);
		}
		PuffContext.setSessionAttribute("token_list", tokenList);
		PuffContext.getResponse().setHeader("server_token", token);

	}

}
