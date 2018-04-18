package com.trip.hotel.test.android.developer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TouchUtils {
    private static final Log logger = LogFactory.getLog(TouchUtils.class);

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


    public static void swipeToCenterY(AppiumDriver<WebElement> driver, WebElement buttonBook) {
        Rectangle buttonRect = buttonBook.getRect();
        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;

        int moveY = size.height / 2 - buttonRect.y;
        int pressY = size.height / 2 - moveY / 2;

        TouchAction action = new TouchAction(driver);
        action.press(centerX, pressY);
        logger.info("press [" + centerX + ", " + pressY + "]");
        // 居然是相对距离
        action.moveTo(0, moveY);
        logger.info("moveTo [" + 0 + ", " + moveY + "]");
        action.cancel();
    }
}
