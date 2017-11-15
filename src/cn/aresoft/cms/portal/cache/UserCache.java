package cn.aresoft.cms.portal.cache;

import cn.aresoft.cache.AbstractCache;

import com.puff.framework.annotation.Bean;

@Bean(id = "userCache")
public class UserCache extends AbstractCache {
	@Override
	public String region() {
		return "EC_U_BASIC";
	}

}
