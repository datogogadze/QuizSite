package Quiz;

import java.util.ArrayList;
import java.util.List;

import Questions.Question;

public class Quiz {
	private String quizName;
	private String quizDescription;
	private int quizId;
	private int quizNumberOfQuestions;
	private List<Question> quizQuestions;
	private int authorUser;
	
	private boolean multiPageQuiz;
	private boolean randomizedQuiz;
	
	public Quiz(String quizName, String quizDescription, List<Question> quizQuestions) {
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.quizQuestions = quizQuestions;
		this.multiPageQuiz = false;
		this.randomizedQuiz = false;
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public void setAuthor(int authorUser) {
		this.authorUser = authorUser;
	}
	public int getQuizAuthor() {
		return this.authorUser;
	}
	
	public String getQuizName() {
		return this.quizName;
	}
	
	public void setQuizDescription(String description) {
		this.quizDescription = description;
	}
	
	public String getQuizDescription() {
		return this.quizDescription;
	}
	
	public List<Question> getQuizQuestions(){
		return this.quizQuestions;
	}
	
	public Question getQuestion(int ind) {
		return this.quizQuestions.get(ind);
	}
	
	public int getNumberOfQuestions() {
		return this.quizNumberOfQuestions;
	}
	
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	public void setMultiPageQuiz(boolean bool) {
		this.multiPageQuiz = bool;
	}
	
	public void setRandomizedQuiz(boolean bool) {
		this.randomizedQuiz = bool;
	}
	
	public boolean isMultiPageQuiz() {
		return this.multiPageQuiz;
	}
	
	public boolean isRandomizedQuiz() {
		return this.randomizedQuiz;
	}
	
	public boolean addQuestion(Question q) {
		if(!this.quizQuestions.contains(q)) {
			this.quizQuestions.add(q);
			this.quizNumberOfQuestions++;
			return true;
		}
		return false;
	}
	
	public boolean removeQuestion(Question q) {
		if(this.quizQuestions.contains(q)) {
			this.quizQuestions.remove(this.quizQuestions.indexOf(q));
			this.quizNumberOfQuestions--;
			return true;
		}
		return false;
	}
	
	public int getMultipleChoice() {
		int ans = 0;
		for(int i=0;i<this.quizQuestions.size();i++) {
			Question cur = this.quizQuestions.get(i);
			if(cur.getQuestionType() == 3) {
				ans++;
			}
		}
		return ans;			
	}
	
	@Override
	public String toString() {
		return this.quizName + " " + this.quizDescription + " " + this.quizId + " " + this.quizNumberOfQuestions;
	}
	
}
