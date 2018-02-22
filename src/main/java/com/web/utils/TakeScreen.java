package com.web.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class TakeScreen {

	public static void snapshot(WebDriver webdriver) {
        Calendar  calendar =Calendar.getInstance();
        SimpleDateFormat  sFormat= new SimpleDateFormat("YYYY-MM-dd");
        String  date= sFormat.format(calendar.getTime());
		String currentPath = System.getProperty("user.dir");

		System.out.println(currentPath);
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		scrFile.getParentFile();

		try {
			System.out.println("save snapshot path is:" + currentPath +"\\"+"target"+"\\"+date+".jpg");
			FileUtils.copyFile(scrFile, new File(currentPath +"\\"+"target"+"\\"+date+".jpg"));
		} catch (IOException e) {

			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {

			System.out.println("screen shot finished");
		}

	}

	@Test
	public static void picture() {
		
		System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
//		webdriver = new RemoteWebDriver(new URL("http://"+"127.0.0.1"+":4444/wd/hub"), DesiredCapabilities.internetExplorer());
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer(); 
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
		WebDriver webdriver = new InternetExplorerDriver();
		webdriver.get("D:/gitlab/maven/maven/target/surefire-reports/html/index.html");
		snapshot(webdriver);
	}
}
