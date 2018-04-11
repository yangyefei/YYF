package com.trip.hotel.test.android;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Page {
    private static final String APP_PACKAGE_NAME = "ctrip.english";
    private static final String APPIUM_HOST = "127.0.0.1";
    private static final int APPIUM_PORT = 4723;
    private static final String APP_MAIN_ACTIVITY = "com.ctrip.ibu.myctrip.main.module.home.IBUHomeActivity";

    public static AndroidDriver<WebElement> createDrive() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        // capabilities.setCapability(Mobi, "7.1");
        // capabilities.setCapability("deviceName", "emulator-5554");
        // capabilities.setCapability("deviceName","device");
        // capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("automationName", "UiAutomator2");
//		capabilities.setCapability("udid", "127.0.0.1:62025");
        capabilities.setCapability("newCommandTimeout", "10");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
        capabilities.setCapability("autoAcceptAlerts", "True");
        capabilities.setCapability("noReset", true);
        // capabilities.setCapability("udid",
        // "emulator-5554");//如果要远程调用模拟器，这个参数必须要有
        // capabilities.setCapability("app", app.getAbsolutePath());
        // capabilities.setCapability("app", apkPath);
        capabilities.setCapability("appPackage", APP_PACKAGE_NAME);
        capabilities.setCapability("appActivity", APP_MAIN_ACTIVITY);
        capabilities.setCapability("noSign", "True");

        return new AndroidDriver<>(new URL(String.format("http://%1$s:%2$d/wd/hub", APPIUM_HOST, APPIUM_PORT)), capabilities);
    }

    /**
     * App 首页
     */
    public static class AppHome {
        // 首页的Account按钮
        public static final By ACCOUNT = By.id("myctrip_home_bottom_account_icon");
    }

    /**
     * Account页面
     */
    public static class Account {
        // Account 中的设置按钮
        public static final By SETTING = By.id("ll_settings");

        // Account 中的设置页面
        public static class Setting {
            // 设置页面中的选择语言列表中的语言名称
            public static final By LANGUAGE_LIST_LANGUAGE_NAME = By.id("ibu_baseview_language_item_name");
        }
    }

    public static class HotelDetails {
        public static class RoomsList {
            public static final By.ById BOOK_BUTTON = new By.ById("hotel_rooms_list_sub_room_btn_book");
        }
    }

    public static class HotelBook {
        public static final By.ById CONTACT_GAVEN_NAME_CONTAINER = new By.ById("hotel_book_contact_givenname_input");
        public static final By.ById CONTACT_GAVEN_NAME = new By.ById("view_edit_text");
        public static final By.ById CONTACT_SURNAME_CONTAINER = new By.ById("hotel_book_contact_surname_input");
        public static final By.ById CONTACT_SURNAME = new By.ById("view_edit_text");
        public static final By.ById CONTACT_EMAIL_CONTAINER = new By.ById("hotel_book_contact_email_input");
        public static final By.ById CONTACT_EMAIL = new By.ById("view_edit_text");
        public static final By.ById CONTACT_PHONE_NUMBER = new By.ById("hotel_book_contact_phone_input");
        public static final By.ById BOOK_BUTTON = new By.ById("tv_bottom_select");

        public static WebElement findBookButton(AndroidDriver<WebElement> driver) {
            return DriverUtils.waitFind(driver, Page.HotelBook.BOOK_BUTTON);
        }

        public static WebElement findContactGavenName(AndroidDriver<WebElement> driver) {
            return DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_GAVEN_NAME_CONTAINER).findElement(Page.HotelBook.CONTACT_GAVEN_NAME);
        }

        public static WebElement findContactSurname(AndroidDriver<WebElement> driver) {
            return DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_SURNAME_CONTAINER).findElement(Page.HotelBook.CONTACT_SURNAME);
        }

        public static WebElement findContactPhoneNumber(AndroidDriver<WebElement> driver) {
            return DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_PHONE_NUMBER);
        }

        public static WebElement findContactEmail(AndroidDriver<WebElement> driver) {
            return DriverUtils.waitFind(driver, Page.HotelBook.CONTACT_EMAIL_CONTAINER).findElement(Page.HotelBook.CONTACT_EMAIL);
        }
    }
}
