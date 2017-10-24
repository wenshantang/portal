package cn.aresoft.cms.portal.fhcf.tag;
import java.util.List;

import cn.aresoft.fhcf.common.cache.FundNavCache;
import cn.aresoft.fhcf.common.model.product.FundNav;
import cn.aresoft.fhcf.common.service.FundNavService;
import com.puff.ioc.BeanFactory;
public class FundNavInfoTag extends BaseTag {
	private FundNavService fundNavService = BeanFactory.getBean("fundNavService");
	private FundNavCache fundNavCache = BeanFactory.getBean("fundNavCache");
	@Override
	public void render() {
		String pro_code = getAttr("pro_code","");//基金代码
		String date_condition="now";
    	List<FundNav> list =fundNavCache.get("fundnav_"+date_condition+"_"+pro_code);
    	if(list==null){
    			list=fundNavService.fundchart(pro_code, date_condition);
    			if(list.size()>0){
        		fundNavCache.cache("fundnav_"+date_condition+"_"+pro_code,list);//存缓存
    		    }
    	}		
    	this.binds(list);
    	this.doBodyRender();
    	}
}