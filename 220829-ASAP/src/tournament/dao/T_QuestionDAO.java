package tournament.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import tournament.model.Question;

public class T_QuestionDAO {
	 
	private Question resultMapping_S(ResultSet rs) throws SQLException {
	      int question_id = rs.getInt("question_id");
	      int topic_id = rs.getInt("topic_id");
	      String question_text = rs.getString("question_text");
	      
	      return new Question(question_id, topic_id, question_text);
	}
	
	//-다오 안 메소드에서 쓰기 위해 만든 메소드- 하나하나 question객체에 넣는 매소드
	private Question convertQuestion(ResultSet rs) throws SQLException {
		return new Question(rs.getInt("question_id"),rs.getInt("topic_id"),rs.getString("question_text"));
	}

	
	//topic_id를 파라미터로 받아서, 리스트- Question 클래스의 값을 만들어서 리턴한다.    
	public List<Question> readQuestion(Connection conn, int number) throws SQLException{
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try {
			   pstmt = conn.prepareStatement("select * from question_t where topic_id = ?");
			   pstmt.setInt(1,number);
			   rs = pstmt.executeQuery();
			   List<Question> tournament  = new ArrayList<>();
			   while(rs.next()) {
				   tournament.add(convertQuestion(rs));
			   }
			   return tournament;
		   }finally {
			   JdbcUtil.close(rs);
			   JdbcUtil.close(pstmt);
		   }
		}
	
	
	
	//question_id로  찾아서 또 question 클래스를 만든다. 
	public Question selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from question_t where question_id = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Question q = null;
			if (rs.next()) {
				q = convertQuestion(rs);
			}
			return q;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
/*	
	//전체 질문 수가 몇개인지 알기 위해 만든 쿼리문->확인 완료!!!!값 제대로 나옴. 
   public int selectCount(Connection conn, int number) throws SQLException{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try {
		   pstmt = conn.prepareStatement("select count(*) from question_t where topic_id = ?");
		   pstmt.setInt(1,number);
		   rs = pstmt.executeQuery();
		   if(rs.next()) {
			   return rs.getInt(1);
		   }
		   return 0;
	   }finally {
		   JdbcUtil.close(rs);
		   JdbcUtil.close(pstmt);
		   
	   }
		   
	}

   public List<Article> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article " +
					"order by article_no desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
   
   /*   
	private Article convertArticle(ResultSet rs) throws SQLException {
		return new Article(rs.getInt("article_no"),
				new Writer(
						rs.getString("writer_id"),
						rs.getString("writer_name")),
				rs.getString("title"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("read_cnt"));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update article set read_cnt = read_cnt + 1 "+
						"where article_no = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String title) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update article set title = ?, moddate = now() "+
						"where article_no = ?")) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
   
   
	   
	   
	   
	   
	   
   }
   
   
   
   
   
   public List<Question> selectByTopicId(Connection conn, int topic_id) throws SQLException {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<Question> questionList = new ArrayList<>();
      try {
         pstmt = conn.prepareStatement("SELECT * FROM question_t where topic_id = ?");
         pstmt.setInt(1, topic_id);
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
   
 
   
   */
}