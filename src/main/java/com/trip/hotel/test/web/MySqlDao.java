package com.trip.hotel.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * 
 * @author yyf
 *
 */
@Component
public class MySqlDao extends SpringBase{

	public MySqlDao() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Test
	public void find(String name) {
		String sql = String.format("SELECT project_name FROM interview WHERE project_name LIKE '%1$s' ORDER BY id DESC LIMIT 1",name);
		String result = (String) JdbcTemplate.queryForObject(sql, String.class);
		System.out.println(result);

	}

}
