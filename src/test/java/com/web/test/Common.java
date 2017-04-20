package com.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.web.utils.TestLinster;

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


	// @Test

	@Test
	public void testCollcetion() throws IOException {
		File file =new File("./test-output/html/apple.html");
		
 FileInputStream  iStream= new FileInputStream(file);
 

 
 while (iStream.read()!=-1) {
	 char c=(char) iStream.read();
  System.out.print(c);
}

		}
	
}

