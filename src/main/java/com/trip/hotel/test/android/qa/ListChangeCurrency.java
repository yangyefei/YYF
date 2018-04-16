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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ListChangeCurrency extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;

	// 2018/4/8 修改驱动丢失导致的配置失败 by yyf
	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.createAndroidReleaseDriver();
	}

	@Test(description = "by yulf: C1309724	筛选币种酒店详情页查看", groups = { "Base" })
	public void CheckCurrencyOnDetailPage() throws Exception {
		logger.info("by yulf: C1309724	筛选币种酒店详情页查看");

		logger.info("搜上海");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.clear();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.sendKeys("上海");
		ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
		destinationlist.get(0).click();

		logger.info("进入上海列表页");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();

		doChangeCurrency("HK$");

		WebElement hotel = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotels_item_container")));
		hotel.click();

		WebElement price = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_sub_room_price")));
		try {
			logger.info("---开始验证C1309724	筛选币种酒店详情页查看---");
			logger.info("---当前页面币种：" + price.getText());
			assertTrue(price.getText().contains("HK$"));
			logger.info("---验证C1309724	筛选币种酒店详情页查看PASS---");
		} catch (Exception e) {
			logger.info("---验证C1309724	筛选币种酒店详情页查看Fail---");
		}

		logger.info("---返回到酒店列表页---");
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("ivBack")))
				.click();

	}

	@Test(description = "by sxm: C1309722	切换韩币酒店筛选", groups = { "Base" })
	public void listChangeKRW() throws Exception {
		logger.info("---搜索上海---");
		appCommonService.homeSearchHotel(driver, "上海");
		doChangeCurrency("KRW");
		WebElement price = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("tv_currency_name")));
		try {
			logger.info("---开始验证C1309722	切换韩币酒店筛选---");
			logger.info("---当前页面币种：" + price.getText());
			assertTrue(price.getText().contains("KRW"));
			logger.info("---验证C1309722	切换韩币酒店筛选Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309722	切换韩币酒店筛选Fail---");
		}
	}

	@Test(description = "by sxm: C1309723	切换小币种筛选酒店", groups = { "Base" })
	public void listChangePHP() throws Exception {
		logger.info("---搜索上海---");
		appCommonService.homeSearchHotel(driver, "上海");
		doChangeCurrency("PHP");
		WebElement price = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("tv_currency_name")));
		try {
			logger.info("---开始验证C1309723	切换小币种筛选酒店---");
			logger.info("---当前页面币种：" + price.getText());
			assertTrue(price.getText().contains("PHP"));
			logger.info("---验证C1309723	切换小币种筛选酒店Pass---");
		} catch (Exception e) {
			logger.info("---验证C1309723	切换小币种筛选酒店Fail---");
		}
	}

	public void doChangeCurrency(String curreny) {
		logger.info("---定位货币符号---");
		WebElement curr = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(By.id("ll_currency")));
		logger.info("---展开货币列表---");
		curr.click();

		List<WebElement> currenys = driver.findElements(By.id("tv_title"));
		for (WebElement cur : currenys) {
			if (cur.getText().contains(curreny)) {
				logger.info("---选择币种" + cur.getText() + "---");
				cur.click();
				break;
			}
		}

	}

	// 2018/4/8 修改驱动丢失导致的配置失败 by yyf
	@AfterMethod
	public void afterMethod() {
		// logger.info("---返回到酒店首页---");
		// new WebDriverWait(driver,
		// timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
		// logger.info("---返回到首页---");
		// new WebDriverWait(driver,
		// timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.className("android.widget.ImageButton"))).click();
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
