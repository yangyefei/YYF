package com.trip.hotel.test.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Iterator;
public class ExcelProviderByTest{
	private String envTestID="android";

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
