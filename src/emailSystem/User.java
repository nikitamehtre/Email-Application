package emailSystem;

public class User {
	String emailID;
	String password;
	String alternateEmail;
	
	public User(String email, String pass) {
		emailID = email;
		password = pass;
	}	
	
	public void setalternateEmail(String AltEmail) {
		this.alternateEmail = AltEmail;
	}
	
	public void changePassword(String newPassword) {
		this.password = newPassword;	
	}
	
	public boolean check(String pass) {
		if(this.password.equals(pass))
			return true;
		else
			return false;
	}		
	public boolean check1(String email) {
		if(this.alternateEmail.equals(email))
			return true;
		else
			return false;
	}
}
