package common.frame.data;

import java.util.Iterator;

public class ExcelProviderByTest{


	public Iterator<Object[]> excelProvider(Object aa, String sheetName) {
	   return new ExcelProvider(aa, sheetName);
    }


}
