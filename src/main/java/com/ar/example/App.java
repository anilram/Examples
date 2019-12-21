package com.ar.example;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import com.ar.example.fo.PdfGenerator;
import com.ar.example.joda.DatesUtility;
import com.ar.example.nio.NIOUtility;
import com.ar.example.supercsv.CSVFileReaderBank;
import com.ar.example.supercsv.CSVFiletoDatabase;


/**
 * Hello world!
 *
 */
public class App 
{
    private static Scanner scanner;
    
	public static final String RESOURCES_DIR;
	public static final String OUTPUT_DIR;

	private static String methodPrompt;
	
	static {
		RESOURCES_DIR = "src//main//resources//";
		OUTPUT_DIR = "src//main//resources//output//";
	}

	public static void main( final String[] args) {

		scanner = new Scanner(System.in);
		do {
			final String className = "com.ar.example.App";
			Class<?> appClass = null;
			try {
				appClass = Class.forName(className);
			} catch (final ClassNotFoundException e1) {
				
				e1.printStackTrace();
			}
			try {
				final Object app = appClass.getDeclaredConstructor().newInstance();
				methodPrompt = "Enter Method Name";
				System.out.println(methodPrompt);
				final String methodName = scanner.nextLine();
				Method method = null;
				try {
					method = app.getClass().getMethod(methodName);
				} catch (final NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (final SecurityException e) {
					
					e.printStackTrace();
				}
				try {
					method.invoke(app);
				} catch (final IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (final InvocationTargetException e) {
					
					e.printStackTrace();
				} // explicit cast

			} catch (final InstantiationException e) {
				
				e.printStackTrace();
			} catch (final IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (final Exception e) {
				
				e.printStackTrace();
			}
			System.out.println("press \"continue\" to proceed");
		} while (scanner.nextLine().equalsIgnoreCase("continue"));

	}

	public static void testJoda() {
		System.out.println("Enter Date in Format in MM/dd/yyyy ");
		final String endDate = scanner.nextLine();
		System.out.println(DatesUtility.daysBtnDates(DatesUtility.tomorrowsDate(), endDate, "MM/dd/yyyy"));
	}

	public static void testSuperCsv() throws Exception {
		CSVFileReaderBank.readWithCsvBeanReader();
	}

	public static void testSuperCsvDB() throws Exception {
		CSVFiletoDatabase.readWithCsvBeanReader();
	}

	public static void testSuperCsvDBPerf() throws Exception {
		CSVFiletoDatabase.readWithCsvBeanReaderPerf();
	}

	public static void testNIO() {
		try {
			System.out.println(NIOUtility.readFile(RESOURCES_DIR + "//apacheCanada.xml", Charset.defaultCharset()));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void testApacheFO() {
		try {
			PdfGenerator.generateDocument(
					NIOUtility.readFile(RESOURCES_DIR + "//apacheCanada.xml", Charset.defaultCharset()),
					RESOURCES_DIR + "//confirmation_letter_ca_English.xsl", MimeConstants.MIME_PDF, OUTPUT_DIR,
					"confirmation_letter_ca_English.pdf", ".pdf");
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void testUtilDates() {
		try {
			final Date dateInstance = new Date(scanner.nextLine());
			final Date when = new Date(scanner.nextLine());
			System.out.println("after " + dateInstance.after(when));
			System.out.println("before " + dateInstance.after(when));
			System.out.println("compareTo " + dateInstance.compareTo(when));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void testVariableLengthArray() {
		variableLengthArray(1, "", 1);
	}

	public static void variableLengthArray(final int i, final Object... objects) {
    	System.out.println("Empty Array");
    }
    
    
}
