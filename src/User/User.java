package User;

import PasswordCracker.Cracker;

import java.sql.SQLException;
import java.util.ArrayList;

import Achievement.Achievement;
import DAO.AchievementDao;
import DAO.FriendDao;
import DAO.UserDao;

public class User {
	private String name;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private int userId;
	private int type; //1 admin, 2 user.
	
	public User(String name, String lastName, String userName, String password, String email, int type) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.password = Cracker.generateHash(password);
		this.email = email;
		this.type = type;
	}
	
	public User(String name, String lastName, String userName, String password, String email) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.password = Cracker.generateHash(password);
		this.email = email;
		this.type = 2;
	}
	
	public String getName () {
		return this.name;
	}
		
	public String getLastName() {
		return this.lastName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getMail() {
		return this.email;
	}
	public String getPass() {
		return this.password;
	}
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setUserId(int ID) {
		this.userId = ID;
	}
	
	public int getId() {
		return this.userId;
	}
	
	public ArrayList<Achievement> getAchievements() {
		AchievementDao ad= new AchievementDao();
		ArrayList<Achievement> res = ad.getAchievementsByID(this.getId());
		ad.closeCon();
		return res;
	}
	
	public void addAchievement(Achievement achievement) {
		AchievementDao ad = new AchievementDao();
		ad.addAchievement(achievement);
		ad.closeCon();
	}
	
	public void  addFriend(String username) throws SQLException {
		FriendDao fd = new FriendDao();
		fd.addFriend(this.userId, new UserDao().getUserId(username));
		fd.closeCon();
	}
	public ArrayList<String> getFriends() {
		UserDao ud = new UserDao();
		ArrayList<String> friends = ud.getFriends(userId);
		ud.closeCon();
		return friends;
	}
	public void deleteFriend(String username) {
		FriendDao fd = new FriendDao();
		fd.deleteFriendship(this.getId(), new UserDao().getUserId(username));
		fd.closeCon();
	}
	
	@Override
	public String toString() {
		return this.userName + " " + this.email + " " + this.type + " " + this.userId;
	}
	
}
