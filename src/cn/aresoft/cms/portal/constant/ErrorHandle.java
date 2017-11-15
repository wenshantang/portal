package cn.aresoft.cms.portal.constant;

import com.puff.exception.ErrorHandler;
import com.puff.log.Log;
import com.puff.log.LogFactory;
import com.puff.web.mvc.Executor;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;
public class ErrorHandle implements ErrorHandler {

	private static final Log log = LogFactory.get(ErrorHandle.class);

	@Override
	public View handle404() {
		log.error("executor [404] invoke error ");
		//RetMsg msg = RetMsg.error(RetCode.NOTFOUND_RESOURCE, "木有找到您想调用的服务");
		//return ViewFactory.json(msg);
		//return new JspView("error.jsp");
//		String b = PuffContext.getRequest().getRequestURL().toString();
//		System.out.println(b);
		return ViewFactory.redirect("/error");
	}

	@Override
	public View handleExecption(Executor executor, Throwable t) {
		log.error("executor [{0}] invoke error ", t, executor);
/*		RetMsg msg = RetMsg.error(RetCode.NOTFOUND_RESOURCE, "服务器繁忙,请联系管理员,稍后重试");
		return ViewFactory.json(msg);*/
//		return new JspView("error.jsp");
		return ViewFactory.redirect("/error");
	}

}
