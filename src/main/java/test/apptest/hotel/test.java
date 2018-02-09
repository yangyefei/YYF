package test.apptest.hotel;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import org.apache.log4j.Logger;

public class test extends BaseTest {
	
	Logger logger = Logger.getLogger("testa.class");

	@Test(dataProvider = "testData", description = "携程测试hotel",groups={"base"})
	public void testa(Map<String, String> datadriven) {
		System.out.println(datadriven.get("version"));
//assertEquals(datadriven.get("version"), actual);
		
		logger.info("testtest");
	}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}
}
