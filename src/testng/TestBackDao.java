package testng;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import db.BackDao;
import entity.Back;
import util.DbUtil;

public class TestBackDao {
	DbUtil dbUtil = null;
	
	@BeforeClass
	public void setUp() {
		this.dbUtil = new DbUtil();
	}

	@Test
	public void testInsert() {
		BackDao backDao = new BackDao();
		Back back  = new Back();
		back.setTopicId(1);
		back.setTitle("title1");
		back.setAuthor("author1");
		back.setContent("我们爱java");
		Assert.assertEquals(backDao.insert(back), 1);
	}
	
	@Test
	public void testGetAll() {
		BackDao backDao = new BackDao();
		List list = backDao.getAll();
		Assert.assertEquals(true, list.size()>0);
	}
	
	
	@AfterClass
	public void destroy() {
		this.dbUtil = null;
	}
}
