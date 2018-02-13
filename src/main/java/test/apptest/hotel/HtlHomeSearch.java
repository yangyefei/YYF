package test.apptest.hotel;

import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import org.testng.annotations.BeforeClass;

import static org.testng.AssertJUnit.assertEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class HtlHomeSearch extends BaseTest {

	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;

	// 根据国内地标搜索酒店
	@Test
	public void C1309606() {
		String searchKey = "東方明珠";
		logger.info("--C1309606 根据国内地标搜索酒店---");
		
		logger.info("--点击输入框 转到输入页面---");
		driver.findElement(By.id("rl_stay_in")).click();
		
		logger.info("--在输入框输入 東方明珠---");
		WebElement input = driver.findElement(By.id("hotel_destination_search_keyword_import"));
		input.clear();
		input.sendKeys(searchKey);
		
		logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
		ArrayList<AndroidElement>  elm= (ArrayList<AndroidElement>) driver.findElementsByClassName("android.widget.TextView");
//		for (AndroidElement androidElement : elm) {
//			logger.info(androidElement.getText());
//		}
		
		elm.get(0).click();
		
		logger.info("--点击搜寻 进入列表页---");
		driver.findElement(By.id("tv_search")).click();
		
		logger.info("--验证列表页搜索框有 东方明珠---");
		WebElement hotels_list_search_input = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input")));
		logger.info("-------------" + hotels_list_search_input.getText());
		logger.info("--------------" + searchKey);
		assertEquals(hotels_list_search_input.getText(),searchKey);
		
		
		logger.info("--点击位置---");
		driver.findElement(By.id("tv_location")).click();
		
		logger.info("--验证东方明珠勾选状态---");
		WebElement status = driver.findElement(By.id("list_sub_menus")).findElement(By.xpath("./LinearLayout/TextView"));
		logger.info("-------------" + status.getText());		
		assertEquals(status.getText()!="",true);
		
		logger.info("--关闭位置筛选---");
		driver.findElement(By.id("tv_hotel_filter_back")).click();
		
		logger.info("--返回搜索首页---");
		driver.findElement(By.id("iv_back")).click();
		
    
	}

	//C1309620	搜索海外城市在28天搜索酒店
	@Test
	public void C1309620() {
		String searchKey = "首尔";
		logger.info("--C1309620	搜索海外城市在28天搜索酒店---");
		
		logger.info("--点击输入框 转到输入页面---");
		driver.findElement(By.id("rl_stay_in")).click();
		
		logger.info("--在输入框输入 東方明珠---");
		WebElement input = driver.findElement(By.id("hotel_destination_search_keyword_import"));
		input.clear();
		input.sendKeys(searchKey);
		
		logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
		ArrayList<AndroidElement>  elm= (ArrayList<AndroidElement>) driver.findElementsByClassName("android.widget.TextView");
//		for (AndroidElement androidElement : elm) {
//			logger.info(androidElement.getText());
//		}
		
		elm.get(0).click();
		
		logger.info("--点击搜寻 进入列表页---");
		driver.findElement(By.id("tv_search")).click();
		
		logger.info("--验证列表页搜索框有 东方明珠---");
		WebElement hotels_list_search_input = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input")));
		logger.info("-------------" + hotels_list_search_input.getText());
		logger.info("--------------" + searchKey);
		assertEquals(hotels_list_search_input.getText(),searchKey);
		
		
		logger.info("--点击位置---");
		driver.findElement(By.id("tv_location")).click();
		
		logger.info("--验证东方明珠勾选状态---");
		WebElement status = driver.findElement(By.id("list_sub_menus")).findElement(By.xpath("./LinearLayout/TextView"));
		logger.info("-------------" + status.getText());		
		assertEquals(status.getText()!="",true);
		
		logger.info("--关闭位置筛选---");
		driver.findElement(By.id("tv_hotel_filter_back")).click();
		
		logger.info("--返回搜索首页---");
		driver.findElement(By.id("iv_back")).click();
		
    
	}

	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("---启动APP---");

//		logger.info("初始化成功，准备登陆");
//		appCommonService.loginForApp(driver, "wwwwww", "good08");
//		logger.info("---进入酒店首页---");
		new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
		.click();
//		driver.findElement(By.id("myctrip_hotel_icon")).click();
	}

	@AfterClass
	public void afterClass() {
		logger.info("Close Trip App");
		driver.quit();
	}

}
