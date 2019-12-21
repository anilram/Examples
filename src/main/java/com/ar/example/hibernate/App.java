package com.ar.example.hibernate;

import java.util.Date;

import com.ar.example.hibernate.dao.CustomerDAO;
import com.ar.example.hibernate.model.Customer;

public class App {
	
	public static void main(String[] args){
//		CityDAO cityDAO = new CityDAO();
//		
//		long cityId1 = cityDAO.saveCity("New York");
//		long cityId2 = cityDAO.saveCity("Rio de Janeiro");
//		long cityId3 = cityDAO.saveCity("Tokyo");
//		long cityId4 = cityDAO.saveCity("London");
//	
//		cityDAO.listCities();
//		
//		cityDAO.updateCity(cityId4, "Paris");
//		
//		cityDAO.deleteCity(cityId3);
//		
//		cityDAO.listCities();
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = new Customer("A1", "Malli", "Kharjuna", new Date(), "Bhalanagar", true, 1, "Great People", "malli@gmail.com", 5, "pending");
		customerDAO.saveCustomer(customer);
		
		
	}
	

}
