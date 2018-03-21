package test.apptest.hotel;

import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RoomDisplay extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService  appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver ;
    int timeOutInSeconds = 60;
    
    @BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}
    
    @Test(description = "by sxm: C1309740	详情页酒店政策下方外露优质点评", groups = { "Base" })
    public void reviews() throws Exception{
    	logger.info("---搜索上海思南公館酒店---");
    	appCommonService.homeSearchHotel(driver, "上海思南公館酒店");
    	appCommonService.listToDetail(driver);
    	appCommonService.swipeToDown(driver, 1000, 2);
    	try {
			logger.info("---开始验证C1309740	详情页酒店政策下方外露优质点评---");
			WebElement comment = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_detail_foot_comment")));
			String content = comment.findElement(By.id("view_hotel_detail_foot_comment_content")).getText();
			assertFalse(content.isEmpty());
			logger.info("---验证C1309740	详情页酒店政策下方外露优质点评Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309740	详情页酒店政策下方外露优质点评Fail---");
		}
    }
    
    @Test(description = "By sxm: C1309739	【房型弹层】功能", groups = { "Base" })
    public void RoomShell() throws Exception{
    	logger.info("---搜索上海思南公館酒店---");
    	appCommonService.homeSearchHotel(driver, "上海思南公館酒店");
    	appCommonService.listToDetail(driver);
    	logger.info("---点击房型弹层---");
    	new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("hotel_rooms_list_sub_room_right")))
		.click();
    	try {
    		logger.info("---开始验证C1309739	【房型弹层】功能---");
    		WebElement book = new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("htl_room_detail_bottom_bar_btn")));
    		Assert.assertTrue(book.isDisplayed());
    		logger.info("---验证C1309739	【房型弹层】功能Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309739	【房型弹层】功能Fail---");
		}
    	driver.findElement(By.id("tvClose")).click();
    }
    
    @AfterMethod
	public void afterTest() {
    	logger.info("---返回列表页---");
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("ivBack"))).click();
	     logger.info("---返回搜索首页---");
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
	     //返回Trip首页	     
	     logger.info("---返回Trip首页---");
	     driver.findElementByClassName("android.widget.ImageButton").click();
	}	
    @AfterClass
    public void afterClass() {
  	  driver.quit();
    }
}
