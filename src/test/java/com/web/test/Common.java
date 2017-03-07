package com.web.test;

import java.io.*;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.StringTokenizer;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.web.utils.WebHelper;
@Listeners({Linster1.class})
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
	public void testCollcetion() throws IOException {
		String str = "英语 语文 化学";
		// StringTokenizer st = new StringTokenizer(str);
		// System.out.println(str + "\n课程数为：" + st.countTokens());
		// while (st.hasMoreTokens()) {
		// System.out.println(st.nextToken()); java.io.File file =new
		// java.io.File("d:\\demo.txt");

		FileWriter fw = new FileWriter(file);
		fw.write("abcdec");
		// fw.flush();
		fw.write("kkkk你好啊");
		fw.close();

		int ch = 0;
		FileReader fr = new FileReader(file);
		BufferedReader bReader = new BufferedReader(fr);
		String string ;
		while ((string = bReader.readLine()) != null) {
			System.out.println(string);
		}

	}

}
