package test.apptest.hotel;

import static org.testng.AssertJUnit.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import jxl.write.DateTime;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

@Test
public class BookGuaranteeHotel extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService  appCommonService = new AppCommonServiceImpl();
    private AndroidDriver driver ;
    
    @BeforeClass
    public void beforeclass() throws MalformedURLException{
    	driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
    	logger.info("初始化成功，准备登陆");
		appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆
    }
    
    public void bookGuaranteeHotel() throws Exception{
    	logger.info("--进入酒店首页--");
    	new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("myctrip_hotel_icon"))).click();
    	//driver.findElement(By.id("myctrip_hotel_icon")).click(); // 进入酒店首页

    	logger.info("--点击搜索框--");
    	driver.findElement(By.id("tv_stay_in")).click();
    	logger.info("--定位目的地搜索框--");
    	WebElement search = new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
    	logger.info("--清除目的地搜索框--");
    	search.clear();
    	String keywords = "Baoan Hotel";
    	logger.info("--对目的地搜索框输入内容--" + keywords);
    	search.sendKeys(keywords);
    	new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("tvTitle"))).click();

    	logger.info("----展开基础房型------");
    	new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("hotel_rooms_list_main_room_expanded_room_expand_indicator"))).click();
    	logger.info("----选择房型------");
    	new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("hotel_rooms_list_sub_room_btn_book"))).click();
    	logger.info("----选择超时担保------");
    	new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("iv_arrival_time_select"))).click();
    	logger.info("----选择担保时间------");
    	new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("tv_arrival_time_tip"))).click();
    	logger.info("----填写入住人信息------");
    	List<WebElement> Contact = driver.findElements(By.id("view_edit_text"));
    	Contact.get(2).clear();
    	Contact.get(2).sendKeys("test@test.com");
    	String paymentamount = driver.findElement(By.id("hotel_book_bottom_bar_normal_price_view_payment_currency_and_amount")).getText();
    	logger.info("----预定跳转到支付页------");
    	new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("tv_bottom_select"))).click();
    	String totalamount = driver.findElement(By.id("tvTotal")).getText();
    	assertEquals(paymentamount,totalamount);
    }
    
    @AfterClass
	public void afterClass() {
		logger.info("I am here afterclass");
		driver.quit();
	}
}
