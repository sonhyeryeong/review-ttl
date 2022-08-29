package tournament.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tournament.model.Question;
import tournament.service.T_QuestionService;

public class FirstHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/worldcup/selectQuestion.jsp";
	private T_QuestionService plz = new T_QuestionService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}	
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	
	
	
	
	//현재 클릭된 값만 받아서 선택 형태로 넘겨준다. 
	public String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//현재 클릭된 값만 넘겨 받아서 question_id를 int리스트에 넣는다. 
		List<Integer> first =new ArrayList<>(); 
		
		//리퀘스트로 받은 객체가 몇 개인지 알아야 얘를 for문을 돌리는데??-map메소드가 있당
		for(int i =0; i< req.getParameterMap().size();i++) {
			System.out.println(req.getParameter("name"+i));
			int what =Integer.valueOf(req.getParameter("name"+i));
			first.add(what);
		}
//		System.out.println(first.toString());
		
		System.out.println("이름리스트"+listname(first).toString());
		
		List<Question> question = new ArrayList<>();
		//
		for(int j =0; j<first.size(); j++) {
			question.add( plz.q_listQuestion(first.get(j)));
		}
		
		//선택한 질문 리스트(퀘스천 클래스), 라디오 버튼 이름 할 거 전부 넘긴다. 
		req.setAttribute("question", question);
		req.setAttribute("name", listname(first));
		return "/WEB-INF/worldcup/first.jsp";
		
	}
	
	//라디오버튼 이름-두개씩 똑같은 이름 만드는 메소드 
	public List<String> listname(List list){
		List<String> name = new ArrayList<>();
		int k=0;
		for(int i=0; i< list.size() ; i++) {
			name.add("name"+k);
			if((i+1)%2==0) {
				k++;
			}
		}
		return name;
	}
	
}
