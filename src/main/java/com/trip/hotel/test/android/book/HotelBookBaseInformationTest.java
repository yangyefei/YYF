package com.trip.hotel.test.android.book;

import com.trip.hotel.test.android.DriverUtils;
import com.trip.hotel.test.android.Page;
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

    /**
     * C1309759：联系人的姓名+电子邮件+电话号码（不输入时弹出提示）
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void testEmptyToast() throws InterruptedException {
        appCommonService.changeLanguageTo(driver, "English");
        int cityId = 2;
        int hotelId = 436187;
        appCommonService.gotoHotelBook(driver, cityId, hotelId);

        WebElement bookButton = DriverUtils.waitFind(driver, Page.HotelBook.BOOK_BUTTON);

        // 验证 Gaven Name
        WebElement gavenName = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_GAVEN_NAME_CONTAINER).findElement(Page.HotelBook.CONTACT_GAVEN_NAME);
        Assert.assertTrue(gavenName.isDisplayed());
        gavenName.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide guest name.");

        // 输入Gaven Name 验证 Surname
        gavenName.sendKeys("Lei");
        WebElement surname = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_SURNAME_CONTAINER).findElement(Page.HotelBook.CONTACT_SURNAME);
        Assert.assertTrue(surname.isDisplayed());
        surname.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide guest name.");

        // 输入Surname 验证 Email
        surname.sendKeys("Lee");
        WebElement email = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_EMAIL_CONTAINER).findElement(Page.HotelBook.CONTACT_EMAIL);
        Assert.assertTrue(email.isDisplayed());
        email.clear();
        bookButton.click();
        DriverUtils.assertToast(driver, "Please provide email address.");

        // 输入 email 验证电话号码
        email.sendKeys("test@test.com");
        WebElement phoneNumber = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_PHONE_NUMBER);
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
        appCommonService.changeLanguageTo(driver, "English");
        int cityId = 2;
        int hotelId = 436187;
        appCommonService.gotoHotelBook(driver, cityId, hotelId);

        WebElement bookButton = DriverUtils.waitFind(driver, Page.HotelBook.BOOK_BUTTON);

        // 输入姓名，情况电话号码
        DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_GAVEN_NAME_CONTAINER).findElement(Page.HotelBook.CONTACT_GAVEN_NAME).sendKeys("Lei");
        DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_SURNAME_CONTAINER).findElement(Page.HotelBook.CONTACT_SURNAME).sendKeys("Lee");
        DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_PHONE_NUMBER).clear();

        WebElement email = DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_EMAIL_CONTAINER).findElement(Page.HotelBook.CONTACT_EMAIL);
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

        DriverUtils.assertToast(driver, expectedToast);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
