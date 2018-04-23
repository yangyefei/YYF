package com.trip.hotel.test.android.developer;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class DriverUtils {
    private static final long TIMEOUT = 30;
    private static final Log logger = LogFactory.getLog(DriverUtils.class);

    public static void waitClick(AndroidDriver driver, By by) {
        waitFind(driver, by).click();
    }

    public static WebElement waitFind(AndroidDriver driver, By by) {
        return new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by));
    }

    private static WebElement waitToast(AndroidDriver driver, String toastText) {
        String xpath = String.format("//*[@text='%1$s']", toastText);
        logger.info("waitToast(...) xpath = " + xpath);
        return new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static void assertToast(AndroidDriver driver, String toastText) {
        WebElement element = waitToast(driver, toastText);
        Assert.assertNotNull(element);
    }

    /**
     * 滑动找View
     *
     * @param driver       Android Driver
     * @param targetViewId 要寻找的View
     * @return
     */
    public static WebElement scrollFind(AndroidDriver<WebElement> driver, By.ById targetViewId) {
        String scrollViewContainerSelector = "new UiSelector().scrollable(true)";
        String strTargetViewId = Utils.getIdString(targetViewId);
        String elementSelector = "new UiSelector().resourceIdMatches(\".*id/" + strTargetViewId + "\")";
        String uiAutomator = String.format("new UiScrollable(%1$s).scrollIntoView(%2$s)", scrollViewContainerSelector, elementSelector);
        logger.info("scrollFind(...) uiAutomator = " + uiAutomator);
        List<WebElement> elements = driver.findElementsByAndroidUIAutomator(uiAutomator);
        for (WebElement element : elements) {
            Rectangle rect = element.getRect();
            logger.info(String.format("element: [x: %1$d, y: %2$d], [width: %3$d, height: %4$d]", rect.x, rect.y, rect.width, rect.height));
        }
        return elements.get(0);
    }
}
