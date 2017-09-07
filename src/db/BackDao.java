package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Back;
import entity.User;
import util.DbUtil;

public class BackDao {
	private static DbUtil dbUtil = new DbUtil();
	
	/**
	 * 插入一条回复
	 * @param back
	 * @return 插入行数
	 */
	@SuppressWarnings("finally")
	public int insert(Back back) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("insert into back (topicId, title, author, content) values (?,?,?,?);");
			pstmt.setInt(1, back.getTopicId());
			pstmt.setString(2,back.getTitle());
			pstmt.setString(3, back.getAuthor());
			pstmt.setString(4, back.getContent());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return result;
		}
	}
	
	/**
	 * 删除一条回复
	 * @param back
	 * @return 删除数目
	 */
	@SuppressWarnings("finally")
	public int delete(Back back) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM back WHERE id = ?;");
			pstmt.setInt(1, back.getId());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return result;
		}
	}

	/**
	 * 根据id删除一条回复
	 * @param back
	 * @return 删除数目
	 */
	@SuppressWarnings("finally")
	public int deleteById(int backId) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("delete from back where id = ?;");
			pstmt.setInt(1, backId);
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return result;
		}
	}
	
	/**
	 * 返回所有回复
	 * @return
	 */
	public ArrayList<Back> getAll() {
		ArrayList<Back> backs = new ArrayList<Back>();
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT id, topicId, title, author, content, pubtime FROM back;");
			//PreparedStatement pstmt = con.prepareStatement("SELECT * FROM back;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Back back = new Back();
				back.setId(rs.getInt("id"));
				back.setTopicId(rs.getInt("topicId"));
				back.setTitle(rs.getString("title"));
				back.setAuthor(rs.getString("author"));
				back.setContent(rs.getString("content"));
				back.setPubTime(rs.getString("pubtime"));
				
				backs.add(back);
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return backs;
	}
	
	
	public Back getBackById(int id) {
		return null;
	}
	
	/**
	 * 返回某用户所有的回复
	 * @param user
	 * @return
	 */
	public ArrayList<Back> getBackByUser(User user) {
		ArrayList<Back> backs = new ArrayList<Back>();
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT id, topicId, title, author, content, pubtime FROM back where author = ?;");
			pstmt.setString(1, user.getUserName());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Back back = new Back();
				back.setId(rs.getInt("id"));
				back.setTopicId(rs.getInt("topicId"));
				back.setTitle(rs.getString("title"));
				back.setAuthor(rs.getString("author"));
				back.setContent(rs.getString("content"));
				back.setPubTime(rs.getString("pubtime"));
				
				backs.add(back);
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return backs;
	}
	
	
}
