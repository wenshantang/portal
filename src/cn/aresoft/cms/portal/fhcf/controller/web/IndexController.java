package cn.aresoft.cms.portal.fhcf.controller.web;

import com.puff.framework.annotation.BeanScope;
import com.puff.framework.annotation.Controller;
import com.puff.jdbc.core.PageRecord;
import com.puff.jdbc.core.Record;
import com.puff.web.view.View;
import com.puff.web.view.ViewFactory;

import cn.aresoft.cms.portal.fhcf.controller.BaseController;
import cn.aresoft.common.model.CommonParam;
import cn.aresoft.fhcf.common.util.InterfaceUtil;

@Controller(value = "/web", scope = BeanScope.SINGLETON)
public class IndexController extends BaseController{
	
	public View autoComplete() {
		
		
		CommonParam p=new  CommonParam();
		p.setPage(1);
		p.setRows(1000000);
		
		PageRecord<Record> record=InterfaceUtil.queryFundList(null, null, null, "1", p);
		return ViewFactory.json(record);
    }
}
