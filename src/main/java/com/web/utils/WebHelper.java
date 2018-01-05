package com.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WebHelper {
/**
 * 获取当前年月日
 * @return String : yyyy-mm-dd
 */
	public  static String getTime(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd");
		return dFormat.format(calendar.getTime());
	}
}
