package Projects.Kolton.Serverside_API.domain;

import javax.validation.constraints.NotBlank;

public class User {
	
	@NotBlank (message = "Username cannot be blank.")
	private String username;
	@NotBlank (message = "Password cannot be blank.")
	private String password;
	private String firstName;
	private String lastName;
	private String birthDate;
	private Address address;
	private String gender;
	private double annualIncome;
	private boolean needsJob;
	private boolean needsHousing;
	private boolean needsFood;
	private boolean needsCellPhone;
	private String[] medicalConditions;
	
	public User() {
		
	}
	

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		
	}

	
	public String getPassword() {
		return password;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		return 0;
	}

	

	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public boolean getNeedsJob() {
		return needsJob;
	}

	public void setNeedsJob(boolean needsJob) {
		this.needsJob = needsJob;
	}

	public boolean getNeedsHousing() {
		return needsHousing;
	}

	public void setNeedsHousing(boolean needsHousing) {
		this.needsHousing = needsHousing;
	}

	public boolean getNeedsFood() {
		return needsFood;
	}

	public void setNeedsFood(boolean needsFood) {
		this.needsFood = needsFood;
	}

	public boolean getNeedsCellPhone() {
		return needsCellPhone;
	}

	public void setNeedsCellPhone(boolean needsCellPhone) {
		this.needsCellPhone = needsCellPhone;
	}

	public String[] getMedicalConditions() {
		return medicalConditions;
	}

	public void setMedicalConditions(String[] medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	
	

}






