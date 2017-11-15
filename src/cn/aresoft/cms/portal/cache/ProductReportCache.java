package cn.aresoft.cms.portal.cache;

import com.puff.framework.annotation.Bean;

import cn.aresoft.cache.AbstractCache;

@Bean(id = "productReportCache")
public class ProductReportCache extends AbstractCache {

	@Override
	public String region() {
		return "tb_product_report";
	}
}
