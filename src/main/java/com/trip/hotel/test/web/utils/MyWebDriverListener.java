package com.trip.hotel.test.web.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;
import java.io.IOException;

public class MyWebDriverListener implements WebDriverEventListener {


	@Override
	public void afterClickOn(WebElement arg0, WebDriver driver) {
		// TODO Auto-generated method stub
		if (arg0.getText().equals("军事"))
			System.err.println(driver.getCurrentUrl());
	}

	@Override
	public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

	}

	@Override
	public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertAccept(WebDriver webDriver) {

	}

	@Override
	public void afterAlertAccept(WebDriver webDriver) {

	}

	@Override
	public void afterAlertDismiss(WebDriver webDriver) {

	}

	@Override
	public void beforeAlertDismiss(WebDriver webDriver) {

	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable arg0, WebDriver webdriver) {
		// TODO Auto-generated method stub

		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\yyf.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
