 package Achievement;

public class Achievement {
	private String name;
	private int userId;
	private int achievementId;
	private String finishTime;
	
	public Achievement (String name, int userId) {
		this.name = name;
		this.userId = userId;
	}
	
	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}
	public void setFinishTime(String time) {
		this.finishTime = time;
	}
	
	public String getFinishTime() {
		return this.finishTime;
	}
	
	public String getAchievementName() {
		return this.name;
	}
	
	public int getUserID() {
		return this.userId;
	}
	
	public int getAchievementID() {
		return this.achievementId;
	}
	
}