package Questions;

import java.util.ArrayList;

import Answer.Answer;

public abstract class Question {
	private int questionId;
	private int quizId;
	private int questionType;
	private String question;
	private int questionN;
	
	public Question(int questionType, String question, int questionN) {
		this.questionType = questionType;
		this.question = question;
		this.questionN = questionN;
	}
	
	public abstract ArrayList<Answer> getAnswers();
	public abstract boolean isCorrect(Answer answer);
	public abstract Answer getCorrectAnswer();
	
	public int getQuestionId() {
		return this.questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	public int getQuestionType() {
		return this.questionType;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public int getQuestionN() {
		return this.questionN;
	}
	@Override
	public String toString() {
		return this.question;
	}
}
