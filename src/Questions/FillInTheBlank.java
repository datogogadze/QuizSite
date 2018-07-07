package Questions;

import java.util.ArrayList;

import Answer.Answer;

public class FillInTheBlank extends Question {
	
	private Answer answer;
	private static final int FILLINTHEBLANKID = 2;
	
	public FillInTheBlank(String question, Answer answer, int questionN) {
		super(FILLINTHEBLANKID, question, questionN);
		// TODO Auto-generated constructor stub
		this.answer = answer;
	}

	@Override
	public ArrayList<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCorrect(Answer answer) {
		// TODO Auto-generated method stub
		return this.answer.equals(answer);
	}

	@Override
	public Answer getCorrectAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}

}
