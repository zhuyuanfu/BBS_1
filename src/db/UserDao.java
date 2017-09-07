package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.User;
import util.DbUtil;

public class UserDao {
	private static DbUtil dbUtil = new DbUtil();
	
	
	/**
	 * 插入一条用户记录
	 * @param user
	 * @return
	 */
	public int insert(User user) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO user (name, password, sex, age, address, email, phone, introduction, is_admin) VALUES (?,?,?,?,?,?,?,?,?);");
			pstmt.setString(1,user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getSex());
			pstmt.setInt(4, user.getAge());
			pstmt.setString(5, user.getAddress());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getPhone());
			pstmt.setString(8, user.getIntroduction());
			pstmt.setBoolean(9, user.isAdmin());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除一条用户记录
	 * @param user
	 * @return
	 */
	public int delete(User user) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM user WHERE userId = ?;");
			pstmt.setInt(1, user.getUserId());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
	 * 通过用户名删除用户
	 * @param user
	 * @return
	 */
	public int deleteByUserName(User user) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM user WHERE name = ?;");
			pstmt.setString(1, user.getUserName());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过用户名删除用户
	 * @param name
	 * @return
	 */
	public int deleteByUserName(String name) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM user WHERE name = ?;");
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 列出所有用户
	 * @return 
	 */
	public ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT userid, name, password, sex, age, address, email, phonem, introduction, is_admin FROM user;");
			//PreparedStatement pstmt = con.prepareStatement("SELECT * FROM back;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setIntroduction(rs.getString("introduction"));
				user.setAdmin(rs.getBoolean("is_admin"));
				
				users.add(user);
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	/**
	 * 通过用户名和密码查找用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User getUserByUserNameAndPassword(String userName, String password) {
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT userid, name, password, sex, age, address, email, phone, introduction, is_admin FROM user WHERE name = ? AND password = ?;");
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setIntroduction(rs.getString("introduction"));
				user.setAdmin(rs.getBoolean("is_admin"));
				
				return user;
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(User user) {
		return 0;
	}
}
