package tournament.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tournament.model.Question;
import tournament.service.T_QuestionService;

public class FirstHandler implements CommandHandler {
	private T_QuestionService plz = new T_QuestionService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//현재 클릭된 값만 넘겨 받아서 question_id를 int리스트에 넣는다. 
		List<Integer> first =new ArrayList<>(); 
		
		//리퀘스트로 받은 객체가 몇 개인지 알아야 얘를 for문을 돌리는데??-map메소드가 있당
		for(int i =0; i< req.getParameterMap().size();i++) {
			System.out.println(req.getParameter("name"+i));
			int what =Integer.valueOf(req.getParameter("name"+i));
			first.add(what);
		}
		System.out.println(first.toString());
		
		List<Question> list = new ArrayList<>();
		//
		for(int j =0; j<first.size(); j++) {
			list.add( plz.q_listQuestion(first.get(j)));
		}
		req.setAttribute("list", list);
		return "/WEB-INF/view/whatis.jsp";
		
	}
	
	
	
}
