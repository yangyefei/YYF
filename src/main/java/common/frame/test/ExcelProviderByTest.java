package common.frame.test;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ExcelProviderByTest{
	@Value("${envTestID}")
	private String envTestID;

	public Iterator<Object[]> excelProvider(Object aa, String sheetName) {
	   return new ExcelProvider(aa, sheetName, this.envTestID);
    }

	public String getEnvTestID() {
		return envTestID;
	}

	public void setEnvTestID(String envTestID) {
		this.envTestID = envTestID;
	}

}
