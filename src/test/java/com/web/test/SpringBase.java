package com.web.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath*:ApplicationContext.xml" })

public class SpringBase extends AbstractTestNGSpringContextTests {
 
	private String webRunMachineIp;
	
	public void setWebRunMachineIp(String webRunMachineIp) {
		this.webRunMachineIp = webRunMachineIp;
	}

	public SpringBase() throws MalformedURLException {
		PropertyConfigurator.configure("log4j.properties");

	}


}
