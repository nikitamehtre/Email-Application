package emailSystem;

public class Email {
	String sender;
	String receiver;
	String subject;
	String body;
	
	public Email(String sender2, String receiver2, String subject2, String body2) {
		sender = sender2;
		receiver = receiver2;
		subject = subject2;
		body = body2;
	}
	
	public String[] getRow() {
		String[] row = new String[3];
		row[0] = sender;
		row[1] = subject;
		row[2] = body;
		
		return row;
	}
}
