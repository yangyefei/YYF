package test.selftest;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import service.AppCommonService;
import service.InitialService;
import service.impl.AppCommonServiceImpl;
import service.impl.InitialServiceImpl;

public class test extends BaseTest {
	private InitialService initial = new InitialServiceImpl();
	private AppCommonService appCommonService = new AppCommonServiceImpl();
	private AndroidDriver driver;
	int timeOutInSeconds = 60;
	
	@BeforeSuite
	public void beforeSuites() {
		logger.info("-------------beforesuite-------------");
	}

	@Test(description = "携程测试hotel", groups = { "base" })
	public void testa() throws MalformedURLException {
		driver = initial.appiumAndroidCtripSetUp(driver,"ctrip.english.debug");
		appCommonService.logoutForApp(driver);
		
	}

	//test
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

}
