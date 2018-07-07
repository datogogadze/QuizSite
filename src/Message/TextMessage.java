package Message;

public class TextMessage extends Message{
	private String text;
	private static int typeID = 1;
	public TextMessage (int sender, int reciever, String text, String sendDate) {
		super(sender, reciever, typeID, sendDate);
		this.text = text;
	}
	public String getText() {
		return text;
	}
}