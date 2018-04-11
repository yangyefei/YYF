package com.trip.hotel.test.common;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Iterator;
@ContextConfiguration(locations = { "classpath*:ApplicationContext.xml" })
//AbstractTransactionalTestNGSpringContextTests
//@Listeners(TestLinster.class)
public class BaseTest  extends AbstractTestNGSpringContextTests{

	ExcelProviderByTest excelProviderByTest = new ExcelProviderByTest();

	public Iterator<Object[]> ExcelProviderByEnv(Object aa, String sheetName) {

		return this.excelProviderByTest.excelProvider(aa, sheetName);
	}

	public BaseTest() {
		PropertyConfigurator.configure("log4j.properties");
	}
}
