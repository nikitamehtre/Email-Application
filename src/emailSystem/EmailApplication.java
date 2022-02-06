package emailSystem;

import java.util.*;

public class EmailApplication {
	static int mailboxcapacity = 100;
//	static int lastEmailIndex = 0;
	static String currentUser;
	
	static List<Email> email_list = new ArrayList<Email>();  
	static HashMap<String, User> user_list = new HashMap<>();
	
	// 1.1
	public static void login_user() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Email ID: ");
		String emailID = scan.nextLine();
		System.out.println("Enter your Password: ");
		String password = scan.nextLine();
		
		User user = user_list.get(emailID);
		if(user == null ) {
			System.out.println("User not found, Enter correct credentials.\n");
			login_user();	
		} else if(user.check(password)) {
			System.out.println("Login Successful!");
			currentUser = user.emailID;
		} else {
			System.out.println("Try again..");
			login_user();
		}
	}
	
	public static void login_menu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Select one of the options: ");
		System.out.println("1. Display Inbox");
		System.out.println("2. Display Sentbox");
		System.out.println("3. Send email");
		System.out.println("4. Logout");
		System.out.println("5. Exit");
		int option = scan.nextInt();

		switch(option) {
			case 1:
				display_emails("received");
				login_menu();
				break;
			case 2:
				display_emails("sent");
				login_menu();
				break;
			case 3:
				send_email();
				login_menu();
				break;
			case 4:
				mainMenu();
				break;
			case 5:
				System.out.println("Thank you for using Email System");
				System.out.println("Bye bye!!");
				System.exit(0);
				break;
		}
	}
	
	// 1.2
	public static void change_password() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Email ID: ");
		String emailID = scan.nextLine();
		System.out.println("Enter your Password: ");
		String password = scan.nextLine();
		
		User user = user_list.get(emailID);
		
		if(user.check(password)) {
			System.out.println("Enter new password: ");
			String newPassword = scan.nextLine();
			user.changePassword(newPassword);
		} else {
			System.out.println("Wrong password, try again!");
			change_password();
		}
		System.out.println("Password changed!");
	}
	public static void alter_email() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Email ID: ");
		String emailID = scan.nextLine();
		System.out.println("Enter your Password: ");
		String password = scan.nextLine();
		
		User user = user_list.get(emailID);
		
		if(user.check(password)) {
			System.out.println("Enter alternate emailID: ");
			String AltEmail = scan.nextLine();
			user.setalternateEmail(AltEmail);
		}else {
			System.out.println("Wrong password, try again!");
			alter_email();
		}
	}	
	
	private static void populate_demo_data() {
		// create demo emails
		for(int i = 1; i < 6; i++) {
			String receiver = "nikita@gmail.com";
			String sender = "email-"+i+"@gmail.com";
			String subject = "Test Result";
			String body = "Congrats You Passed!!";
			
			Email email = new Email(sender, receiver, subject, body);
			email_list.add(email);
		}
		
		//create demo users
		String email = "nikita@gmail.com";
		String pass = "12345";
				
		User user = new User(email, pass);
		user_list.put(email, user);
	}
	
	private static void display_emails(String type) {		
		List<Email> emails = get_my_emails(type);
		
		System.out.println("Your " + type + " emails -");
		
		String[] header = new String[] { "From", "Subject", "Body" };
		String[] hr = new String[] { "-------------------------", "-------------------------", "-------------------------" };
		System.out.format("%-25s|%-25s|%-25s%n", hr);
		System.out.format("%-25s|%-25s|%-25s%n", header);
		System.out.format("%-25s|%-25s|%-25s%n", hr);

		for(Email email:emails) {	
			System.out.format("%-25s|%-25s|%-25s%n", email.getRow());				
		}
		System.out.format("%-25s|%-25s|%-25s%n", hr);

	}
	
	public static List<Email> get_my_emails(String type) {
		List<Email> results = new ArrayList<Email>(); 

		for(Email email:email_list) {
			if(type.equals("sent")) {
				if(currentUser.equals(email.sender)) {
					results.add(email);
				}
			} else if(type.equals("received")) {
				if(currentUser.equals(email.receiver)) {
					results.add(email);
				}
			}
		}
		
		return results;
	}
	
	public static void send_email() {
		if(email_list.size() >= mailboxcapacity) {
			System.out.println("Email system is full !!!");
			System.out.println("Now! You can not send emails.");
			return;
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter receiver email: ");
		String receiver = scan.nextLine();
		System.out.println("Enter subject: ");
		String subject = scan.nextLine();
		System.out.println("Enter body: ");
		String body = scan.nextLine();
		
		Email email = new Email(currentUser, receiver, subject, body);
		email_list.add(email);
		
		System.out.println("Email sent successfully!");
	}

	public static void mainMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Select one of the options: ");
		System.out.println("1. Login");
		System.out.println("2. Change password");
		System.out.println("3. Set Alternate email");
		System.out.println("4. Check capacity of Mailbox");
		System.out.println("5. Exit");
		int option = scan.nextInt();

		switch(option) {
			case 1:
				login_user();
				login_menu();
				break;
			case 2:
				change_password();
				mainMenu();
				break;
			case 3:
				alter_email();
				mainMenu();
				break;
			case 4:
				System.out.println("Email capacity used: ("+ email_list.size() + '/' + mailboxcapacity + ")");
				mainMenu();
				break;
			case 5:
				System.out.println("Thank you for yousing Email System");
				System.out.println("Bye bye!!");
				System.exit(0);
				break;
		}
		
			
		
	}
	
	public static void main(String[] args) {
		populate_demo_data();

		System.out.println("Email System:");
		System.out.println("=============================");
		
		mainMenu();
	}

}

