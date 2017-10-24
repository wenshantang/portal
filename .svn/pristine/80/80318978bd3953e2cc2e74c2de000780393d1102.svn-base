package cn.aresoft.cms.portal.fhcf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

	public static String formatString(String datestring){
		String result = "";
	    StringBuffer data = new StringBuffer(datestring);
	    data.insert(4, "年");
	    data.insert(7, "月");
	    data.insert(data.length(), "日");
	    result = data.substring(5,data.length());
	    return result;
	  }
	public static String formatTimeString(String datestring){
		String result = "";
	    StringBuffer data = new StringBuffer(datestring);
	    data.insert(4, "年");
	    data.insert(7, "月");
	    data.insert(data.length(), "日");
	    result = data.substring(0,data.length());
	    return result;
	  }
	/**
	 * 当前时间 MM月dd日 hh:mm
	 * @return
	 */
	public static String nowTime(){
		Date date = new Date();
		DateFormat  df = new SimpleDateFormat("MM月dd日 HH:mm");
		return df.format(date);
	}
	/**
	 *yyyy年mm月dd日 
	 */
	public static String formatString(){
		Date date = new Date();
		DateFormat  df = new SimpleDateFormat("yyyy年MM月dd日");
	    return df.format(date);
	  }
	/**
	 *yyyy-mm-dd 
	 */
	public static String formatTimeString(){
		Date date = new Date();
		DateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
	    return df.format(date);
	  }
	/**
	 *yyyy-MM-dd hh:mm:ss
	 */
	public static String formatTimeStringTwo(){
		Date date = new Date();
		DateFormat  df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return df.format(date);
	  }
	
	/**
	 * 当前时间 HH
	 * @return
	 */
	public static int nowTimeHH(){
		Date date = new Date();
		DateFormat  df = new SimpleDateFormat("HH");
		int hour = Integer.valueOf(df.format(date));
		return hour;
	}
	public static void main(String[] args) {
		System.out.println(nowTimeHH());
	}
}