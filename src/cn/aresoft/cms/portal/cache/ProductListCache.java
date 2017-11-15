package cn.aresoft.cms.portal.cache;

import com.puff.framework.annotation.Bean;

import cn.aresoft.cache.AbstractCache;

@Bean(id = "productListCache")
public class ProductListCache extends AbstractCache {
	@Override
	public String region() {
		return "tb_product_info";
	}
}
