package Questions;

import java.util.ArrayList;

import Answer.Answer;

public class QuestionResponse extends Question {
	
	private Answer answer;
	private static final int QUESTIONRESPONCEID = 1;
	private ArrayList<Answer> answers;
	public QuestionResponse(String question, ArrayList<Answer> answers, int questionN) {
		super(QUESTIONRESPONCEID, question, questionN);
		// TODO Auto-generated constructor stub
		this.answers = answers;
	}

	@Override
	public ArrayList<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return answers;
	}

	@Override
	public boolean isCorrect(Answer answer) {
		// TODO Auto-generated method stub
		for(int i=0;i<answers.size();i++) {
			if(answers.get(i).equals(answer)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Answer getCorrectAnswer() {
		// TODO Auto-generated method stub
		for(Answer ans : answers) {
			if(ans.isAnswerCorrect()) {
				return ans;
			}
		}
		return null;
	}

}
