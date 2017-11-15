package cn.aresoft.cms.portal.controller;

import cn.aresoft.cms.portal.accessIntercept.FormSubmitTokenInterceptor;
import cn.aresoft.cms.portal.model.Umsg;
import cn.aresoft.cms.portal.service.UmsgService;
import cn.aresoft.common.model.RetCode;
import cn.aresoft.common.model.RetMsg;

import com.puff.framework.annotation.BeanScope;
import com.puff.framework.annotation.Before;
import com.puff.framework.annotation.Controller;
import com.puff.framework.annotation.Inject;
import com.puff.framework.utils.DateUtil;
import com.puff.framework.utils.StringUtil;
import com.puff.web.mvc.PuffContext;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;
/**
 * 预约功能以及留言功能
 */
@Controller(value = "/portal/umsg", scope = BeanScope.SINGLETON)
public class UmsgController {
	@Inject 
	private UmsgService umsgService;
	@Before(FormSubmitTokenInterceptor.class)
	public View add() {
		String name=PuffContext.getParameter("name");//姓名
		String phone=PuffContext.getParameter("phone");//手机号
		String email=PuffContext.getParameter("email");//邮箱
		String type_id=PuffContext.getParameter("type_id");//产品类型
		String type=PuffContext.getParameter("type");//'1:活动  2:产品'
		String msg_name=PuffContext.getParameter("msg_name");//活动名称
		String msg_id=PuffContext.getParameter("msg_id");//活动id
		if(StringUtil.empty(name)){
			return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM,"姓名不能为空!"));
		}
        if(StringUtil.empty(phone)){
        	return ViewFactory.json(RetMsg.error(RetCode.NULL_PARAM, "手机号码不能为空!"));
		}
		Umsg u = new Umsg();
		u.setMsg_id(msg_id);
		u.setMsg_name(msg_name);
		u.setName(name);
		u.setMobile(phone);
		u.setEmail(email);
		u.setType_id(type_id);
		u.setType(type);
		String dateStr = "";
		u.setCreate_time(dateStr);
		u.setUpdate_time(dateStr);
		umsgService.insert(u);
		return ViewFactory.json(RetMsg.success("新增成功！"));
	}

	
}
