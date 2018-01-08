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

public class CtripHotel extends BaseTest {
	private InitialService Initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {

	}

	// 测试用例 执行 ，数据提供testData, 超时30000s,读取一次数据
	@Test(dataProvider = "testData", description = "hotelByYyf", groups = {"base"},timeOut=30000)
	public void HotelLogin(Map<String, String> datadriven) throws Exception {
		
		logger.info("APP " + datadriven.get("version") + "---启动携程app---");
		driver = Initial.appiumAndroidCtripSetUp(driver);

		
		logger.info("初始化成功，准备登陆");
		appCommonService.loginForApp(driver, "wwwwww", "good08");
		driver.findElement(By.id("myctrip_hotel_icon")).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("tv_search"))).click();
		Thread.sleep(100000);
		
		
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
