package Questions;

import java.util.ArrayList;

import Answer.Answer;

public class PictureResponse extends Question {
	
	private Answer answer;
	private static final int PICTURERESPONCEID = 4;
	

	public PictureResponse(String pictureUrl, Answer answer, int questionN) {
		super(PICTURERESPONCEID, pictureUrl, questionN);
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
