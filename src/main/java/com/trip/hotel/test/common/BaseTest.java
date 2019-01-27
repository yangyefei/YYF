package com.trip.hotel.test.common;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Iterator;
@ContextConfiguration(locations = { "classpath*:ApplicationContext.xml" })
//AbstractTransactionalTestNGSpringContextTests
//@Listeners(TestLinster.class)
@PropertySource("classpath:/Users/yangyefei/IdeaProjects/AppTest/config/mysql.properties")
public class BaseTest  extends AbstractTestNGSpringContextTests{

	ExcelProviderByTest excelProviderByTest = new ExcelProviderByTest();

	public Iterator<Object[]> ExcelProviderByEnv(Object aa, String sheetName) {

		return excelProviderByTest.excelProvider(aa, sheetName);
	}

	public BaseTest() {
		PropertyConfigurator.configure("log4j.properties");
	}
}
