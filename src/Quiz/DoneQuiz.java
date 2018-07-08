package Quiz;

public class DoneQuiz {
	private int userId;
	private int quizId;
	private int score;
	private String date;
	private int timeSpent;
	
	public DoneQuiz(int userId, int quizId, int score, String date, int timeSpent) {
		this.userId = userId;
		this.quizId = quizId;
		this.score = score;
		this.date = date;
		this.timeSpent = timeSpent;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getTimeSpent() {
		return this.timeSpent;
	}
}
