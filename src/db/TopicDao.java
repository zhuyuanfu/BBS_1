package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Topic;
import entity.User;
import util.DbUtil;

public class TopicDao {
	private static DbUtil dbUtil = new DbUtil();
	
	/**
	 * 插入一条帖子
	 * @param topic
	 * @return
	 */
	public int insert(Topic topic) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO topic (title, author, content ) VALUES (?,?,?);");
			pstmt.setString(1,topic.getTitle());
			pstmt.setString(2, topic.getAuthor());
			pstmt.setString(3, topic.getContent());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除一条主题
	 * @param topic
	 * @return
	 */
	public int delete(Topic topic) {
		int result = 0;
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM topic WHERE id = ?;");
			pstmt.setInt(1, topic.getId());
			result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id返回一条主题
	 * @param topicId
	 * @return
	 */
	public Topic getById(int topicId) {
		Topic topic = new Topic();
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT id, title, author, content, pubtime FROM topic WHERE id = ?;");
			pstmt.setInt(1, topicId);
			//PreparedStatement pstmt = con.prepareStatement("SELECT * FROM back;");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				topic.setId(rs.getInt("id"));
				topic.setTitle(rs.getString("title"));
				topic.setAuthor(rs.getString("author"));
				topic.setContent(rs.getString("content"));
				topic.setPubTime(rs.getString("pubtime"));
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topic;
	}
	
	/**
	 * 返回所有主题帖
	 * @return
	 */
	public ArrayList<Topic> getAll() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT id, title, author, content, pubtime FROM topic;");
			//PreparedStatement pstmt = con.prepareStatement("SELECT * FROM back;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Topic t = new Topic();
				t.setId(rs.getInt("id"));
				t.setTitle(rs.getString("title"));
				t.setAuthor(rs.getString("author"));
				t.setContent(rs.getString("content"));
				t.setPubTime(rs.getString("pubtime"));
				topics.add(t);
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return topics;
	}
	
	/**
	 * 返回某用户的所有主题帖
	 * @param user
	 * @return
	 */
	public ArrayList<Topic> getByUser(User user) {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		try {
			Connection con = dbUtil.getConn();
			PreparedStatement pstmt = con.prepareStatement("SELECT id, title, author, content, pubtime FROM topic WHERE author = ?;");
			pstmt.setString(1, user.getUserName());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Topic t = new Topic();
				t.setId(rs.getInt("id"));
				t.setTitle(rs.getString("title"));
				t.setAuthor(rs.getString("author"));
				t.setContent(rs.getString("content"));
				t.setPubTime(rs.getString("pubtime"));
				
				topics.add(t);
			}
			dbUtil.close(pstmt, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topics;
	}
}
