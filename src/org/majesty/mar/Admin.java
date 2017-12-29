package org.majesty.mar;

import java.util.Scanner;

public class Admin {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		UserGod root = new UserGod();
		User admin = new User();
		String opt = "";

		while(true) {

			System.out.println("==================== \n"
					+   "Administration panel \n" 
					+	"==================== \n"
					+	"Type: \n" 
					+	">add - to create new user \n" 
					+	">delete - to remove user \n" 
					+	">edit - to edit existing user \n"
					+   ">all - prints all users \n"
					+	">quit - to exit application");

			opt = scan.nextLine();

			if (opt.equals("add")) {

				System.out.println("Input username");
				String userName = scan.nextLine();
				admin.setUsername(userName);

				System.out.println("Input user email");
				String email = scan.nextLine();
				admin.setEmail(email);

				System.out.println("Input user password");
				String pass = scan.nextLine();
				admin.setPassword(pass);

				System.out.println("Adding...");
				System.out.println("User added");
				System.out.println("");

				root.create(admin);

			}else if (opt.equals("delete")) {

				System.out.println("Select id of user you want to delete");
                root.findAll();
				Integer userId = scan.nextInt();

				System.out.println("Deleting...");
				System.out.println("User deleted");
				System.out.println("");

				root.delete(userId);

			}else if (opt.equals("edit")) {

				System.out.println("Set new username");
				admin.setUsername(scan.nextLine());
				
				System.out.println("Set new email");
				admin.setEmail(scan.nextLine());

				System.out.println("Set new password");
				admin.setPassword(scan.nextLine());
				
				System.out.println("Input user ID");
				admin.setId(scan.nextInt());

				System.out.println("Updating...");
				System.out.println("User updated");
				System.out.println("");

				root.update(admin);

			}else if (opt.equals("quit")) {
				
				scan.close();
				System.exit(0);

			}else if (opt.equals("all")){

				System.out.println("Printing...");
				System.out.println("====================");
				System.out.println("All users in DB");
				System.out.println("====================");

				root.findAll();
				System.out.println("");

			}else {
				
				System.out.println("Wrong command");
				System.out.println("");
			}


		}
	}

}