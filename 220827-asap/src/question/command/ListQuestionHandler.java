package question.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import question.model.Question;
import question.service.QuestionPage;
import question.service.QuestionService;

public class ListQuestionHandler implements CommandHandler {
	QuestionService qService = new QuestionService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		QuestionPage questionPage = qService.getQuestionPage(1, pageNo);
		req.setAttribute("questionPage", questionPage);
		return "/WEB-INF/view/questionpage.jsp";
	}
	
}
