package cn.aresoft.cms.portal.tag;
import cn.aresoft.cms.portal.cache.ProductListCache;
import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.cms.portal.service.ProductInfoService;
import com.puff.ioc.BeanFactory;
public class ProductInfoTag extends BaseTag {
	private ProductInfoService productInfoService = BeanFactory.getBean("productInfoService");
	private ProductListCache productListCache = BeanFactory.getBean("productListCache");
	@Override
	public void render() {
		String pro_code = getAttr("pro_code","");//基金代码
        ProductInfo product  = productListCache.get("productinfo_"+pro_code);
        if(product==null){
        	product=productInfoService.queryByCode(pro_code);
        	productListCache.cache("productinfo_"+pro_code, product);
        }
    	this.binds(product);
    	this.doBodyRender();
    }
}