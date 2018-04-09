package com.trip.hotel.test.android.book;

import com.trip.hotel.test.android.DriverUtils;
import com.trip.hotel.test.android.Page;
import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import java.net.MalformedURLException;

@Test
public class HotelBookBaseInformationTest extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver<WebElement> driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        driver = initial.appiumAndroidCtripSetUp(null, "ctrip.english");
        logger.info("初始化成功，准备登陆");
        appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
    }

    /**
     * C1309754	非中文站点填写中文
     */
    @Test
    public void testChineseInNonChineseSite() {
        String targetLanguage = "English";
        appCommonService.changeLanguageTo(driver, targetLanguage);
        // 通过DeepLink跳转到酒店详情
        driver.get("ctripglobal://HotelDetail?ct=2&hid=436187&cin=2015-10-01&cout=2015-10-04&td=2");
        // 找到Book按钮
        WebElement buttonBook = DriverUtils.scrollFind(driver, Page.HotelDetails.RoomsList.BOOK_BUTTON);
        Assert.assertNotNull(buttonBook);
        Assert.assertTrue(buttonBook.isDisplayed());
        // 点击Book按钮
        buttonBook.click();
    }

}
