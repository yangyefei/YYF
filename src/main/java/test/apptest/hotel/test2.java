package test.apptest.hotel;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jetty.util.ReadLineInputStream;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class test2 extends BaseTest {

	@Test(priority = 2, description = "携程测试hotel", groups = { "base" })
	public void testb() {
		int count = 0;
		String str = "i Am a ApplE";
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				count++;
			}

		}
		System.out.printf("大写字母有 %s个", count);

	}

	@Test(priority = 1, description = "携程测试hotel", groups = { "base" })
	public void readTest() throws IOException {
		   System.out.println("ReadTest, Please Enter Data:");   
		    InputStreamReader is = new InputStreamReader(System.in); //new构造InputStreamReader对象   
		    BufferedReader br = new BufferedReader(is); //拿构造的方法传到BufferedReader中   
		    try{ //该方法中有个IOExcepiton需要捕获   
		      String name = br.readLine();   
		      System.out.println("ReadTest Output:" + name);   
		    }   
		    catch(IOException e){   
		      e.printStackTrace();   
		    }   
		        
		  }   
	}


