package test.apptest.hotel;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.web.utils.TestLinster;
import com.web.utils.TestLinster;

import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.testng.annotations.DataProvider;
import common.frame.test.BaseTest;

import org.eclipse.jetty.util.ReadLineInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelSearchEngine extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver);
		//TestLinster.webDriver = driver; // androiddriver 传递给testlinster
		logger.info("初始化成功，准备登陆");
		appCommonService.loginForApp(driver, "wwwwww", "good08"); // 登陆

	}

	// 测试用例 执行 ，数据提供testData
	@Test(dataProvider = "testData", description = "yefei.yang", groups = { "Base" })
	public void hotelSearch(Map<String, String> datadriven) throws Exception {
        logger.info("---"+datadriven.get("id")+"---==>StartTest");

		driver.findElement(By.id("myctrip_hotel_icon")).click(); // 进入酒店首页
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("rl_stay_in")))
				.click();
		WebElement e = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")));
		e.clear();
		e.sendKeys(datadriven.get("searchKeyWord"));
		WebElement tvTitle = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
		assertEquals(tvTitle.getText(), datadriven.get("result"));

		driver.pressKeyCode(4);
		Thread.sleep(1000);
		driver.pressKeyCode(4);
		
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
