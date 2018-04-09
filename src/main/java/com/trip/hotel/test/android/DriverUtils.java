package com.trip.hotel.test.android;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
    private static final long TIMEOUT = 30;
    private static final Log logger = LogFactory.getLog(DriverUtils.class);

    public static void waitClickId(AndroidDriver driver, By by) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    /**
     * 滑动找View
     *
     * @param driver       Android Driver
     * @param targetViewId 要寻找的View
     * @return
     */
    public static WebElement scrollFind(AndroidDriver driver, By.ById targetViewId) {
        String scrollViewContainerSelector = "new UiSelector().scrollable(true).instance(1)";

        String strTargetViewId = Utils.getIdString(targetViewId);
        String elementSelector = "new UiSelector().resourceIdMatches(\".*id/" + strTargetViewId + "\")";
        String uiAutomator = String.format("new UiScrollable(%1$s).scrollIntoView(%2$s)", scrollViewContainerSelector, elementSelector);
        logger.debug("scrollFind(...) uiAutomator = " + uiAutomator);
        return driver.findElementByAndroidUIAutomator(uiAutomator);
    }
}
