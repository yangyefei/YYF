package com.web.test;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

import com.web.utils.TestLinster;

import java.net.MalformedURLException;

@ContextConfiguration(locations = { "classpath*:ApplicationContext.xml" })
@Listeners(TestLinster.class)
public class SpringBase extends AbstractTestNGSpringContextTests {
 
	private String webRunMachineIp;
	
	public void setWebRunMachineIp(String webRunMachineIp) {
		this.webRunMachineIp = webRunMachineIp;
	}

	public SpringBase() throws MalformedURLException {
		PropertyConfigurator.configure("log4j.properties");

	}


}
