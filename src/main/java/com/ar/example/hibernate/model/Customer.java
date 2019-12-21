package com.ar.example.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CUSTOMER")
public class Customer {
	
	public Customer(){
		super();
	}
	
	public Customer(String customerNo, String firstName, String lastName, Date birthDate, String mailingAddress,
			boolean married, int numberOfKids, String favouriteQuote, String email, long loyaltyPoints, String status) {
		super();
		this.customerNo = customerNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.mailingAddress = mailingAddress;
		this.married = married;
		this.numberOfKids = numberOfKids;
		this.favouriteQuote = favouriteQuote;
		this.email = email;
		this.loyaltyPoints = loyaltyPoints;
		this.status = status;
	}
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="customer_no")
	private String customerNo; 

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
    private String lastName;
    
	@Column(name="birth_date")
	private Date  birthDate;
    
	@Column(name="mailing_address")
	private String mailingAddress;
    
	@Column(name="married")
	private boolean married;
    
	@Column(name="number_of_Kids")
	private int numberOfKids;
    
	@Column(name="favourite_quote")
	private String favouriteQuote;
    
	@Column(name="email")
	private String email;
    
	@Column(name="loyalty_points")
	private long loyaltyPoints;
	
	@Column(name="status")
	private String status;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
