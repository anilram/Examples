package com.ar.example.fo;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class FOErrorListner  implements ErrorListener{

	public void warning(TransformerException exception) throws TransformerException {
		System.out.println(exception);
	}

	public void error(TransformerException exception) throws TransformerException {
		System.out.println(exception);
	}

	public void fatalError(TransformerException exception) throws TransformerException {
		System.out.println(exception);
	}

}
