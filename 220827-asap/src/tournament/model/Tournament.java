package tournament.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import tournament.dao.T_QuestionDAO;
import tournament.service.T_QuestionService;

public class Tournament {
	//다오로 질문 리스트를 불러와서 몇개인지 list사이즈로 참가자 순을 받는다. 
	private static T_QuestionService plz = new T_QuestionService();
	static T_QuestionDAO dao = new T_QuestionDAO();
	//question 객체를 list로 리턴하는 메소드 
	
	  public static List<Question> listQuestion(int what){
	  
		  try(Connection conn= ConnectionProvider.getConnection()){ 
			  List<Question>question = new ArrayList<>(dao.readQuestion(conn,what)); 
			  return question;
		  }catch(SQLException e) { 
			  throw new RuntimeException(e); 
		  }
	  }
	 
	
	
	
	public static void main(String[] args) {
		List<Question> list = plz.listQuestion(1);
		System.out.println(list.toString());
		
	}

}
