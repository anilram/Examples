package com.ar.example.supercsv;

import java.util.Date;

public class CustomerBean {
	private String customerNo; //(must be unique)
    private String firstName;
    private String lastName;
    private Date  birthDate;
    private String mailingAddress;
    private boolean married;
    private int numberOfKids;
    private String favouriteQuote;
    private String email;
    private long loyaltyPoints;
    
    
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public boolean getMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public int getNumberOfKids() {
		return numberOfKids;
	}
	public void setNumberOfKids(int numberOfKids) {
		this.numberOfKids = numberOfKids;
	}
	public String getFavouriteQuote() {
		return favouriteQuote;
	}
	public void setFavouriteQuote(String favouriteQuote) {
		this.favouriteQuote = favouriteQuote;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getLoyaltyPoints() {
		return loyaltyPoints;
	}
	public void setLoyaltyPoints(long loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return customerNo+ email+favouriteQuote+firstName+lastName+mailingAddress;
    }
}
