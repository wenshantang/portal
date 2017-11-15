package cn.aresoft.cms.portal.tag;
import cn.aresoft.cms.portal.cache.ProductListCache;
import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.cms.portal.service.ProductInfoService;

import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;
public class ProductListTag extends BaseTag {
	private ProductInfoService productInfoService = BeanFactory.getBean("productInfoService");
	private ProductListCache productListCache = BeanFactory.getBean("productListCache");
	@Override
	public void render() {
		String pro_style = getAttr("pro_style", "");//'产品类型：固定、浮动、海外',
		String pro_direction = getAttr("pro_direction", "");//产品风向，是用于钱码头产品的分类
		String i_prostatus = getAttr("i_prostatus", "");//开放状态：1：在售 2：即将开放 3：已售罄 
    	ProductInfo  p = new ProductInfo();
    	/*p.setPro_style(pro_style);
    	p.setPro_direction(pro_direction);
    	p.setI_prostatus(i_prostatus);*/
    	PageRecord<ProductInfo> datas = productListCache.get("prolist_"+pro_style+"_"+pro_direction+"_"+i_prostatus+"_"+getCommonParam().getPage());
    	if(datas==null){
    		datas=productInfoService.paging(p, getCommonParam());
    		if(datas.getDataList().size()>0){
    			productListCache.cache("prolist_"+pro_style+"_"+pro_direction+"_"+i_prostatus+"_"+getCommonParam().getPage(), datas);
    		}
    	}
    	this.binds(datas);
    	this.doBodyRender();
    	}
}