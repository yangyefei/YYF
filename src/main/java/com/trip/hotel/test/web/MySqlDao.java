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
	public void find() {
		String sql = "SELECT project_name FROM interview WHERE project_name LIKE 'Ricardo%' ORDER BY id DESC LIMIT 1";
		String result = (String) JdbcTemplate.queryForObject(sql, String.class);
		System.out.println(result);

	}

}
