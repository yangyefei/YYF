package com.trip.hotel.test.android.qa;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.AppCommonService;
import com.trip.hotel.test.service.InitialService;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class SearchForDomesticAttractions extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;
    int timeOutInSeconds = 60;
    Logger logger = Logger.getLogger("searchForDomesticAttractions.class");

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        driver = initial.createAndroidReleaseDriver();
    }

    @Test(description = "by lnn: 搜索国内区域景点C1309606", groups = {"Base"})
    public void searchForDomesticAttractions() throws Exception {
        String attractionText = null;
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
                .sendKeys("qiandaohu");
        logger.info("点击第一条搜出的景点千岛湖 ");
        ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
        try {
            attractionText = destinationlist.get(0).getText();
            logger.info(attractionText);
            Thread.sleep(3000);
            Assert.assertEquals("千島湖", attractionText);
            logger.info("C1309606:成功找到景点千岛湖");
        } catch (Exception e) {
            logger.info("C1309606:没有找到景点千岛湖");
        }

        try {
            destinationlist.get(0).click();
            Thread.sleep(3000);
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
            logger.info("成功跳转到列表页");
        } catch (Exception e) {
            logger.info("跳转到列表页失败");
        }

    }

    @AfterClass
    public void afterClass() {
        logger.info("I here afterclass");
        driver.quit();
    }

}
