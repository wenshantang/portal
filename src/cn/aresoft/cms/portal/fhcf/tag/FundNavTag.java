package cn.aresoft.cms.portal.fhcf.tag;
import cn.aresoft.fhcf.common.cache.FundNavCache;
import cn.aresoft.fhcf.common.model.product.FundNav;
import cn.aresoft.fhcf.common.service.FundNavService;
import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;
public class FundNavTag extends BaseTag {
	private FundNavService fundNavService = BeanFactory.getBean("fundNavService");
	private FundNavCache fundNavCache = BeanFactory.getBean("fundNavCache");
	@Override
	public void render() {
		String pro_code = getAttr("pro_code","");//基金代码
		FundNav  f = new FundNav();
        f.setFundInfoId(pro_code);
    	PageRecord<FundNav> datas =fundNavCache.get("fundnav_"+"_"+pro_code+"_"+getCommonParam().getPage());
    	if(datas==null){
    		datas=fundNavService.paging(f, getCommonParam());
    		if(datas.getDataList().size()>0){
    			fundNavCache.cache("fundnav_"+"_"+pro_code+"_"+getCommonParam().getPage(), datas);
    		}
    	}
    	this.binds(datas);
    	this.doBodyRender();
    	}
}