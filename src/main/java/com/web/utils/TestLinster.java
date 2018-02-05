package com.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

public class TestLinster implements ITestListener {
   // public static WebDriver webDriver;
	public static Logger logger = Logger.getLogger(TestLinster.class); 
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext it) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult it) {
		// TODO Auto-generated method stub
		System.out.println("onTestFailure:"+it.getName()+"测试失败");
	   //TakeScreen.snapshot(webDriver, "yyf");
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult it) {
		// TODO Auto-generated method stub
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}




}
