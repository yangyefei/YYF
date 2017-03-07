package com.web.test;


import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.Test;



/**
 * 
 * @author yyf
 *
 */
public class MySqlDao {

	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	@Test
	public void find() {
		String  sql="SELECT project_name FROM interview WHERE project_name LIKE 'Ricardo Liu' ORDER BY id DESC LIMIT 1";
		String  result= (String) JdbcTemplate.queryForObject(sql, String.class);
		System.out.println(result);
//		ApplicationContext ctx=new
//		ClassPathXmlApplicationContext("classpath*:ApplicationContext.xml");
//		 Cat cat=(Cat) ctx.getBean("cat");
//	throw new RuntimeException("yichang");
		// animal.anmialeat();
//Set<String> set = new HashSet<String>();
//set.add("a");
//set.add("ba");
//set.add("c");
//set.add("d");
//	for (String string : set) {
//		System.out.println(string);
	}
		
	
}
