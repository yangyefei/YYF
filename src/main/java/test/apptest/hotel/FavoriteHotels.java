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
import common.frame.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.apache.tools.ant.taskdefs.Sleep;
import org.eclipse.jetty.util.ReadLineInputStream;
import org.openqa.selenium.By;
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

	}
	
	@Test(description = "hotelBySxm", groups = {"base"})
	public void menuHome() throws Exception {
		
		//logger.info("APP " + datadriven.get("version") + "---启动携程app---");
		driver = initial.appiumAndroidCtripSetUp(driver);

		logger.info("初始化成功，准备登陆");
		appCommonService.loginForApp(driver, "wwwwww", "good08");
		driver.findElement(By.id("myctrip_hotel_icon")).click();
		
		logger.info("C1309665	无任何喜爱的酒店+返回上级菜单 ");
		//new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_my_hotel_title"))).click();
		driver.findElement(By.id("tv_my_hotel_title")).click();
		driver.findElement(By.id("favoriteRL")).click();
		driver.findElement(By.id("hotel_wish_list_menu_home")).click();
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
