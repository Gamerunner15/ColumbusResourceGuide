package project.Kolton.logic;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.client.RestTemplate;

import project.Kolton.objects.Address;
import project.Kolton.objects.User;
import projects.Kolton.view.MainMenu;

public class AppCLI {

	private Scanner userScanner = new Scanner(System.in);
	private MainMenu mainMenu;
	private Controller controller;
	private User user;
	private final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {

		AppCLI cli = new AppCLI();

		cli.run(cli);

	}

	public void run(AppCLI cli) {

		mainMenu = new MainMenu(cli);
		controller = new Controller(mainMenu);

		boolean loop = true;
		while (loop) {
			loop = mainMenu.start();
		}

	}

	public boolean startHandler(String response) {
		if (response.equals("1")) {
			if (this.logIn() == false) {
				return true;
			} else {
				boolean keepGoing = true;
				while (keepGoing) {
					keepGoing = mainMenu.menuOptions();
				}
			}
			return true;

		} else if (response.equals("2")) {
			controller.createUser(this.createAccount());
			System.out.println("Success!");
			return true;

		} else if (response.equalsIgnoreCase("Q")) {
			System.out.println("Goodbye!");
			return false;
		} else {
			System.out.println("Please enter a valid response.");
			return true;
		}
	}

	public boolean menuOptionsHandler(String response) {
		if (response.equals("1")) {
			this.updateInformationMenu();
			return true;
		} else if (response.equals("2")) {
			this.printAllInfo();
			return true;
		} else if (response.equals("3")) {
			
			return true;
		} else if (response.equalsIgnoreCase("R")) {
			return false;
		} else {
			System.out.println("Please select a valid option.");
			return true;
		}
	}

	public boolean logIn() {
		System.out.print("Username: ");
		String username = userScanner.nextLine();
		System.out.print("Password: ");
		String password = userScanner.nextLine();
		boolean valid = controller.validatePassword(username, password);

		if (!valid) {
			System.out.println("Invalid username or password.");
			return false;
		} else {
			user = controller.getUserInfo(username);
			return true;
		}
	}

	public User createAccount() {
		boolean isValid = false;
		String username = null;
		while (!isValid) {
			System.out.println("Enter your desired Username:");
			username = userScanner.nextLine();
			isValid = controller.validateUsername(username);
			if (!isValid) {
				System.out.println("That Username is taken. Please try again.");
			}
		}
		System.out.println("Enter your password:");
		String password = userScanner.nextLine();

		System.out.println("Enter your first name:");
		String firstName = userScanner.nextLine();

		System.out.println("Enter your last name:");
		String lastName = userScanner.nextLine();
		
		String birthDate = "";
		isValid = false;
		while(!isValid) {
			System.out.println("Enter your birth date: (YYYY-MM-DD)");
			birthDate = userScanner.nextLine();
			isValid = this.isBirthDateValid(birthDate);
		}

		User newUser = new User(username, password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setBirthDate(birthDate);
		this.user = newUser;
		return newUser;
	}

	public void printAllInfo() {
		String fullAddress = "";
		if(!(user.getAddress() == null)) {
			fullAddress = user.getAddress().getFullAddress();
		}
		System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
		System.out.println("Address: " + fullAddress);
		System.out.println("Birth Date: " + user.getBirthDate());
		System.out.println("Annual Income: " + user.getAnnualIncome());
		System.out.println("Gender: " + user.getGender());
		System.out.println("Need Cell Phone: " + user.getNeedsCellPhone());
		System.out.println("Need Housing: " + user.getNeedsHousing());
		System.out.println("Need Job: " + user.getNeedsJob());
		System.out.println("Need Food: " + user.getNeedsFood() + "\n");

		System.out.println("You can add or update information in the options menu.");

	}

	public void updateInformationMenu() {
		System.out.println("Would you like to update your address?(Y OR N)");
		String response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateAddress();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}

		System.out.println("Would you like to update your annual income?(Y OR N)");
		response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateIncome();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}

		System.out.println("Would you like to update your gender?(Y OR N)");
		response = userScanner.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			this.updateGender();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}

		
		//Job Question
		System.out.println("Do you need a job? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsJob(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsJob(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		
		//Food Question
		System.out.println("Do you need food? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsFood(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsFood(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		
		//Housing Question
		System.out.println("Do you need housing? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsHousing(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsHousing(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		
		//Cellphone question
		System.out.println("Do you need a cell phone? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			user.setNeedsCellPhone(true);
		} else if (response.equalsIgnoreCase("N")) {
			user.setNeedsCellPhone(false);
		} else {
			System.out.println("Not a valid response. Moving on.");
		}
		//Medical Conditions Question
		System.out.println("Do you need to add existing medical conditions? (Y or N)");
		response = userScanner.nextLine();
		if(response.equalsIgnoreCase("Y")) {
			this.updateMedicalConditions();
		} else if (!response.equalsIgnoreCase("N")) {
			System.out.println("Not a valid response. Moving on.");
		}

		controller.updateUserInfo(user);
		System.out.println("Update Complete!");

	}

	public void updateAddress() {
		System.out.println("Please provide your street address: (Ex: 1111 StreetName St West)");
		String streetAddress = userScanner.nextLine();
		System.out.println("Please provide your city: ");
		String city = userScanner.nextLine();
		System.out.println("Please provide your state: ");
		String state = userScanner.nextLine();
		System.out.println("Please provide your zipcode: ");
		int zipcode = 0;
		boolean valid = false;
		while (!valid) {
			try {
				zipcode = Integer.parseInt(userScanner.nextLine());

				if (zipcode < 10000) {
					throw new Exception();
				}
				valid = true;
			} catch (Exception e) {
				valid = false;
				System.out.println("Invalid zipcode. Try again.");
			}
		}
		Address address = new Address(streetAddress, city, state, zipcode);
		user.setAddress(address);
	}

	public void updateIncome() {
		System.out.println("Please provide the estimated annual income for your household:");
		boolean valid = false;
		double income = 0;
		while (!valid) {
			try {
				income = Double.parseDouble(userScanner.nextLine());
				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid response.");
				valid = false;
			}
		}
		user.setAnnualIncome(income);
	}

	public void updateGender() {
		System.out.println("Please provide your gender-identity:");
		boolean valid = false;
		String gender = "";
		while (!valid) {
			try {
				gender = userScanner.nextLine();
				if (gender.equalsIgnoreCase("dinosaur")) {
					throw new Exception();
				}

				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid response.");
				valid = false;
			}
		}
		user.setGender(gender);
	}
	
	
	
	public void updateMedicalConditions() {
		System.out.println("Your current list of medical conditions is:");
		List<String> conditions = user.getMedicalConditions();
		if(conditions != null) {
		for (String condition : user.getMedicalConditions()) {
			System.out.println("- " + condition);
		}
		}
		System.out.println("Please list any additional medical conditions: (separated only by commas)");
		String response = userScanner.nextLine();
		String[] responseSplit = response.split(",");
		for (String condition : responseSplit) {
			user.addMedicalCondition(condition);
		}
		
		System.out.println("Your new list of medical conditions is:");
		for (String condition : user.getMedicalConditions()) {
			System.out.println("- " + condition);
		}

	}
	

	// -----------------------------------Validation--------------------
	public boolean isDateValid(String date) {
		ZoneId z = ZoneId.of("America/Montreal");
		LocalDate today = LocalDate.now(z);

		try {
			LocalDate newDate = LocalDate.parse(date);

			if (newDate.isBefore(today)) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Please enter a valid date. Example: YYYY-MM-DD");
			return false;
		}
		return true;
	}
	
	public boolean isBirthDateValid(String date) {
		ZoneId z = ZoneId.of("America/Montreal");
		LocalDate today = LocalDate.now(z);

		try {
			LocalDate newDate = LocalDate.parse(date);

			if (newDate.isAfter(today)) {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Please enter a valid date. Example: YYYY-MM-DD");
			return false;
		}
		return true;
	}

}
