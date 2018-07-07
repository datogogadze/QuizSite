package Message;

public class Challenge extends Message{
	private boolean Challenged;
	private static int typeID = 2;
	private String completeDate = "NOT COMPLETED YET";
	private int quizId;
	
	public Challenge(int sender, int reciever, int quizId) {
		super(sender, reciever, typeID, null);
		this.Challenged = true;
		Challenged = true;
		this.quizId = quizId;
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public void completeChallange(String date) {
		Challenged = false;
		completeDate = date;
	}
	
	public String getCompleteDate() {
		return completeDate;
	}
	
	public boolean isCompleted() {
		return !Challenged;
	}
	
	
}