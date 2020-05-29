package project.Kolton;

import java.util.Scanner;

public class Address {
	private int houseNumber;
	private String streetName;
	private String cityName;
	private String stateName;
	private int zipCode;
	private String fullAddress = this.houseNumber + " " + this.streetName + ", "  + this.cityName + ", " + this.stateName + " " + this.zipCode;
	private String buildingType;
	
	public void setBuildingType(String response){
		Scanner inputScanner = new Scanner(System.in);
		if (response.equalsIgnoreCase("no")) {
			this.buildingType = "Homeless";
		} else {
			System.out.println("Do you rent or own?");
			if(inputScanner.nextLine().equalsIgnoreCase("rent")) {
				this.buildingType = "Apartment";
			} else {
				this.buildingType = "Home";
			}
		}
	}
	
	public void getAddress() {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("What is your house number?");
		this.houseNumber = inputScanner.nextInt();
		inputScanner.nextLine();
		System.out.println("What is your street name? (including direction and street type)");
		this.streetName = inputScanner.nextLine();
		System.out.println("What is the city?");
		this.cityName = inputScanner.nextLine();
		System.out.println("What is the state?");
		this.stateName = inputScanner.nextLine();
		System.out.println("Finally, what's the zipcode?");
		this.zipCode = inputScanner.nextInt();
		inputScanner.nextLine();
	}
	
	
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getFullAddress() {
		return fullAddress;
	}

}
