package common.frame.test;


import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;



@ContextConfiguration(locations={"classpath*:/config/ApplicationContext.xml"})
public  class BaseTest extends AbstractTestNGSpringContextTests{
	@Autowired
   public ExcelProviderByTest excelProviderByTest;
	
   public Iterator<Object[]> ExcelProviderByEnv(Object aa, String sheetName) {
	   
	     return this.excelProviderByTest.excelProvider(aa, sheetName);
   }

}
