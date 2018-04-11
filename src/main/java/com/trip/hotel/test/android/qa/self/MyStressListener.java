package com.trip.hotel.test.android.qa.self;

import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyStressListener implements ITestListener {


    private AppiumDriver driver;

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult itr) {


//			System.out.println(itr.getName()+"：初始化成功");

    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        // TODO Auto-generated method stub
        int time = (int) ((itr.getEndMillis() - itr.getStartMillis()) / 1000);
        System.out.println(itr.getName() + "：测试成功" + "花费时间：" + time);
    }


}
