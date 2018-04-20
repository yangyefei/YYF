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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

public class FullRoomRate extends BaseTest {
    private InitialService initial = new InitialServiceImpl();
    private AppCommonService appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver;
    int timeOutInSeconds = 60;
    Logger logger = Logger.getLogger("districtFullRoomRate.class");

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        driver = initial.createAndroidReleaseDriver();
    }

    @Test(description = "by lnn: 测试区域满房度C1306883/C1315188", groups = {"Base"})
    public void districtFullRoomRate() throws Exception {
        WebElement districtFullRoomRateText = null;
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
                .sendKeys("徐汇区");
        logger.info("tvTitle 属性遍历存入到集合中，然后取出第一个点击 ");
        Thread.sleep(3000);
        ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
        for (WebElement webElement : destinationlist) {

        }
        destinationlist.get(0).click();
        Thread.sleep(3000);
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
        try {
            districtFullRoomRateText = driver.findElement(By.id("tv_tip_full_house"));
            Assert.assertTrue(districtFullRoomRateText.isDisplayed());
            logger.info("C1306883:验证首页搜索行政区+满房度提示话术成功");
        } catch (Exception e) {
            logger.info("C1306883:首页搜索行政区+满房度提示话术验证失败");
        }

        try {
            driver.findElement(By.id("iv_delete_full_house")).click();
            logger.info("C1315188:验证删除满房度提示话术成功");
        } catch (Exception e) {
            logger.info("C1315188:删除满房度提示话术验证失败");
        }
    }

    @DataProvider(name = "testData")
    public Iterator<Object[]> data1test() throws IOException {
        return ExcelProviderByEnv(this, "testData");
    }

    @AfterClass
    public void afterClass() {
        logger.info("I here afterclass");
        driver.quit();
    }

}
