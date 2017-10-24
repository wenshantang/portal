package cn.aresoft.cms.portal.fhcf.tag;
import cn.aresoft.fhcf.common.cache.ProductListCache;
import cn.aresoft.fhcf.common.model.product.ProductInfo;
import cn.aresoft.fhcf.common.service.ProductInfoService;

import com.puff.framework.utils.StringUtil;
import com.puff.ioc.BeanFactory;
public class ProductInfoTag extends BaseTag {
	private ProductInfoService productInfoService = BeanFactory.getBean("productInfoService");
	private ProductListCache productListCache = BeanFactory.getBean("productListCache");
	@Override
	public void render() {
		String pro_code = getAttr("pro_code","");//基金代码
        ProductInfo product  = productListCache.get("productinfo_"+pro_code);
        ProductInfo p =null;
        if(product==null){
        	product=productInfoService.queryByCode(pro_code);
        	productListCache.cache("productinfo_"+pro_code, product);
        }else{
        	 p=productListCache.get("productinfo_"+product.getVc_pro_code());
        	if(p==null){
        		if(StringUtil.notEmpty(product.getVc_pro_code())){
        			p=productInfoService.queryByCode(product.getVc_pro_code());
        			productListCache.cache("productinfo_"+product.getVc_pro_code(), p);
        		}else{
        			p = new ProductInfo();
        		}
        	}
        }
    	this.binds(product,p);
    	this.doBodyRender();
    	}
}