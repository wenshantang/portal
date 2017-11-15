package cn.aresoft.cms.portal.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.GeneralVarTagBinding;

import cn.aresoft.cms.portal.cache.LoginCache;
import cn.aresoft.cms.portal.cache.UserCache;
import cn.aresoft.common.model.CommonParam;

import com.puff.framework.utils.StringUtil;
import com.puff.ioc.BeanFactory;
import com.puff.jdbc.core.PageRecord;
import com.puff.web.mvc.HttpCookie;

public abstract class BaseTag extends GeneralVarTagBinding {

	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_ROWS = 10;
	protected UserCache userCache = BeanFactory.getBean("userCache");
	protected LoginCache loginCache = BeanFactory.getBean("loginCache");
	protected String getAttr(String key) {
		return (String) this.getAttributeValue(key);
	}

	protected String getAttr(String key, String defVal) {
		String val = getAttr(key);
		if (StringUtil.empty(val)) {
			return defVal;
		}
		return val;
	}

	protected int getIntAttr(String key) {
		return Integer.parseInt(getAttr(key));
	}

	protected int getIntAttr(String key, int defVal) {
		String val = getAttr(key);
		if (StringUtil.empty(val)) {
			return defVal;
		}
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return defVal;
		}
	}
	protected String getCust_id() {
		String login_sign = findCookieValue("login_sign");
		String mobile = loginCache.get(login_sign);
		if(StringUtil.empty(mobile)){
			String openid = (String)getRequest().getSession().getAttribute("openId");
			mobile = loginCache.get(openid);

		}
//		 User user = userCache.get(mobile);
//		 if(user==null){
//			 return null;
//		 }
		return null;//user.getCust_id();
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) ctx.getGlobal("request");
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) ctx.getGlobal("response");
	}

	protected String findCookieValue(String name) {
		return HttpCookie.findCookieValue(getRequest(), name);
	}

	public <T> PageRecord<T> getPageRecord() {
		PageRecord<T> record = new PageRecord<T>();
		record.setPage(getIntAttr("page", DEFAULT_PAGE));
		record.setPageSize(getIntAttr("rows", DEFAULT_ROWS));
		return record;
	}

	public CommonParam getCommonParam() {
		CommonParam param = new CommonParam();
		param.setRows(getIntAttr("rows", DEFAULT_ROWS));
		param.setPage(getIntAttr("page", DEFAULT_PAGE));
		param.setSort(getAttr("sort"));
		param.setOrder(getAttr("order"));
		return param;
	}
	public int getPage() {
		return getIntAttr("page", DEFAULT_PAGE);
	}

	public int getRows() {
		return getIntAttr("rows", DEFAULT_ROWS);
	}
	public int getTotalPage(int totalCount,int pageSize,int page){
		int totalPage;
		if (totalCount % pageSize == 0) {
			 totalPage = totalCount / pageSize;
		} else {
			 totalPage = totalCount / pageSize + 1;
		}
		if (page > totalPage) {
			page = totalPage < 1 ? 1 : totalPage;
		}
		return  totalPage;
	}

}
