package com.ar.example.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ar.example.hibernate.HibernateUtil;
import com.ar.example.hibernate.model.Customer;

public class CustomerDAO {
	public Long saveCustomer(Customer customer)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long CustomerId = null;
		try {
			transaction = session.beginTransaction();
			CustomerId = (Long) session.save(customer);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	return CustomerId;
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> listCustomers()
	{
		List<Customer> customers = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			customers = session.createQuery("from Customer order by customerNo").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return customers;
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> listCustomers(String status)
	{
		List<Customer> customers = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			customers = session.createQuery("from Customer where status = '"+status+"'").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return customers;
	}
	
	public void saveOrUpdateAll(List<Customer> entities)  {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for(Customer entity : entities) {
			    session.saveOrUpdate(entity);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
}
