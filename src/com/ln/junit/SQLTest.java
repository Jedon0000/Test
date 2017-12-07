package com.ln.junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ln.jdbc.utils.DbUtil;

public class SQLTest {
	
	private static Connection conn = null;
	private static PreparedStatement pre = null;
	private static ResultSet rs = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn = DbUtil.getConnection();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DbUtil.closeConnection(conn, pre, rs);
	}
	
	@Test
	public void test() throws Exception{
		String sql = "select * from user";
		pre = conn.prepareStatement(sql);
		rs = pre.executeQuery();
		assertTrue(rs.next());
	}

}
