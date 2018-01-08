package com.web.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

public class TestLinster implements ITestListener {

	public static Logger logger = Logger.getLogger(TestLinster.class); 
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext it) {
		 logger.info("监听器onstart");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult it) {
		// TODO Auto-generated method stub

		System.out.println("onTestFailure:"+it.getName());
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult it) {
		// TODO Auto-generated method stub
		System.out.println("监听器onTestStart:"+it.getName());
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}





}
