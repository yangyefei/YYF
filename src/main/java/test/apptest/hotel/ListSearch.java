package test.apptest.hotel;

import com.trip.hotel.test.android.po.HtlDetailPage;
import com.trip.hotel.test.android.po.HtlHomePage;
import com.trip.hotel.test.android.po.HtlListPage;
import com.trip.hotel.test.common.BaseTest;
import com.trip.hotel.test.service.impl.AppCommonServiceImpl;
import com.trip.hotel.test.service.impl.InitialServiceImpl;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.InitialService;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class ListSearch extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonServiceImpl appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	
	@Test(description = "by ylf: C1309714	2成人2儿童（一成年，一未成年）搜索酒店", groups = { "Base" })
	public void Adults2Children2() 
	{
		String searchKey = "上海";
		logger.info("--C1309714	2成人2儿童（一成年，一未成年）搜索酒店---");
		
		logger.info("--点击输入框 转到输入页面---");
		HtlHomePage.findElement(driver,HtlHomePage.hotel_main_search).click();
		
		logger.info("--在输入框输入 "+searchKey+"---");
		WebElement input = HtlHomePage.findElement(driver,HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
		input.clear();
		input.sendKeys(searchKey);
		
		logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
		ArrayList<WebElement>  elm= HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);	
		elm.get(0).click();
		
/*		logger.info("--设置入店日期---");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		String Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckin(driver, Month, (cal.get(Calendar.DATE)+""));
		
		logger.info("--设置离店日期---");
		cal.add(Calendar.DATE, 1);
		Month =  cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		logger.info("--"+Month+cal.get(Calendar.DATE));
		HtlHomePage.SetCheckout(driver, Month, (cal.get(Calendar.DATE)+""));*/
		
		logger.info("进入酒店列表");
		HtlHomePage.DoSearch(driver);
		
		logger.info("打开日期、人数设置");
		HtlListPage.ShowDateAdultChildPage(driver);
		
		logger.info("打开成人、儿童设置");
		HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);
		
		logger.info("设置2成人2儿童");
		HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 2, 2);//2成人2儿童
		
		logger.info("第1个儿童1岁");
		HtlListPage.AdultChildPage.SetChildAge(driver, 1, 1);//第1个儿童1岁
		
		logger.info("第2个儿童13岁");
		HtlListPage.AdultChildPage.SetChildAge(driver, 2, 13);//第2个儿童13岁
		
		logger.info("点击确认");
		HtlListPage.AdultChildPage.Confirm(driver);
		
		logger.info("点击搜寻");
		HtlListPage.DateAdultChildPage.DoSearch(driver);
		
		logger.info("进入详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("验证成人数");
		Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).equals("2 成人"));
		
		logger.info("验证儿童数");
		Assert.assertTrue(HtlDetailPage.getChildNumber(driver).equals("2 小童"));
		
		HtlDetailPage.backToList(driver);
	}
	
	@Test(description = "by ylf: C1309713	8成人3儿童（均成年）", groups = { "Base" })
	public void Adults8Children3() 
	{
		String searchKey = "首尔";
		logger.info("--C1309713	8成人3儿童（均成年）---");
		
		logger.info("--点击输入框 转到输入页面---");
		HtlHomePage.findElement(driver,HtlHomePage.hotel_main_search).click();
		
		logger.info("--在输入框输入 "+searchKey+"---");
		WebElement input = HtlHomePage.findElement(driver,HtlHomePage.SearchEnginePage.hotel_destination_search_keyword);
		input.clear();
		input.sendKeys(searchKey);
		
		logger.info("--在搜索结果列表选第一个结果 回到搜索首页---");
		ArrayList<WebElement>  elm= HtlHomePage.findElements(driver, HtlHomePage.SearchEnginePage.tvTitle);	
		elm.get(0).click();
		
		logger.info("进入酒店列表");
		HtlHomePage.DoSearch(driver);
		
		logger.info("打开日期、人数设置");
		HtlListPage.ShowDateAdultChildPage(driver);
		
		logger.info("打开成人、儿童设置");
		HtlListPage.DateAdultChildPage.ShowAdultChildPage(driver);
		
		logger.info("设置8成人3儿童");
		HtlListPage.AdultChildPage.SetAdultChildNumber(driver, 8, 3);
		
		HtlListPage.AdultChildPage.SetChildAge(driver, 1, 12);
		HtlListPage.AdultChildPage.SetChildAge(driver, 2, 13);
		HtlListPage.AdultChildPage.SetChildAge(driver, 3, 14);

		
		logger.info("点击确认");
		HtlListPage.AdultChildPage.Confirm(driver);
		
		logger.info("点击搜寻");
		HtlListPage.DateAdultChildPage.DoSearch(driver);
		
		logger.info("进入详情页");
		HtlListPage.ToFirstHotelDetailPage(driver);
		
		logger.info("验证成人数");
		Assert.assertTrue(HtlDetailPage.getAdultNumber(driver).equals("8 成人"));
		
		logger.info("验证儿童数");
		Assert.assertTrue(HtlDetailPage.getChildNumber(driver).equals("3 小童"));
		
		HtlDetailPage.backToList(driver);
	}


	@AfterMethod
	public void afterMethod() {
		//		logger.info("---返回搜索首页---");
		//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("iv_back"))).click();
		// 返回Trip首页
		//logger.info("---返回Trip首页---");
		//driver.findElementByClassName("android.widget.ImageButton").click();.
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver, "ctrip.english");
		logger.info("初始化成功");
		logger.info("进入酒店首页");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon")))
				.click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	
	}

}
