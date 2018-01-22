package test.apptest.hotel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class test2 extends BaseTest {

	@Test(priority=2, description = "携程测试hotel",groups={"base"})
	public void testb() {

		
		logger.info("testtest2");
	}

	@Test(priority=1, description = "携程测试hotel",groups={"base"})
	public void testc() {

		
		logger.info("testtest3");
	}
	
}
