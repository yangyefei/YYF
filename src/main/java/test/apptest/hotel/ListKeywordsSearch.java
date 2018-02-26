package test.apptest.hotel;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

import java.net.MalformedURLException;

import java.util.List;

import common.frame.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ListKeywordsSearch extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
	}

	@Test(description = "By sxm : C1309690	海外省和景点下的品牌搜索", groups={"Base"})
	public void listProvinceSearchBrand() throws Exception{
		logger.info("---酒店首页搜索紐約州---");
		appCommonService.homeSearchHotel(driver, "紐約州");
		logger.info("---酒店列表页搜索品牌---");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("hotels_list_search_input"))).click();

		logger.info("---查看是否存在搜索历史---");
		if(driver.findElement(By.id("view_hotel_top_destination_group_title")).getText().equals("最近搜尋"))
        {
        	logger.info("---清空历史搜索记录---");
        	driver.findElement(By.id("tv_hotel_keyword_clear")).click();
        }
		Thread.sleep(1000);
		logger.info("---查找元素brinds---");
		WebElement brinds = driver.findElement(By.id("view_hotel_top_destination_group_container"));
		logger.info("---查找元素brind---");
        WebElement brand = brinds.findElements(By.className("android.widget.FrameLayout")).get(0).
        findElement(By.id("hotel_keywords_search_line_left")).
        findElement(By.id("hotel_top_destination_line_item_0"));
        logger.info("---点击品牌搜索---");
        String searchbrand = brand.getText();
        brand.click();
        try {
        	logger.info("---C1309690	海外省和景点下的品牌搜索---");
        	WebElement filterreddot = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("top_bar_filter_red_dot")));

	    	Assert.assertTrue(filterreddot.isDisplayed());
	    	logger.info("C1309690	海外省和景点下的品牌搜索Pass");
		} catch (Exception e) {
			logger.info("C1309690	海外省和景点下的品牌搜索Fail");
		}
	}
	
	@AfterClass
	public void afterClass() {
		logger.info("I here afterclass");
		driver.quit();
	}
}
