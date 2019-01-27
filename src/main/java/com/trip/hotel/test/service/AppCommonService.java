package com.trip.hotel.test.service;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;


public interface AppCommonService {

    /**
     * app登录
     *
     * @param driver
     * @param userName
     * @param userPassWord
     * @return
     */
    public AppiumDriver loginForApp(AppiumDriver driver, String userName, String userPassWord);

    /**
     * 退出app,无需确认是否已登录
     *
     * @param driver
     * @return AppiumDriver
     */
    public AppiumDriver logoutForApp(AppiumDriver driver);


    /**
     * 评论并且提交
     *
     * @param driver
     * @param comment
     * @return
     */

    /**
     * 滚动并查找要找的内容
     *
     * @param driver
     * @param searchName
     * @param nameId
     * @return
     */
    public AppiumDriver scrollAndFindName(AppiumDriver driver, String searchName, String nameId, String totalNum);

    /*
     * 酒店首页搜索
     * @param driver
     * @param keyword
     * */
    public AppiumDriver homeSearchHotel(AppiumDriver driver, String keyword) throws InterruptedException;


    void changeLanguageTo(AndroidDriver<WebElement> driver, String targetLanguage);

    void gotoHotelBook(AndroidDriver<WebElement> driver, int cityId, int hotelId) throws InterruptedException;
}
