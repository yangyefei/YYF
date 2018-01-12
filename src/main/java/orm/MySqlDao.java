package orm;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;



/**
 * @author yefeiyang
 *
 */
public class MySqlDao extends BaseTest {

	public MySqlDao() throws MalformedURLException {
		super();
	}

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Test
	public void find() {
		String sql = "SELECT room FROM ctrip WHERE hotelid='1'";
		String result = (String) JdbcTemplate.queryForObject(sql, String.class);
		System.out.println(result);
	}

	/**
	 * 通过hotelid 更新数据库
	 */

	@Test
	@Rollback(true) // 不回滚事物
	public void addData() {
		String sql = "update ctrip set room='yefeiyang' where hotelid='1'";
		JdbcTemplate.update(sql);
		String sql1 = "SELECT room FROM ctrip WHERE hotelid='1'";
		String result = (String) JdbcTemplate.queryForObject(sql1, String.class);
		System.out.println(result);
	}

	@AfterClass
	public void teardown() {
		logger.info("释放资源");
	}
}
