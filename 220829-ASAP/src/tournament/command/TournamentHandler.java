package tournament.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tournament.service.T_QuestionService;

public class TournamentHandler implements CommandHandler {
	private T_QuestionService plz = new T_QuestionService();

	//파라미터로 토픽아이디를 받아주어야 함. 
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//여기서 같이 라디오 버튼의 이름을 넘겨준다
		List<String> name = new ArrayList<>();
		int j=0;
		for(int i=0; i< plz.t_listQuestion(1).size() ; i++) {
			name.add("name"+j);
			if((i+1)%2==0) {
				j++;
			}
		}
//		System.out.println(name.toString());
		req.setAttribute("name", name);//라디오 버튼 그룹 지정을 위한 리스트 이름 
		//질문 객체를 넘겨준다. 
		req.setAttribute("question", plz.t_listQuestion(1));
		  
		 return "/WEB-INF/worldcup/startQuestion.jsp";
	}
	
	

}