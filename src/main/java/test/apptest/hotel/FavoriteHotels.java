package test.apptest.hotel;

import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.web.utils.Retry;
import com.web.utils.TestLinster;

import common.frame.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.tools.ant.taskdefs.Sleep;
import org.eclipse.jetty.util.ReadLineInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class FavoriteHotels extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	
	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("初始化成功");
	}
	@AfterMethod
	public void afterMethod() throws MalformedURLException {
		driver.quit();
	}
	
	@Test(description = "by ylf: C1309665	无任何喜爱的酒店+返回上级菜单 ", groups = { "Base" },priority=1)
	public void NoFavoriteHotels() throws Exception {
		logger.info("准备登陆");
	
		appCommonService.loginForApp(driver, "avepellaba-2332@yopmail.com", "1qaz@wsx");
		
		logger.info("进入搜索首页 ");
		driver.findElement(By.id("myctrip_hotel_icon")).click();
		
		logger.info("C1309665	无任何喜爱的酒店+返回上级菜单 ");
		
		driver.findElement(By.id("tv_hotel_favorite")).click();
		WebElement content=driver.findElement(By.id("viw_wish_list_empty_tv"));
		//无任何喜爱的酒店
		logger.info("---验证无任何喜爱的酒店---");
		Assert.assertTrue(content.getText().contains("沒有喜愛的酒店。請按心形標誌，收藏酒店！"));
		
		//返回酒店首页	     
	     logger.info("---返回酒店首页---");
	     driver.findElementByClassName("android.widget.ImageButton").click();
	}
	
	
	@Test(description = "by yyf: C1309666	喜爱酒店城市筛选 ", groups = { "Base" },priority=2)
	public void FavoriteHotelsFilter() throws Exception {
		logger.info("准备登陆");
		appCommonService.logoutForApp(driver);
		appCommonService.loginForApp(driver, "wwwwww", "good08");
		logger.info("进入搜索首页 ");
		driver.findElement(By.id("myctrip_hotel_icon")).click();
		//所有喜爱的酒店
		driver.findElement(By.id("tv_hotel_favorite")).click();
		driver.findElement(By.id("tv_wish_hotel_title")).click();
		
		WebElement web=driver.findElement(By.id("ll_menu"));
		List<WebElement> list=web.findElements(By.className("android.widget.LinearLayout"));
		list.get(3).click();
		
		//rv_results
		new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("rv_results"))).isDisplayed();
		}
	
	@Test(description = "by chr: C1309667	喜爱的酒店和详情页的联动（取消收藏+收藏） ", groups = { "Base" },priority=3)
	public void FavoriteHotelsRelation() throws Exception {
		String hotelname = null;
		String attractionText = null;
		logger.info("准备登陆1");
		appCommonService.logoutForApp(driver);
		appCommonService.loginForApp(driver, "wwwwww", "good08");
		logger.info("进入搜索首页1 ");
		Thread.sleep(3000);
		driver.findElement(By.id("myctrip_hotel_icon")).click();
		//所有喜爱的酒店
		Thread.sleep(1000);
		driver.findElement(By.id("tv_hotel_favorite")).click();
		//driver.findElement(By.id("tv_wish_hotel_title")).click();
		//记录酒店名称
		hotelname=driver.findElement(By.id("tv_hotel_name")).getText().trim();
		logger.info(hotelname);
		driver.findElement(By.id("tv_hotel_name")).click();
		//
		//WebElement web=driver.findElement(By.id("rv_results"));
		//
		//List<WebElement> list=web.findElements(By.id("view_right_content_container"));
		//list.get(0).click();
		//
		Thread.sleep(3000);
		driver.findElement(By.id("ivFavorite")).click();
		//
		Thread.sleep(1000);
		driver.findElement(By.id("ivBack")).click();
		//
		Thread.sleep(1000);
		Assert.assertNotEquals(driver.findElement(By.id("tv_hotel_name")).getText(),hotelname);
		//
		driver.findElement(By.id("hotel_wish_list_menu_home")).click();
		//
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("myctrip_hotel_icon"))).click();
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("tv_stay_in"))).click();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import"))).clear();
	     new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("hotel_destination_search_keyword_import")))
	                      .sendKeys(hotelname); 
	     logger.info(hotelname);
	     //
	     ArrayList<WebElement> destinationlist = (ArrayList<WebElement>) driver.findElements(By.id("tvTitle"));
	     try {
	    	 attractionText=destinationlist.get(0).getText().trim();
		     logger.info(attractionText);
		     Thread.sleep(3000);
	    	 Assert.assertEquals(hotelname,attractionText);
	         //logger.info("C1309616:成功找到新加坡希爾頓酒店(Hilton Singapore)");
	     } catch (Exception e) 
	    {
	    	logger.info("没有找到"+ hotelname);
	    }
	     
	     try {
		     destinationlist.get(0).click();
		     Thread.sleep(3000);
		     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		        logger.info("成功跳转到列表页");
		    } catch (Exception e) 
		    {
		    	logger.info("跳转到列表页失败");
		    }
	     //
	     Thread.sleep(5000);
	     logger.info("详情页进入");
	     driver.findElement(By.id("tv_hotel_name")).click();
	     Thread.sleep(3000);
	     driver.findElement(By.id("ivFavorite")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("ivBack")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("iv_back")).click();
		//所有喜爱的酒店
		driver.findElement(By.id("tv_hotel_favorite")).click();
		//记录酒店名称
		attractionText=driver.findElement(By.id("tv_hotel_name")).getText().trim();
		//
		 logger.info(attractionText);
		 //
		Assert.assertEquals(hotelname,attractionText);

		}
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

