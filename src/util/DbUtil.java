package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DbUtil {
	private static String dbUrl = "jdbc:mysql://localhost:3306/bbs";
	private static String dbUserName = "root";
	private static String dbPassword = "123456";
	private static String jdbcName = "com.mysql.jdbc.Driver";
	
	
	/**
	 * 获取数据库连接
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getConn() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	/**
	 * 关闭连接
	 * @param con
	 * @throws Exception
	 */
	public void close(Connection con) throws Exception{
		if(con!=null) {
			con.close(); 
		}
	}
	/**
	 * 关闭连接
	 * @param con
	 * @throws Exception
	 */
	public void close(Statement stmt, Connection con) throws SQLException {
		if (stmt != null) {
			stmt.close();
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 关闭连接
	 * @param con
	 * @throws Exception
	 */
	public void close(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt!=null) {
			pstmt.close();
			if(con!=null) {
				con.close();
			}
		}
	}
	
}
