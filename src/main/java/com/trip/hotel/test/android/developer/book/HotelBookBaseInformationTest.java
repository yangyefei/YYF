package com.trip.hotel.test.android.developer.book;

import com.trip.hotel.test.android.developer.DriverUtils;
import com.trip.hotel.test.android.developer.Page;
import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.common.JsonUtils;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;

@Test
public class HotelBookBaseInformationTest extends BaseTest {
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver<WebElement> driver;

    @DataProvider
    public static Iterator<Object[]> getLanguageTestData() {
        return JsonUtils.readJson("book-language-test.json");
    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        logger.info("beforeClass()...");
        AndroidDriver<WebElement> driver = Page.createDrive();
        logger.info("初始化成功，准备登陆");
        appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        logger.info("beforeMethod()...");
        driver = Page.createDrive();
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("afterMethod()...");
        driver.quit();
    }

    /**
     * C1309755	中文站点填写英文；C1309754	非中文站点填写中文。
     */
    @Test(dataProvider = "getLanguageTestData")
    public void testEnglishInChineseSite(Map<String, String> data) throws InterruptedException {
        logger.info("testEnglishInChineseSite()...");
        String targetLanguage = data.get("targetLanguage");
        String expectedToast = data.get("expectedToast");
        String strGivenName = data.get("strGivenName");
        String strSurname = data.get("strSurname");
        testWrongLanguageInput(targetLanguage, expectedToast, strGivenName, strSurname);
    }

    /**
     * C1309759：联系人的姓名+电子邮件+电话号码（不输入时弹出提示）
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void testEmptyToast() throws InterruptedException {
        logger.info("testEmptyToast()...");
        appCommonService.changeLanguageTo(driver, "English");
        int cityId = 2;
        int hotelId = 436187;
        appCommonService.gotoHotelBook(driver, cityId, hotelId);

        WebElement bookButton = Page.HotelBook.findBookButton(driver);

        // 验证 Gaven Name
        WebElement gavenName = Page.HotelBook.findContactGavenName(driver);
        Assert.assertTrue(gavenName.isDisplayed());
        gavenName.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide guest name.");

        // 输入Gaven Name 验证 Surname
        gavenName.sendKeys("Lei");
        WebElement surname = Page.HotelBook.findContactSurname(driver);
        Assert.assertTrue(surname.isDisplayed());
        surname.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide guest name.");

        // 输入Surname 验证 Email
        surname.sendKeys("Lee");
        WebElement email = Page.HotelBook.findContactEmail(driver);
        Assert.assertTrue(email.isDisplayed());
        email.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide email address.");

        // 输入 email 验证电话号码
        email.sendKeys("test@test.com");
        WebElement phoneNumber = Page.HotelBook.findContactPhoneNumber(driver);
        Assert.assertTrue(phoneNumber.isDisplayed());
        phoneNumber.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide contact phone number.");
    }

    /**
     * C1309764Edit: 邮箱验证填写，正确邮箱地址，异常邮箱地址
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void testEmailFormat() throws InterruptedException {
        logger.info("testEmailFormat()...");
        appCommonService.changeLanguageTo(driver, "English");
        int cityId = 2;
        int hotelId = 436187;
        appCommonService.gotoHotelBook(driver, cityId, hotelId);


        WebElement bookButton = Page.HotelBook.findBookButton(driver);

        // 输入姓名，情况电话号码
        Page.HotelBook.findContactGavenName(driver).sendKeys("Lei");
        Page.HotelBook.findContactSurname(driver).sendKeys("Lee");
        Page.HotelBook.findContactPhoneNumber(driver).clear();

        WebElement email = Page.HotelBook.findContactEmail(driver);
        Assert.assertTrue(email.isDisplayed());

        email.clear();
        email.sendKeys("fsdfdfsdf");
        bookButton.click();
        DriverUtils.assertToast(driver, "Email address invalid, please try again.");

        email.clear();
        email.sendKeys("test@test.com");
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide contact phone number.");
    }

    private void testWrongLanguageInput(String targetLanguage, String expectedToast, String strGivenName, String strSurname) throws InterruptedException {
        appCommonService.changeLanguageTo(driver, targetLanguage);
        logger.info("通过DeepLink跳转到酒店详情");
        int cityId = 2;
        int hotelId = 436187;
        appCommonService.gotoHotelBook(driver, cityId, hotelId);

        WebElement gavenName = Page.HotelBook.findContactGavenName(driver);
        Assert.assertTrue(gavenName.isDisplayed());
        gavenName.clear();
        gavenName.sendKeys(strGivenName);
        Assert.assertEquals(gavenName.getText(), strGivenName);

        WebElement surname = Page.HotelBook.findContactSurname(driver);
        Assert.assertTrue(surname.isDisplayed());
        surname.clear();
        surname.sendKeys(strSurname);
        Assert.assertEquals(surname.getText(), strSurname);

        WebElement phoneNumber = Page.HotelBook.findContactPhoneNumber(driver);
        Assert.assertTrue(phoneNumber.isDisplayed());
        // 清空电话号码，防止创单成功，以便通过Toast信息判断
        phoneNumber.clear();

        Page.HotelBook.findBookButton(driver).click();

        DriverUtils.assertToast(driver, expectedToast);
    }
}
