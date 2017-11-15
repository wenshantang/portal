package cn.aresoft.cms.portal.service;
import java.util.List;

import cn.aresoft.cms.portal.model.ProductInfo;
import cn.aresoft.common.service.BaseService;
public interface ProductInfoService extends BaseService<ProductInfo>{
	/**
	 * 根据产品code值查询产品
	 * @param code 产品code
	 * @return
	 */
	ProductInfo queryByCode(String code);
	
	/**
	 * 查询所有的产品code 和 产品name
	 * @return
	 */
	List<ProductInfo> products();
}
