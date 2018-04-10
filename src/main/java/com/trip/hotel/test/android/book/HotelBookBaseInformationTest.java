package com.trip.hotel.test.android.book;

import com.trip.hotel.test.android.DriverUtils;
import com.trip.hotel.test.android.Page;
import com.trip.hotel.test.android.TouchUtils;
import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
    public void testChineseInNonChineseSite() throws InterruptedException {
        String targetLanguage = "English";
        String expectedToast = "English Only";
        String strGivenName = "雷";
        String strSurname = "李";
        testWrongLanguageInput(targetLanguage, expectedToast, strGivenName, strSurname);
    }

    /**
     * C1309755	中文站点填写英文
     */
    @Test
    public void testEnglishInChineseSite() throws InterruptedException {
        String targetLanguage = "繁體中文";
        String expectedToast = "請提供聯絡電話號碼。"; //提示这个Toast说明填写英文可以通过
        String strGivenName = "Lei";
        String strSurname = "Lee";
        testWrongLanguageInput(targetLanguage, expectedToast, strGivenName, strSurname);
    }

    private void testWrongLanguageInput(String targetLanguage, String expectedToast, String strGivenName, String strSurname) throws InterruptedException {
        appCommonService.changeLanguageTo(driver, targetLanguage);
        logger.info("通过DeepLink跳转到酒店详情");
        driver.get("ctripglobal://HotelDetail?ct=2&hid=436187&cin=2015-10-01&cout=2015-10-04&td=2");
        logger.info("找Book按钮");
        WebElement buttonBook = DriverUtils.scrollFind(driver, Page.HotelDetails.RoomsList.BOOK_BUTTON);
        Assert.assertNotNull(buttonBook);
        Assert.assertTrue(buttonBook.isDisplayed());
        TouchUtils.swipeDown(driver, buttonBook);
        logger.info("点击Book按钮");
        // 等Toast消失，防止被Toast View 盖住
        Thread.sleep(4000);
        buttonBook.click();
        Thread.sleep(1000);
        String currentActivity = driver.currentActivity();
        logger.debug("currentActivity = " + currentActivity);
        logger.info("判断到了预订页面");
        Assert.assertEquals(currentActivity, "com.ctrip.ibu.hotel.module.book.HotelBookActivity");

        WebElement gavenName = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_GAVEN_NAME_CONTAINER).findElement(Page.HotelBook.CONTACT_GAVEN_NAME);
        Assert.assertTrue(gavenName.isDisplayed());
        gavenName.clear();
        gavenName.sendKeys(strGivenName);
        Assert.assertEquals(gavenName.getText(), strGivenName);

        WebElement surname = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_SURNAME_CONTAINER).findElement(Page.HotelBook.CONTACT_SURNAME);
        Assert.assertTrue(surname.isDisplayed());
        surname.clear();
        surname.sendKeys(strSurname);
        Assert.assertEquals(surname.getText(), strSurname);

        WebElement phoneNumber = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_PHONE_NUMBER);
        Assert.assertTrue(phoneNumber.isDisplayed());
        // 清空电话号码，防止创单成功，以便通过Toast信息判断
        phoneNumber.clear();

        driver.findElement(Page.HotelBook.BOOK_BUTTON).click();

        WebElement toast = DriverUtils.waitToast(driver, expectedToast);
        Assert.assertNotNull(toast);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
