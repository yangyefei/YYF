package com.trip.hotel.test.android.developer;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Page {
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
