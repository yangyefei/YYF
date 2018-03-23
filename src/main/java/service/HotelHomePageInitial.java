package service;

import io.appium.java_client.AppiumDriver;

public interface HotelHomePageInitial {
	
	/**
	 * 初始化酒店首页星级筛选
	 * @param driver
	 * @return
	 */
	public AppiumDriver HotelPageStarsFilter(AppiumDriver driver); 
	
	/**
	 * 初始化酒店首页成人儿童筛选
	 * @param driver
	 * @return
	 */
	public AppiumDriver HotelPageAdultsChildFilter(AppiumDriver driver); 

}
