package com.web.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.Suite;

import com.beust.testng.TestNG;

public class StartTest {

	   public static void main(String[] args) {
		TestNG  testNG =new TestNG();
		List<String> suites  = new  ArrayList<String>();
		suites.add("testng.xml");
		suites.add("testng2.xml");
		testNG.setTestSuites(suites);
		testNG.run();
	}
}
