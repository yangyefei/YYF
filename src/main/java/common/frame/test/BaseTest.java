package common.frame.test;


import java.util.Iterator;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import common.frame.data.ExcelProviderByTest;


@ContextConfiguration(locations = { "classpath*:ApplicationContext.xml" })
public  class BaseTest extends AbstractTransactionalTestNGSpringContextTests{
	
   ExcelProviderByTest excelProviderByTest = new ExcelProviderByTest();

   public Iterator<Object[]> ExcelProviderByEnv(Object aa, String sheetName) {
	   
	     return this.excelProviderByTest.excelProvider(aa, sheetName);
   }
public BaseTest() {
	PropertyConfigurator.configure("log4j.properties");
}
}
