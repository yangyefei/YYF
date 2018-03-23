package com.web.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.web.utils.TestLinster;

public class Common {

	public Common() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}



	public void testCollcetion() throws IOException {
		// File file = new File("./test-output/html/apple.html");
		// String tomcatPath = System.getProperty("user.dir");
		// logger.info(tomcatPath);
		// FileInputStream iStream = new FileInputStream(file);
		//
		// while (iStream.read() != -1) {
		// char c = (char) iStream.read();
		// System.out.print(c);
		// }

		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		eventFiringWebDriver.manage().window().maximize();
		eventFiringWebDriver.get("http://www.baidu.com");
		eventFiringWebDriver.navigate().to("http://www.sina.com.cn");
		eventFiringWebDriver.findElement(By.linkText("新闻")).click();
		Set set = driver.getWindowHandles();
		for (Object object : set) {
			System.out.println(object);
		}
		driver.getCurrentUrl();
	}

	/**
	 * Map
	 */
	@Test
	public void testtest() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "阳阳");
		map.put("贱男", "禁勃然");
		System.out.println("----------------"+map.entrySet());
		Set<String> keys = map.keySet();
		for (String string : keys) {
			System.out.println(string + "  :  " + map.get(string));

		}

	}

	/**
	 * 转换
	 */

	public void testtest1() {
		Calendar calendar = Calendar.getInstance();
		long str = calendar.getTimeInMillis();

		String str2 = String.valueOf(str);
		int i = Integer.parseInt("123");
		System.err.println(str2.substring(2));
		System.err.println(i);

	}

	/***
	 * string list
	 */
	public void tsetstring() {
		// String string = "apple";
		//
		// char[] c = string.toCharArray(); // apple 转换成字符数组
		//
		// System.out.println(c[0]);
		//
		// System.out.println(string.charAt(2)); // 获取某一个字符
		//
		// System.out.println(string.length());// string 的长度
		//
		// String[] strings = { "a", "b" };
		// System.out.println(strings.length); // string[] 数组的长度
		//
		// String[] aString = { "1", "2" };
		// List list1 = Arrays.asList(aString);
		// for (Object object : list1) {
		// System.out.println(object);
		// }

		int a[] = { 1, 6, 3, 3, 4, 5 };

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i : a) {
			arrayList.add(i);
		}
		for (Integer integer : arrayList) {
			System.out.println(integer);
		}

		Collections.sort(arrayList);//object需要实现compareable接口才能排序
		arrayList.sort(null);
		Collections.reverse(arrayList); // 反向排序
		System.out.println(arrayList);
		String aString = "ilikejava";
		System.out.println(aString.substring(2, 5));

		ArrayList<String> lStrings = new ArrayList<String>();
		List<Integer> barrayList = new ArrayList<Integer>();

		// Collections.addAll(barrayList,"1,2,3");
		// barrayList=(ArrayList<String>) arrayList.clone();

		// Collections.swap(arrayList, 0, 4);
		System.out.println(barrayList);
		System.out.println(Collections.binarySearch(arrayList, 1));
	}


	public static void main(String[] args) throws MalformedURLException {

		// String aString=" i am ( man ";
		// String bString=aString.replace(" ", "");
		// String cString=aString.trim();
		// System.out.println(bString);
		// System.out.println(cString);
		// System.out.println((aString.substring(0,aString.indexOf("("))));
		// int[] a={1,2,3};
		// //System.out.println(Arrays.binarySearch(a, 1));
		// System.out.println(Arrays.toString(a));
		// Arrays.fill(a,6);
		// System.out.println(Arrays.toString(a));
		// Arrays.asList(a);
		String string = "abcdef";
		String reverse = new StringBuffer(string).reverse().toString();
		System.out.println("String before reverse: " + string);
		System.out.println("String after reverse:" + reverse);
		
		 int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 711, 8, 9};
         int num = 711;

         //Arrays类的使用
         //binarySearch方法使用
         System.out.println(Arrays.binarySearch(a , num));
         System.out.println(Arrays.binarySearch(a , 6, 8,num));

         //copyOf方法使用
         int[] b = Arrays.copyOf(a , 5);
		for (int i : b) {
			System.out.println(i);
		}
		Arrays.fill(b, 0,3,9);
		for (int i : b) {
			System.out.println(i);
		}
	}
	
	
//
//	String str = Integer.toString(456); //获取数字的十进制表示
//	String str2 = Integer.toBinaryString(456); //获取数字的二进制表示
//	String str3 = Integer.toHexString(456); //获取数字的十六进制表示
//	String str4 = Integer.toOctalString(456); //获取数字的八进制表示
//	System.out.println("'456'的十进制表示为："+str);
//	System.out.println("'456'的二进制表示为："+str2);
//	System.out.println("'456'的十六进制表示为："+str3);
//	System.out.println("'456'的八进制表示为："+str4);

}
