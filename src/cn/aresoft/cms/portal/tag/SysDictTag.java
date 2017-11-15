package cn.aresoft.cms.portal.tag;

import java.util.List;

import com.puff.framework.utils.StringUtil;
import com.puff.ioc.BeanFactory;

import cn.aresoft.cms.common.cache.CmsPropertiesCache;
import cn.aresoft.cms.common.model.CmsProperties;

public class SysDictTag extends BaseTag {
	private CmsPropertiesCache cmsPropertiesCache = BeanFactory.getBean("cmsPropertiesCache");

	@Override
	public void render() {
		String type = getAttr("type", "");
		String name = getAttr("name", "");
		if(StringUtil.notEmpty(name)){
			CmsProperties dict = cmsPropertiesCache.findPropertiesFormCache(type, name);
			this.binds(dict);
		}else{
			List<CmsProperties> listDict = cmsPropertiesCache.findListFormCache(type);
			this.binds(listDict);
		}
		this.doBodyRender();
	}

}
