package com.ar.example.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.ar.example.hibernate.model.City;
import com.ar.example.hibernate.model.Customer;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
								.configure()
								.addPackage("com.ar.example.hibernate.model") //the fully qualified package name
								.addAnnotatedClass(City.class)
								.addAnnotatedClass(Customer.class)
								.buildSessionFactory();
			
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
