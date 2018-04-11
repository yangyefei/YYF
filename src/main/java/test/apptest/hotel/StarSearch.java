package test.apptest.hotel;

import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.HotelHomePageInitialImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.AppCommonService;
import service.InitialService;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class StarSearch extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private HotelHomePageInitialImpl hotelHomePageInitialImpl = new HotelHomePageInitialImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	private Logger logger = Logger.getLogger("StarSearch.class");

	
	//2018/4/8   修改驱动丢失导致的配置失败  by yyf
	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver, "ctrip.english");
	}

	@Test(description = "by lnn: 星级为3星或者4星或者5星搜索C1309631", groups = { "Base" })
	public void starSearch() throws Exception {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.clear();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.sendKeys("上海");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
		ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
		destinationlist.get(0).click();

		// 清除酒店首页的成人儿童筛选
		hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_3"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		try {
			WebElement filterreddot = driver.findElement(By.id("top_bar_filter_red_dot"));
			Assert.assertTrue(filterreddot.isDisplayed());
			logger.info("C1309631:验证筛选3星级成功");
		} catch (Exception e) {
			logger.info("C1309631:筛选3星级失败");
		}
	}

	@Test(description = "by sxm: C1309630星级分别为<=2星搜索", groups = { "Base" })
	public void twostarSearch() throws Exception {
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

		// 清除酒店首页的成人儿童筛选
		hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_2"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		try {
			WebElement filterreddot = driver.findElement(By.id("top_bar_filter_red_dot"));
			Assert.assertTrue(filterreddot.isDisplayed());
			logger.info("C1309630 星级分别为<=2星搜索成功");
		} catch (Exception e) {
			logger.info("C1309630星级分别为<=2星搜索失败");
		}
	}

	@Test(description = "by ylf: C1309627	默认价格搜索，默认星级", groups = { "Base" })
	public void defaultStarSearch() throws Exception {
		logger.info("---搜 上海---");
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

		// 清除酒店首页的成人儿童筛选
		logger.info("---清除酒店首页的成人儿童筛选---");
		hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);

		// 搜3星到列表页
		logger.info("---搜3星到列表页---");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_3"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();

		// 返回搜索首页
		logger.info("---返回搜索首页---");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
		// 返回Trip首页
		logger.info("---返回Trip首页---");
		driver.findElementByClassName("android.widget.ImageButton").click();
		// 进入酒店搜索首页
		logger.info("---进入酒店搜索首页---");
		new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();

		// 3星仍在
		try {
			logger.info("---判断3星仍在---");
			WebElement star3 = driver.findElement(By.id("tv_star_text"));
			Assert.assertTrue(star3.getText().equals("3"));
			logger.info("默认3星成功");
		} catch (Exception e) {
			logger.info("默认3星失败");
		}
		// 进入3星列表页
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
			logger.info("---进入3星列表页，判断筛选红点---");
			WebElement filterreddot = driver.findElement(By.id("top_bar_filter_red_dot"));
			Assert.assertTrue(filterreddot.isDisplayed());
			logger.info("C1309627	默认价格搜索，默认星级成功");

		} catch (Exception e) {
			logger.info("C1309627	默认价格搜索，默认星级失败");
		}

	}

	@Test(description = "by ylf: C1309632	星级为3,4,5搜索", groups = { "Base" })
	public void Star345Search() throws Exception {
		logger.info("---搜 上海---");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.clear();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
				.sendKeys("上海");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tvTitle")));
		ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
		destinationlist.get(0).click();

		// 清除酒店首页的成人儿童筛选
		hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_3"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_4"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_5"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();

		try {
			WebElement filterreddot = driver.findElement(By.id("top_bar_filter_red_dot"));
			Assert.assertTrue(filterreddot.isDisplayed());
			logger.info("C1309632	星级为3,4,5搜索成功");
		} catch (Exception e) {
			logger.info("C1309632	星级为3,4,5搜索失败");
		}

	}

	@Test(description = "by yyf: C1309634	星级为3,5搜索", groups = { "Base" })
	public void Star35Search() throws Exception {
		logger.info("---搜 上海---");
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

		// 清除酒店首页的成人儿童筛选
		hotelHomePageInitialImpl.HotelPageAdultsChildFilter(driver);

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_3"))).click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_5"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();

		try {
			WebElement filterreddot = driver.findElement(By.id("top_bar_filter_red_dot"));
			Assert.assertTrue(filterreddot.isDisplayed());
			logger.info("C1309634	星级为3,5搜索成功");
		} catch (Exception e) {
			logger.info("C1309632	星级为3,5搜索失败");
		}

	}
	//2018/4/8   修改驱动丢失导致的配置失败  by yyf
	@AfterMethod
	public void afterMethod() {
//		// 返回搜索首页
//		logger.info("---返回搜索首页---");
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("rl_star_price"))).click();
//		new WebDriverWait(driver, 60)
//				.until(ExpectedConditions.elementToBeClickable(By.id("view_hotel_filter_rating_no_limit"))).click();
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_done"))).click();
//		// 返回Trip首页
//		logger.info("---返回Trip首页---");
//		driver.findElementByClassName("android.widget.ImageButton").click();
		driver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}

}
