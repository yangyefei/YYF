package com.web.test;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import com.web.utils.*;

@Listeners({TestLinster.class,RetryListener.class})
public class Linster1Test extends SpringBase {
	public Linster1Test() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	int i = 0;
	
	@BeforeMethod
	public void BeforeMethod() {
		System.out.println("@BeforemMethod");
	}

	@Test // 单个测试用例重跑方法(retryAnalyzer = Retry.class)

	public void test1() {
		System.out.println(i);
		System.out.println("test1" + 1 / i);
		
	}

	@AfterClass
	public void afterClass() {
		System.out.println("@afterClass");
	}
}
