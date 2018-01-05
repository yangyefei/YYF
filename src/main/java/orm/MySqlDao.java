package orm;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.annotation.Rollback;

/**
 * 
 * @author yyf
 *
 */

public class MySqlDao extends BaseTest {

	public MySqlDao() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private JdbcTemplate JdbcTemplate;

	// @Test
	// public void find() {
	// String sql = "SELECT roomname FROM hotels WHERE hotelid='123'";
	// String result = (String) JdbcTemplate.queryForObject(sql, String.class);
	// System.out.println(result);
	//
	// }
	/**
	 * 通过hotelid 更新数据库
	 */
	/**
	 * 
	 */
	@Test
	@Rollback(false) // 不回滚事物
	public void addData() {

		String sql = "update hotels set roomname='yangyefei999' where hotelid='112'";
		JdbcTemplate.update(sql);
//		String sql1 = "SELECT roomname FROM hotels WHERE hotelid='112'";
//		String result = (String) JdbcTemplate.queryForObject(sql1, String.class);
//
//		System.out.println(result);

	}

	@AfterClass
	public void teardown() {
		logger.info("释放资源");

	}
}
