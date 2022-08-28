package question.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import question.dao.QuestionDAO;
import question.model.Question;

public class QuestionService {
	private QuestionDAO qdao = new QuestionDAO();
	
//	public List<Question> getQuestionPage(int topic_id) {
//		try (Connection conn = ConnectionProvider.getConnection()) {
//			List<Question> questionList = qdao.selectByTopicId(conn, topic_id);
//			
//			return questionList;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null; 
//	}
	
	public QuestionPage getQuestionPage(int topic_id, int pageNum) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = qdao.selectCount(conn, topic_id);
			List<Question> question_context = qdao.selectByTopicId(conn, topic_id, pageNum - 1);
			
			return new QuestionPage(total, pageNum, question_context);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
