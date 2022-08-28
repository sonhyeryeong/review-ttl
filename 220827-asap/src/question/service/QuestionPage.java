package question.service;

import java.util.List;

import question.model.Question;

public class QuestionPage {
	private int total;
	private int currentPage;
	private List<Question> question_context;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public QuestionPage(int total, int currentPage, List<Question> question_context) {
		this.total = total;
		this.currentPage = currentPage;
		this.question_context = question_context;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = (total / 2) / 6;
			if ((total / 2) % 6 > 0) {
				totalPages++;
			}
			int modVal = currentPage % 6;
			startPage = currentPage / 6 * 6 +1;
			if (modVal == 0) startPage -= 6;
			
			endPage = startPage + 5;
			if (endPage > totalPages) endPage = totalPages;
		}
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Question> getQuestion_context() {
		return question_context;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	
	
	
	
}
