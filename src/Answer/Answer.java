package Answer;

public class Answer {
	private int answerId;
	private int questionId;
	private boolean answerCorrect;
	private String possibleAnswer;
	
	public Answer(boolean answerCorrect, String possibleAnswer) {
		this.answerCorrect = answerCorrect;
		this.possibleAnswer = possibleAnswer;
	}
	
	public int getAnswerId() {
		return this.answerId;
	}
	
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	
	public int getQuestionId() {
		return this.questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public boolean isAnswerCorrect() {
		return this.answerCorrect;
	}
	
	public void setAnswerCorrect(boolean answerCorrect) {
		this.answerCorrect = answerCorrect;
	}
	
	public String getPossibleAnswer() {
		return this.possibleAnswer;
	}
	
	public void setPossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}
	
	public boolean isGivenAnswerCorrect(String answer) {
		return this.answerCorrect && answer.toUpperCase().equals(this.possibleAnswer);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Answer ans = (Answer) obj;
		return this.answerCorrect == ans.answerCorrect 
			&& this.possibleAnswer.equals(ans.possibleAnswer);
	}
	@Override
	public String toString() {
		return this.possibleAnswer;
	}
}
