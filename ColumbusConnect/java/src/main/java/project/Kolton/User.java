package project.Kolton;

import java.util.Scanner;

public class User {
	
	private String firstName;
	private String lastName;
	private int age;
	private double annualIncome;
	private int numberOfDependents;
	private boolean hasTransportation;
	private boolean hasCellphone;
	private boolean needsJob;
	private String userAddress;
	
	
	
	public void whatIsYourAge(int age) {
		this.setAge(age);
		if(this.getAge() < 18) {
			System.out.println("You're pretty young! Maybe you should work on this with your parent of gaurdian.");
		} else {
			System.out.println("Wow, you've made it pretty far into life! Congrats on turning " + this.getAge());
		}
	}
	
	public void whatIsYourIncome(double income) {
		this.setAnnualIncome(income);
		System.out.println("Thanks.");
	}
	
	public boolean getYesOrNo(String response) {
		if(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("yeah") || response.equalsIgnoreCase("sure") || response.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	public String doYouHaveCellPhone(String cellResponse) {
		String cellPhoneAnswerActual = cellResponse;
		String cellPhoneAnswerNormalized;
		if (this.getYesOrNo(cellResponse)) {
		this.setHasCellphone(true);
		cellPhoneAnswerNormalized = "Yes";
		System.out.println("Perfect. That wil be handy.");
		} else {
			this.setHasCellphone(false);
			cellPhoneAnswerNormalized = "No";
			System.out.println("No worries. We can probably get you one.");
		}
		return cellPhoneAnswerNormalized;
	}
	
	public String doYouHaveTransport(String transportResponse) {
		String transportAnswerNormalized;
		if (this.getYesOrNo(transportResponse)) {
			this.setHasTransportation(true);
			transportAnswerNormalized = "Yes";
			System.out.println("That's great.");
			} else {
				this.setHasTransportation(false);
				transportAnswerNormalized = "No";
				System.out.println("Okay, there's a place I know that might help with bus passes.");
			}
		return transportAnswerNormalized;
	}
	
	public String doYouNeedAJob(String jobResponse) {
		if (this.getYesOrNo(jobResponse)) {
			this.setNeedsJob(true);
			return "Yes";
			} else {
				this.setNeedsJob(false);
				return "No";
			}
	}
	
	
		 
	
	
	
	
	public void setAddress(String Address) {
		this.userAddress = Address;
	}
	
	
	
	public String getUserAddress() {
		return userAddress;
	}

	public void setNeedsJob(boolean needsJob) {
		this.needsJob = needsJob;
	}
	
	public boolean getNeedsJob() {
		return needsJob;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public int getNumberOfDependents() {
		return numberOfDependents;
	}
	public void setNumberOfDependents(int numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}
	public boolean isHasTransportation() {
		return hasTransportation;
	}
	public void setHasTransportation(boolean hasTransportation) {
		this.hasTransportation = hasTransportation;
	}
	public boolean isHasCellphone() {
		return hasCellphone;
	}
	public void setHasCellphone(boolean hasCellphone) {
		this.hasCellphone = hasCellphone;
	}
	

}
