package cn.aresoft.cms.portal.fhcf.util;


import java.io.UnsupportedEncodingException;

public class StringUtil {

	/**
	 * 字符串为 null 或者为 "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}

	public static boolean hasBlank(String... strings) {
		if (strings == null)
			return true;
		for (String str : strings)
			if (isBlank(str))
				return true;
		return false;
	}

	/**
	 * checkValue为 null 或者为 "" 时返回 defaultValue
	 */
	public static String isBlank(String checkValue, String defaultValue) {
		return isBlank(checkValue) ? defaultValue : checkValue;
	}

	/**
	 * 字符串不为 null 而且不为 "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str == null || "".equals(str.trim()) ? false : true;
	}

	/**
	 * 字符串不为 null 而且不为 "" 并且等于other
	 */
	public static boolean notBlankAndEqOther(String str, String other) {
		if (isBlank(str))
			return false;
		return str.equals(other);
	}

	/**
	 * 字符串不为 null 而且不为 "" 并且不等于other
	 */
	public static boolean notBlankAndNotEqOther(String str, String... other) {
		if (isBlank(str))
			return false;
		for (int i = 0; i < other.length; i++) {
			if (str.equals(other[i]))
				return false;
		}
		return true;
	}

	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}
	
	public static String setField(String buffer, short sFieldLen) {
		try {
			int iFieldLen = sFieldLen;
			byte dest[] = new byte[iFieldLen];
			byte src[] = buffer.getBytes("GBK");
			for (int i = 0; i < iFieldLen; i++)
				if (i < src.length)
					dest[i] = src[i];
				else
					dest[i] = 32;

			String field = new String(dest, "GBK");
			return field;
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
