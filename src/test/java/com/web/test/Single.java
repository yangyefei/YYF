package com.web.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;


import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.web.utils.TestLinster;
/**
 * 懒汉模式
 * @author yyf
 *
 */
public class Single {
	
	private static  Single single;
      private Single() {
		// TODO Auto-generated constructor stub
	}
	
      public  static Single getInstance(){
    	  if(single== null)
    	  single=new Single();
		return single;
      }
    

	
	public static void sumn(int[] array, int  n){
		if (array==null || array.length==0) {
			System.out.println("数组不能为空");
		}else {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					if (array[i]+array[j]==n) {
						System.out.println(i+"   "+j);
						
					}
				}
			}
		}
		
	}
	
	public  static  void  max(int[] array){
		int temp = 0;
		for(int i=0;i<array.length;i++){
		    if (array[i]>temp) {
				temp=array[i];
			}
			
			
			}
		System.out.println(temp);
		}
	
	
	public static void main(String[] args) {
		int[] array={1,2,3,10,5,6,7,8};
		max(array);
	}
}