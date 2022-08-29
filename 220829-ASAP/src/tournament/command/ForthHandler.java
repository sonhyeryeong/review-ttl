package tournament.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import tournament.model.Question;
import tournament.service.T_QuestionService;

public class ForthHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/worldcup/third.jsp";
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
	
	
	//6개 중 3개 선택한 걸 받아서 2개/1개 넘기기. 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		//현재 클릭된 값만 넘겨 받아서 question_id를 int리스트에 넣는다. 
		List<Integer> second =new ArrayList<>(); 
		for(int i =0; i< req.getParameterMap().size();i++) {
			System.out.print("선택된 질문아이디: "+req.getParameter("name"+i));
			int what =Integer.valueOf(req.getParameter("name"+i));
			second.add(what);
		}//선택한 거를 질문아이디를 받아서  second리스트로 만들었다. -> 
		
		//한 문제가 남음. 부전승으로 보내야 된다. 
		//마지막 한 문제를 빼고 퀘스천리스트에 넣음
		List<Question> question = new ArrayList<>();
		for(int j =0; j<second.size()-1; j++) {
			question.add( plz.q_listQuestion(second.get(j)));
		}
		//마지막 한 문제를 퀘스천 객체로 만듬. 
		Question walkOver = plz.q_listQuestion(second.get(second.size()-1));
		
		
		//선택한 질문 리스트(퀘스천 클래스), 라디오 버튼 이름 할 거 전부 넘긴다. 
		req.setAttribute("question", question);//질문 보내기.
		req.setAttribute("name", listname(second));//라디오버튼 이름 보내기
		req.setAttribute("walkOver", walkOver);
		return "/WEB-INF/worldcup/forth.jsp";
				
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
