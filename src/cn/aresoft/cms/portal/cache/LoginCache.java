package cn.aresoft.cms.portal.cache;

import com.puff.framework.annotation.Bean;

import cn.aresoft.cache.AbstractCache;

@Bean(id = "loginCache")
public class LoginCache extends AbstractCache {

	@Override
	public String region() {
		return "LoginSign";
	}

	public int addLoginFailCount(String username) {
		int count = 0;
		Object object = getCacheClient().get("LOGIN_FAILCOUNT", username);
		if (object != null) {
			count = (Integer) object;
		}
		getCacheClient().put("LOGIN_FAILCOUNT", username, ++count, 60);
		return count;
	}

}
