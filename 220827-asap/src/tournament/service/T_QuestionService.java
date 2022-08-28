package tournament.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import tournament.dao.T_QuestionDAO;
import tournament.model.Question;


public class T_QuestionService {
	public T_QuestionDAO tournamentQ = new T_QuestionDAO();
	
	//topic_id로 받은 question 객체를 list로 리턴하는 메소드 
	public List<Question> t_listQuestion(int what){
		try(Connection conn= ConnectionProvider.getConnection()){
			List<Question> question = new ArrayList<>(tournamentQ.readQuestion(conn,what));
			return question;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//question_id로 받은 question 객체를 리턴하는 메소드 
	public Question q_listQuestion(int what){
		try(Connection conn= ConnectionProvider.getConnection()){
			return tournamentQ.selectById(conn,what);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
