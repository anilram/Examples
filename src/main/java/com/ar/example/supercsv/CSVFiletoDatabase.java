package com.ar.example.supercsv;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.ar.example.hibernate.dao.CustomerDAO;
import com.ar.example.hibernate.model.Customer;

public class CSVFiletoDatabase {

	private static final String CSV_FILENAME = "src/main/resources/CsvFiles/customers.csv";
	private static CellProcessor[] getProcessors() {
	        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
	        StrRegEx.registerMessage(emailRegex, "must be a valid email address");
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	                new UniqueHashCode(), // customerNo (must be unique)
	                new NotNull(), // firstName
	                new NotNull(), // lastName
	                new ParseDate("dd/MM/yyyy"), // birthDate
	                new NotNull(), // mailingAddress
	                new Optional(new ParseBool()), // married
	                new Optional(new ParseInt()), // numberOfKids
	                new NotNull(), // favouriteQuote
	                new StrRegEx(emailRegex), // email
	                new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
	        };
	        return processors;
	}
	
	public static void readWithCsvBeanReader() throws Exception {
		CustomerDAO customerDAO = new CustomerDAO();
		List<CustomerBean> customerBeans = new ArrayList<CustomerBean>();
	    ICsvBeanReader beanReader = null;
	    try {
	            beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME), CsvPreference.STANDARD_PREFERENCE);
	            
	            // the header elements are used to map the values to the bean (names must match)
	            final String[] header = beanReader.getHeader(true);
	            final CellProcessor[] processors = getProcessors();
	            
	            CustomerBean customerBean;
	            while( (customerBean = beanReader.read(CustomerBean.class, header, processors)) != null ) {
	            	customerBeans.add(customerBean);
	            }
	            
	    		List<Customer> pendingCustomers = customerDAO.listCustomers("pending");
	    		List<Customer> pendingToActiveCustomers = new ArrayList<Customer>();

	    		for(Customer pendingCustomer : pendingCustomers){
	    	    	for(CustomerBean _customerBean : customerBeans){
	    	    		if(pendingCustomer.getCustomerNo().equals(_customerBean.getCustomerNo())){
	    	    			pendingCustomer.setStatus("active");
	    	    			pendingToActiveCustomers.add(pendingCustomer);
	    	    		}
	    	    	}
	    	    }
	    		if(pendingToActiveCustomers.size() > 0 ){
		    		customerDAO.saveOrUpdateAll(pendingToActiveCustomers);
	    		}
	    		List<Customer> activeCustomers = customerDAO.listCustomers("active");
	    		List<Customer> activeToDeleteCustomers = new ArrayList<Customer>();
	    		boolean isDelete = false;
	            for(Customer activeCustomer : activeCustomers){
		    		isDelete = true;
	    	    	for(CustomerBean _customerBean : customerBeans){
	    	    		if(activeCustomer.getCustomerNo().equals(_customerBean.getCustomerNo())){
	    	    			isDelete = false;
	    	    			break;
	    	    		}
	    	    	}
	    	    	if(isDelete){
	    	    		activeCustomer.setStatus("delete");
	    	    		activeToDeleteCustomers.add(activeCustomer);
	    	    	}
	    	    }
	    		customerDAO.saveOrUpdateAll(activeToDeleteCustomers);
	    		
	    		List<Customer> dbCustomers = customerDAO.listCustomers();
	    		List<Customer> updateCustomers = new ArrayList<Customer>();
	    		int size = dbCustomers.size() < customerBeans.size() ? dbCustomers.size() : customerBeans.size();
	    		for(int i = 0,j=0; i < size;){
	    			if(dbCustomers.get(i).getCustomerNo().compareTo(customerBeans.get(j).getCustomerNo()) == 0){
	    				if(i > j){//there in DB not in file --> delete status
	    					for(int k = i; k > j; k--){
	    						updateCustomers.add(dbCustomers.get(k));
	    						updateCustomers.get(k).setStatus("delete");
	    					}
	    				} else if(j > i){
	    					for(int k = j; k > i; k--){
	    						updateCustomers.add(new Customer(customerBeans.get(k).getCustomerNo(),
	    								customerBeans.get(k).getFirstName(),
	    								customerBeans.get(k).getLastName(),
	    								customerBeans.get(k).getBirthDate(),
	    								customerBeans.get(k).getMailingAddress(),
	    								customerBeans.get(k).getMarried(),
	    								customerBeans.get(k).getNumberOfKids(),
	    								customerBeans.get(k).getFavouriteQuote(),
	    								customerBeans.get(k).getEmail(),
	    								customerBeans.get(k).getLoyaltyPoints(),
	    								"active"));
	    					}
	    				}
	    				i++;
	    				j++;
	    				updateCustomers.add(dbCustomers.get(i));
	    				updateCustomers.get(i).setStatus("active");	 
	    			} else if(dbCustomers.get(i).getCustomerNo().compareTo(customerBeans.get(j).getCustomerNo()) < 0){
	    				i++;
	    			} else {
	    				j++;
	    			}
	    		}
	    }   finally {
	            if( beanReader != null ) {
	                    beanReader.close();
	            }
	    }
	}
	
	public static void readWithCsvBeanReaderPerf() throws Exception {
		CustomerDAO customerDAO = new CustomerDAO();
		List<CustomerBean> customerBeans = new ArrayList<CustomerBean>();
	    ICsvBeanReader beanReader = null;
	    try {
	            beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME), CsvPreference.STANDARD_PREFERENCE);
	            
	            // the header elements are used to map the values to the bean (names must match)
	            final String[] header = beanReader.getHeader(true);
	            final CellProcessor[] processors = getProcessors();
	            
	            CustomerBean customerBean;
	            while( (customerBean = beanReader.read(CustomerBean.class, header, processors)) != null ) {
	            	customerBeans.add(customerBean);
	            }
	            
	    	
	    		
	    		List<Customer> dbCustomers = customerDAO.listCustomers();
	    		List<Customer> updateCustomers = new ArrayList<Customer>();
	    		int size = dbCustomers.size() > customerBeans.size() ? dbCustomers.size() : customerBeans.size();
	    		int updateCustomersSize = 0;
	    		int dbCustomersSize = dbCustomers.size();
	    		int customerBeansSize = customerBeans.size();
	    		int i=0,j=0;
	    		for(i = 0,j=0; (i < dbCustomers.size() && j < customerBeans.size());){
	    			if(dbCustomers.get(i).getCustomerNo().compareTo(customerBeans.get(j).getCustomerNo()) == 0){
/*	    				if(i > j){//there in DB not in file --> delete status
	    					for(int k = i; k > j; k--){
	    						updateCustomers.add(dbCustomers.get(k));
	    						updateCustomersSize  = updateCustomers.size();
	    						updateCustomers.get(updateCustomersSize-1).setStatus("delete");
	    					}
	    				} else if(j > i){
	    					for(int k = j; k > i; k--){
	    						updateCustomers.add(new Customer(customerBeans.get(k).getCustomerNo(),
	    								customerBeans.get(k).getFirstName(),
	    								customerBeans.get(k).getLastName(),
	    								customerBeans.get(k).getBirthDate(),
	    								customerBeans.get(k).getMailingAddress(),
	    								customerBeans.get(k).getMarried(),
	    								customerBeans.get(k).getNumberOfKids(),
	    								customerBeans.get(k).getFavouriteQuote(),
	    								customerBeans.get(k).getEmail(),
	    								customerBeans.get(k).getLoyaltyPoints(),
	    								"active"));
	    					}
	    				}*/
	    				if(!dbCustomers.get(i).getStatus().equals("active")){
		    				updateCustomers.add(dbCustomers.get(i));
		    				updateCustomersSize  = updateCustomers.size();
		    				updateCustomers.get(updateCustomersSize-1).setStatus("active");	
	    				}
	    				i++;
	    				j++;
	    				//dbCustomers.get(i).setStatus("active");
	    				 
	    			} else if(dbCustomers.get(i).getCustomerNo().compareTo(customerBeans.get(j).getCustomerNo()) < 0){
	    				if(dbCustomers.get(i).getStatus().equals("active")){
	    					updateCustomers.add(dbCustomers.get(i));
							updateCustomersSize  = updateCustomers.size();
							updateCustomers.get(updateCustomersSize-1).setStatus("delete");
	    				}
	    				i++;
	    			} else {
	    				updateCustomers.add(new Customer(customerBeans.get(j).getCustomerNo(),
								customerBeans.get(j).getFirstName(),
								customerBeans.get(j).getLastName(),
								customerBeans.get(j).getBirthDate(),
								customerBeans.get(j).getMailingAddress(),
								customerBeans.get(j).getMarried(),
								customerBeans.get(j).getNumberOfKids(),
								customerBeans.get(j).getFavouriteQuote(),
								customerBeans.get(j).getEmail(),
								customerBeans.get(j).getLoyaltyPoints(),
								"active"));
	    				j++;
	    			}
	    		}
	    		if(i == dbCustomers.size()){
					for(int k = j; k < customerBeans.size(); k++){
						updateCustomers.add(new Customer(customerBeans.get(k).getCustomerNo(),
							customerBeans.get(k).getFirstName(),
							customerBeans.get(k).getLastName(),
							customerBeans.get(k).getBirthDate(),
							customerBeans.get(k).getMailingAddress(),
							customerBeans.get(k).getMarried(),
							customerBeans.get(k).getNumberOfKids(),
							customerBeans.get(k).getFavouriteQuote(),
							customerBeans.get(k).getEmail(),
							customerBeans.get(k).getLoyaltyPoints(),
							"active"));
					}
	    		} else if(j == customerBeans.size()){
	    			for(int k = i; k < dbCustomers.size(); k++){
	    				updateCustomers.add(dbCustomers.get(k));
						updateCustomersSize  = updateCustomers.size();
						updateCustomers.get(updateCustomersSize-1).setStatus("delete");					}
	    		}
	    		customerDAO.saveOrUpdateAll(updateCustomers);
	    }
	    finally {
	            if( beanReader != null ) {
	                    beanReader.close();
	            }
	    }
	}
}
