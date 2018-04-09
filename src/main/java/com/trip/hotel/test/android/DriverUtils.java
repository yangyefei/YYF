package com.trip.hotel.test.android;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
    private static final long TIMEOUT = 30;

    public static void waitClickId(AndroidDriver driver, By by) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by)).click();
    }
}
