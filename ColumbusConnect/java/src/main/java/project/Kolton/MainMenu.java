package project.Kolton;

import java.util.Scanner;


public class MainMenu {

	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		User firstUser = new User();
//Beginning Stuff
		System.out.println("Hi there! I'd love to help you access available resources in Columbus.");
		System.out.println("First of all, can I get your first name?");
		firstUser.setFirstName(inputScanner.nextLine());
		System.out.println("Thanks, " + firstUser.getFirstName() + "! And your last name?");
		firstUser.setLastName(inputScanner.nextLine());
//Ready to Start?
		System.out.println("Awesome. I can help you best when I know more information about you. Are you ready to answer some additional questions about your personal situation?");
		boolean readyAnswer;
		String readyAnswerActual = inputScanner.nextLine();
		if (readyAnswerActual.equalsIgnoreCase("Yes") || readyAnswerActual.equalsIgnoreCase("Yeah") || readyAnswerActual.equalsIgnoreCase("Sure")) {
			readyAnswer = true;
		} else {
			readyAnswer = false;
		}
		if(readyAnswer == false) {
			System.out.println("That's alright, we can come back to it.");
			System.out.println("In the mean time, here are some resources anyone can take advantage of: ");
			return;
		}
//Address Question
		System.out.println("Perfect. First, do you have stable housing?");
		Address firstAddress = new Address();
		firstAddress.setBuildingType(inputScanner.nextLine());
		firstAddress.getAddress();
		System.out.println("Thanks.");
		firstUser.setAddress(firstAddress.getFullAddress());
//Age Question
		System.out.println("Next, please enter your age.");
		firstUser.whatIsYourAge(inputScanner.nextInt());
		inputScanner.nextLine();
//Income question
		System.out.println("What is your estimated annual income?");
		firstUser.whatIsYourIncome(inputScanner.nextDouble());
		inputScanner.nextLine();
//Dependents Question
		System.out.println("How many dependents do you have? (Enter 0 for none)");
		firstUser.setNumberOfDependents(inputScanner.nextInt());
		inputScanner.nextLine();
//Cell Phone Question
		System.out.println("Do you have a cell phone?");
		String cellPhoneAnswerNormalized = firstUser.doYouHaveCellPhone(inputScanner.nextLine());
// Transportation Question
		System.out.println("Do you already have a source of transportation?");
		String transportAnswerNormalized = firstUser.doYouHaveTransport(inputScanner.nextLine());
		
//Job Question
		System.out.println("Do you need a job?");
		String needsJobAnswerNormalized = firstUser.doYouNeedAJob(inputScanner.nextLine());
		
//Review content
		
		System.out.println("Awesome, that's all I need for now. Is all this information correct?");
		System.out.println("____________________________________________________________________");
		System.out.println("Full Name: " + firstUser.getFirstName() + " " + firstUser.getLastName());
		System.out.println("Age: " + firstUser.getAge());
		System.out.println("Annual Income: " + firstUser.getAnnualIncome());
		System.out.println("Dependents: " + firstUser.getNumberOfDependents());
		System.out.println("Have a Cell Phone: " + cellPhoneAnswerNormalized);
		System.out.println("Have Transportation: " + transportAnswerNormalized);
		System.out.println("Need a job: " + needsJobAnswerNormalized);
		System.out.println("Address: " + firstAddress.getHouseNumber() + " " + firstAddress.getStreetName() + " "  + firstAddress.getCityName() + ", " + firstAddress.getStateName() + " " + firstAddress.getZipCode());
		
//Resubmit Logic
		String reviewAnswer = inputScanner.nextLine();
		if (reviewAnswer.equalsIgnoreCase("Yes") || reviewAnswer.equalsIgnoreCase("Yeah") || reviewAnswer.equalsIgnoreCase("Sure")) {
			System.out.println("Perfect! Let's get started!");
			} else {
				System.out.println("Shoot. Which field was wrong?");
				String resubmitResponse = inputScanner.nextLine();
				if(resubmitResponse.contains("income") || resubmitResponse.contains("money")) {
					System.out.println("What is your estimated annual income?");
					firstUser.whatIsYourIncome(inputScanner.nextDouble());
					inputScanner.nextLine();
				} else if(resubmitResponse.contains("job")) {
					System.out.println("Do you need a job?");
					needsJobAnswerNormalized = firstUser.doYouNeedAJob(inputScanner.nextLine());
				} else if (resubmitResponse.contains("transport") || resubmitResponse.contains("car")) {
					System.out.println("Do you already have a source of transportation?");
					transportAnswerNormalized = firstUser.doYouHaveTransport(inputScanner.nextLine());
				} else if(resubmitResponse.contains("cell") || resubmitResponse.contains("phone")) {
					System.out.println("Do you have a cell phone?");
					cellPhoneAnswerNormalized = firstUser.doYouHaveCellPhone(inputScanner.nextLine());
				} else if(resubmitResponse.contains("name")) {
					System.out.println("Okay, can you repeat your first name?");
					firstUser.setFirstName(inputScanner.nextLine());
					System.out.println("Thanks, " + firstUser.getFirstName() + "! And your last name?");
					firstUser.setLastName(inputScanner.nextLine());
				} 
				System.out.println("Awesome, that's all I need for now. Is all this information correct?");
				System.out.println("____________________________________________________________________");
				System.out.println("Full Name: " + firstUser.getFirstName() + " " + firstUser.getLastName());
				System.out.println("Age: " + firstUser.getAge());
				System.out.println("Annual Income: " + firstUser.getAnnualIncome());
				System.out.println("Dependents: " + firstUser.getNumberOfDependents());
				System.out.println("Have a Cell Phone: " + cellPhoneAnswerNormalized);
				System.out.println("Have Transportation: " + transportAnswerNormalized);
				System.out.println("Need a job: " + needsJobAnswerNormalized);
				System.out.println("Address: " + firstAddress.getHouseNumber() + " " + firstAddress.getStreetName() + " "  + firstAddress.getCityName() + ", " + firstAddress.getStateName() + " " + firstAddress.getZipCode());


	}
//End Review
		
		
		
		
		

		
		
	}

}
