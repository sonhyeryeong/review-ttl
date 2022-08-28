package question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.model.Question;

public class QuestionDAO {
	private Question resultMapping_S(ResultSet rs) throws SQLException {
		int question_id = rs.getInt("question_id");
		int topic_id = rs.getInt("topic_id");
		String question_text = rs.getString("question_text");
		
		return new Question(question_id, topic_id, question_text);
	}
	
//	public List<Question> selectByTopicId(Connection conn, int topic_id) throws SQLException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<Question> questionList = new ArrayList<>();
//		try {
//			pstmt = conn.prepareStatement("SELECT * FROM question_t where topic_id = ?");
//			pstmt.setInt(1, topic_id);
//			rs= pstmt.executeQuery();
//			
//			while(rs.next()) {
//				questionList.add(resultMapping_S(rs));
//			}
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		return questionList;
//	}
	
	public int selectCount(Connection conn, int topic_id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM question_t where topic_id = ?");
			pstmt.setInt(1, topic_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Question> selectByTopicId(Connection conn, int topic_id, int startRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Question> questionList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM question_t where topic_id = ? limit ?,6");
			pstmt.setInt(1, topic_id);
			pstmt.setInt(2, startRow);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				questionList.add(resultMapping_S(rs));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return questionList;
	}
}
