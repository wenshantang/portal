package cn.aresoft.cms.portal.fhcf.util;

import java.util.Random;

import com.puff.framework.utils.StringUtil;
import com.puff.web.mvc.PuffContext;

public class WebUtil {
	private static String[] deviceArray = new String[] { "android", "iphone", "windows phone", "symbianos", "ipad", "ipod" };
	private static char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static final Random random = new Random();

	public static boolean isAjax() {
		String requestType = PuffContext.getRequest().getHeader("X-Requested-With");
		return "XMLHttpRequest".equalsIgnoreCase(requestType);
	}

	public static String getRandom(int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	public static boolean isMobileDevice(String useragent) {
		if (StringUtil.notEmpty(useragent)) {
			useragent = useragent.toLowerCase();
		} else {
			return false;
		}
		for (int i = 0; i < deviceArray.length; i++) {
			if (useragent.indexOf(deviceArray[i]) > 0) {
				return true;
			}
		}
		return false;
	}
}
