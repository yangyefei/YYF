package test.apptest.hotel;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;

public class test extends BaseTest {

	@Test(dataProvider = "testData", description = "携程测试hotel")
	public void testa(Map<String, String> datadriven) {
		System.out.println(datadriven.get("version"));

	}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}
}
