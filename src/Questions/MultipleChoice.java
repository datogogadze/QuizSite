package Questions;

import java.util.ArrayList;

import Answer.Answer;

public class MultipleChoice extends Question {
	
	private Answer answer;
	private static final int MULTIPLECHOICEID = 3;
	private ArrayList<Answer> choices;

	public MultipleChoice(String question, ArrayList<Answer> choices, int questionN) {
		super(MULTIPLECHOICEID, question, questionN);
		// TODO Auto-generated constructor stub
		this.choices = choices;
		for(Answer ans : choices) {
			if(ans.isAnswerCorrect()) {
				this.answer = ans;
			}
		}
	}
	
	@Override
	public ArrayList<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return choices;
	}

	@Override
	public boolean isCorrect(Answer answer) {
		// TODO Auto-generated method stub
		return this.answer.equals(answer);
	}

	@Override
	public Answer getCorrectAnswer() {
		// TODO Auto-generated method stub
		return this.answer;
	}

}
