package test.apptest.hotel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;

public class test extends BaseTest {
	@BeforeSuite
	public void beforeSuites() {
		logger.info("-------------beforesuite-------------");
	}

	@Test(priority=2,dataProvider = "testData", description = "携程测试hotel", groups = { "base" })
	public void testa(Map<String, String> datadriven) {
		System.out.println(datadriven.get("version"));
		logger.info("testtester");
	}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

}
