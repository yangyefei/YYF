package com.web.test;

import org.junit.experimental.max.MaxCore;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import com.web.utils.TestLinster;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
@Listeners({TestLinster.class})
public class Common extends SpringBase {
   
	public Common() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private WebConfig webConfig;
	@Autowired
	private MySqlDao mySqlDao;
	private WebDriver driver;
	File file = new File("d:\\demo.txt");

	// @Test

	@Test
	public void testCollcetion() {
   int[] b = new  int[1];
   b[0]++;
   System.out.println(b[0]);
   //yyfedit
		}
	
}

