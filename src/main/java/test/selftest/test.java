package test.selftest;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


import common.frame.test.BaseTest;

public class test extends BaseTest {
	@BeforeSuite
	public void beforeSuites() {
		logger.info("-------------beforesuite-------------");
	}

	@Test(priority=2,dataProvider = "testData", description = "携程测试hotel", groups = { "base" })
	public void testa(Map<String, String> datadriven) {
		System.out.println(datadriven.get("city"));
		logger.info("testtester");
		Assert.assertEquals(1, 2);
	}

	//test
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

}
