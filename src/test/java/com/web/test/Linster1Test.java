package com.web.test;

import java.io.*;
import java.net.MalformedURLException;

import org.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl11;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.web.utils.WebHelper;


public class Linster1Test extends SpringBase{
	public Linster1Test() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public  void BeforeClass(){
		System.out.println("@BeforeClass");
	}
	
	 @Test
       public void test1(){
    	   System.out.println("test1"+1/0);
       }
		@AfterClass
		public  void afterClass(){
			System.out.println("@afterClass");
		}
}
