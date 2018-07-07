package Message;

public abstract class Message {
	private int messageID;
	protected int sender;
	protected int reciever;
	protected String sendDate;
	private int messageType;
	private String seenDate = "NOT SEEN YET";
	
	public Message (int sender, int reciever, int TypeID, String sendDate) {
		this.sender = sender;
		this.reciever = reciever;
		this.messageType = TypeID;
		this.sendDate = sendDate;
	}
	
	public void setMessageID(int id) {
		this.messageID = id;
	}
	public int getMessageID(int id) {
		return messageID;
	}
	public int getSenderID() {
		return sender;
	}
	public int getRecieverID() {
		return reciever;
	}
	
	public void SEEN(String date) {
		this.seenDate = date;
	}
	public boolean isSeen() {
		return seenDate == "NOT SEEN YET";
	}
	
	
}