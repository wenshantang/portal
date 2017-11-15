package cn.aresoft.cms.portal.tag;
import cn.aresoft.cms.portal.cache.ProductReportCache;
import cn.aresoft.cms.portal.model.ProductReport;
import cn.aresoft.cms.portal.service.ProductReportService;

import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;
public class ProductReportTag extends BaseTag {
	private ProductReportService productReportService = BeanFactory.getBean("productReportService");
	private ProductReportCache productReportCache = BeanFactory.getBean("productReportCache");
	@Override
	public void render() {
		String pro_code = getAttr("pro_code","");//基金代码
		ProductReport  p = new ProductReport();
        p.setPro_code(pro_code);
    	PageRecord<ProductReport> datas =productReportCache.get("product_report"+"_"+pro_code+"_"+getCommonParam().getPage());
    	if(datas==null){
    		datas=productReportService.paging(p, getCommonParam());
    		if(datas.getDataList().size()>0){
    			productReportCache.cache("product_report"+"_"+pro_code+"_"+getCommonParam().getPage(), datas);
    		}
    	}
    	this.binds(datas);
    	this.doBodyRender();
	}
}