package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListWithOtherPage extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;
    int timeOutInSeconds = 60;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        driver = initial.createAndroidReleaseDriver();
    }

    @Test(description = "by sxm: C1309727	包早餐，免费取消，2张床，1张床，及时确认等", groups = {"Base"})
    public void listWithOther() throws Exception {
        logger.info("---搜索上海思南公館酒店---");
        appCommonService.homeSearchHotel(driver, "上海思南公館酒店");
        WebElement filters_quick = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("hrv_filter_quick")));
        List<WebElement> filters = filters_quick.findElements(By.className("android.widget.CheckedTextView"));
        String cancell = filters.get(1).getText();
        filters.get(1).click();
        logger.info("---点击:" + cancell);
        String confirm = filters.get(4).getText();
        filters.get(4).click();
        logger.info("---点击:" + confirm);
        try {
            logger.info("---验证筛选联动---");
            WebElement list = driver.findElement(By.id("top_bar_filter_red_dot"));
            assertTrue(list.isDisplayed());
            driver.findElement(By.id("ll_map")).click();
            WebElement maplist = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_hotel_list")));
            WebElement mapred = driver.findElement(By.id("top_bar_filter_red_dot"));
            assertTrue(mapred.isDisplayed());
            maplist.click();
            new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("view_hotels_item_container"))).click();
            WebElement filter = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("fbl_filter_content")));
            List<WebElement> content = filter.findElements(By.className("android.widget.CheckedTextView"));
            String detailcancell = content.get(0).getText();
            String detailconfirm = content.get(1).getText();
            assertTrue(detailcancell.equals(cancell));
            assertTrue(confirm.equals(detailconfirm));
            logger.info("---验证C1309727	包早餐，免费取消，2张床，1张床，及时确认等Pass---");
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("---验证C1309727	包早餐，免费取消，2张床，1张床，及时确认等Fail---");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
