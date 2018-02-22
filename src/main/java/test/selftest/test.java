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

	@Test(description = "携程测试hotel",groups = { "base" },retryAnalyzer =com.web.utils.Retry.class)
	public void testa() throws MalformedURLException {
		System.out.println(1/1);
		
	}
	@Test
	public void testaa(){
	
		String str = Integer.toString(456); //获取数字的十进制表示
		String str2 = Integer.toBinaryString(456); //获取数字的二进制表示
		String str3 = Integer.toHexString(456); //获取数字的十六进制表示
		String str4 = Integer.toOctalString(456); //获取数字的八进制表示
		System.out.println("'456'的十进制表示为："+str);
		System.out.println("'456'的二进制表示为："+str2);
		System.out.println("'456'的十六进制表示为："+str3);
		System.out.println("'456'的八进制表示为："+str4);
		System.out.println(1/0);
	}

	//test
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

}
