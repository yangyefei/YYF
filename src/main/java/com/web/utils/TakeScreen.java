package com.web.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.bcel.classfile.SourceFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TakeScreen {

	public static void snapshot(WebDriver webdriver, String filename) {
        Calendar  calendar =Calendar.getInstance();
        SimpleDateFormat  sFormat= new SimpleDateFormat("YYYY-MM-dd");
       String  date= sFormat.format(calendar.getTime());
       System.out.println(date);
        
		String currentPath = System.getProperty("user.dir");

		System.out.println(currentPath);
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		scrFile.getParentFile();

		try {
			System.out.println("save snapshot path is:" + currentPath + "/" + filename+date+".jpg");
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + filename+date+".jpg"));
		} catch (IOException e) {

			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {

			System.out.println("screen shot finished");
		}

	}

	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "D:\\autoWeb\\browserDriver\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("file:///D:/github/maven/test-output/html/apple.html");
		snapshot(webDriver, "yyf");
	}
}
