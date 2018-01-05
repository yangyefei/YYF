package test.apptest.hotel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import service.AppCommonService;
import service.InitialService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CtripHotel extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;
	// @Autowired
	// private WebCommonService webCommonService;

	private AppiumDriver driver;
//	private IOSDriver  iosdriver;
//	private AndroidDriver asdriver;
	// private WebDriver webDriver;

	@BeforeClass
	public void beforeClass() {
	}

	// 测试用例 执行 ，数据提供testData, 超时30000s,读取一次数据
	@Test( dataProvider = "testData", description = "携程测试hotel")
	public void ctripHotelLogin(Map<String, String> datadriven) throws Exception {

		String apkPathOfChuangye = datadriven.get("apkPathOfCtripHotel");

		logger.info("APP " + datadriven.get("version") + "---启动携程app---");
		logger.info("启动Ctrip-dbug.app");
		driver = Initial.appiumAndroidChuangyeSetUp((AndroidDriver) driver, apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 登录携程app,用户名 wwwwww
		appCommonService.loginForApp(driver, "wwwwww", "good08");
		// logger.info("登录携程app");
		// new WebDriverWait(driver,
		// 60).until(ExpectedConditions.elementToBeClickable(By.id("rl_account"))).click();
		// new WebDriverWait(driver,
		// 60).until(ExpectedConditions.elementToBeClickable(By.id("tvSignIn"))).click();
		// new WebDriverWait(driver,
		// 60).until(ExpectedConditions.elementToBeClickable(By.id("account_input"))).clear();
		// driver.findElement(By.id("account_input")).sendKeys(datadriven.get("account"));
		// driver.findElement(By.id("password_input")).sendKeys(datadriven.get("password"));
		// driver.findElement(By.id("login_btn")).click();

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_home"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.clear();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.sendKeys("Shanghai");

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("Free cancellation")))
				.click();

		logger.info("遍历酒店列表 ，取出第一个，进入详情页");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("Free cancellation")))
				.isSelected();
		Thread.sleep(3000);
		ArrayList<WebElement> hotellist = (ArrayList<WebElement>) driver.findElements(By.id("tv_hotel_name"));

		for (WebElement webElement : hotellist) {
			logger.info(webElement.getText());

		}
		hotellist.get(0).click();
		// swipeUp(driver);
		WebElement iv = new WebDriverWait(driver, 60)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("ivFavorite")));
		if (iv.isDisplayed()) {
			logger.info("ivFavorvate is dispaly");
			// driver.scrollTo("Twin Room").click();;

			swipeUp(driver);

			driver.findElement(By.name("Deluxe Room")).click();
			driver.findElement(By.id("btn_book")).click();
			// new WebDriverWait(driver,
			// 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_bottom_select"))).click();
			// try {
			// String
			// actual=driver.findElement(By.id("tv_order_status_not_imme_tv")).getText();
			// String expected="Your booking has been submitted...";
			// Assert.assertEquals(actual, expected);
			// } catch (Exception e) {
			// // TODO: handle exception
			// }

			Thread.sleep(5000);
			logger.info("测试完成");
		}

	}
	

	private void swipeleft(AppiumDriver driver) {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 6, height * 5 / 6, width / 6, height * 2 / 3, 1000);// 向下滑动，间隔1s

	}

	private void swiperight(AppiumDriver driver) {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 5 / 6, height * 5 / 6, width * 5 / 6, height * 2 / 3, 1000);// 向下滑动，间隔1s

	}

	private void dateswipeleft(AppiumDriver driver) {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 3 / 10, height * 5 / 6, width * 3 / 10, height * 99 / 100, 1000);// 向上滑动，间隔1s

	}

	private void dateswiperight(AppiumDriver driver) {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 7 / 10, height * 5 / 6, width * 7 / 10, height * 99 / 100, 1000);// 向上滑动，间隔1s

	}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	private void swipeUp(AppiumDriver driver) {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 5 / 6, width / 6, height * 3 / 6, 1000);// 向下滑动，间隔1s

	}

}
