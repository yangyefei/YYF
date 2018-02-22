package test.apptest.hotel;

import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.web.utils.Retry;
import com.web.utils.TestLinster;

import common.frame.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.apache.tools.ant.taskdefs.Sleep;
import org.eclipse.jetty.util.ReadLineInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class FavoriteHotels extends BaseTest{
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english");
		logger.info("初始化成功");
	}
	
	@Test(description = "by ylf: C1309665	无任何喜爱的酒店+返回上级菜单 ", groups = { "Base" })
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
	
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

