package cn.aresoft.cms.portal.tag;
import com.puff.ioc.BeanFactory;

import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.cms.portal.service.ProductInfoService;
public class ProductInfoTag extends BaseTag {
	private ProductInfoService productInfoService = BeanFactory.getBean("productInfoService");
	@Override
	public void render() {
		String pro_code = getAttr("pro_code","");//基金代码
        ProductInfo product=productInfoService.queryByCode(pro_code);
    	this.binds(product);
    	this.doBodyRender();
    }
}