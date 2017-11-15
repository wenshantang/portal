package cn.aresoft.cms.portal.tag;
import cn.aresoft.cms.portal.cache.ProductListCache;
import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.cms.portal.service.ProductInfoService;

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
        	 p=productListCache.get("productinfo_"+product.getCode());
        	if(p==null){
        		if(StringUtil.notEmpty(product.getCode())){
        			p=productInfoService.queryByCode(product.getCode());
        			productListCache.cache("productinfo_"+product.getCode(), p);
        		}else{
        			p = new ProductInfo();
        		}
        	}
        }
    	this.binds(product,p);
    	this.doBodyRender();
    	}
}