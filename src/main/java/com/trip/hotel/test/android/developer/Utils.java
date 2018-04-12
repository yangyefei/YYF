package com.trip.hotel.test.android.developer;

import org.openqa.selenium.By;

public class Utils {
    public static String getIdString(By.ById byId) {
        return byId.toString().substring("By.id: ".length());
    }
}
