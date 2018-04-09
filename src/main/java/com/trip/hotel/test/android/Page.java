package com.trip.hotel.test.android;

import org.openqa.selenium.By;

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
}
