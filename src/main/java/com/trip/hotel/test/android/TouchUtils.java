package com.trip.hotel.test.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TouchUtils {

    public static void swipe(AppiumDriver<WebElement> driver, int startX, int startY, int endX, int endY) {
        swipe(driver, startX, startY, endX, endY, -1);
    }

    public static void swipe(AppiumDriver<WebElement> driver, int startX, int startY, int endX, int endY, int duration) {
        TouchAction action = new TouchAction(driver);
        if (duration >= 0) {
            action.waitAction(Duration.ofMillis(duration));
        }
        action.press(startX, startY)
                .moveTo(endX, endY)
                .cancel();
    }

    public static void tap(AppiumDriver<WebElement> driver, int startX, int startY, int duration) {
        TouchAction action = new TouchAction(driver).waitAction(Duration.ofMillis(duration));
        action.tap(startX, startY).perform();
    }


    public static void swipeDown(AppiumDriver<WebElement> driver) {
        Dimension size = driver.manage().window().getSize();
        TouchAction action = new TouchAction(driver);
        action.press(size.width / 2, size.height / 4);
        action.moveTo(size.width / 2, size.height / 2);
        action.cancel();
    }
}
