package cn.aresoft.cms.portal.tag;
import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;

import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.cms.portal.service.ProductInfoService;
public class ProductListTag extends BaseTag {
	private ProductInfoService productInfoService = BeanFactory.getBean("productInfoService");
	@Override
	public void render() {
		String type = getAttr("type", "");//产品类型（1：自组网产品，2：有中心产品，3.自组网+有中心双网合一产品）
		String status = getAttr("status", "");//状态：1：待审核，2：审核通过
		String isHot = getAttr("isHot", "");//是否热推 1：是热推
		String name = getAttr("name", "");//产品名称
    	ProductInfo  p = new ProductInfo();
    	p.setType(type);
    	p.setStatus(status);
    	p.setIsHot(isHot);
    	p.setName(name);
    	PageRecord<ProductInfo>  datas=productInfoService.paging(p, getCommonParam());
    	this.binds(datas);
    	this.doBodyRender();
    	}
}