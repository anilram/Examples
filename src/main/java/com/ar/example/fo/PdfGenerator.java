package com.ar.example.fo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.xml.sax.SAXException;



public class PdfGenerator {

	public static String generateDocument(final String xml, final String xslt, final String mimeType, 
			final String filePath, final String fileName, final String extension) throws SAXException, IOException, TransformerException  {
		OutputStream out = null;
		InputStream inputStream = null;
		File pdffile = null;
		FopFactory fopFactory = FopFactory.newInstance(new File(filePath).toURI());

		try {
			File pdfFolder = new File(filePath);
			pdfFolder.mkdirs();
			inputStream = new ByteArrayInputStream(xml.getBytes());
			File xsltfile = new File(xslt);

			pdffile = new File(filePath, fileName);
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
			out = new java.io.FileOutputStream(pdffile);
			out = new java.io.BufferedOutputStream(out);

			//fopFactory.setBaseURL(new File(filePath).toURI().toString());
			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(mimeType, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Source src1 = new StreamSource(xsltfile);
			Transformer transformer = factory.newTransformer(src1);
			// Set the value of a <param> in the stylesheet
			transformer.setParameter("versionParam", "2.0");
			transformer.setErrorListener(new FOErrorListner());
			// Setup input for XSLT transformation
			Source src = new StreamSource(inputStream);
			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());
			// Start XSLT transformation and FOP processing
			transformer.transform(src, res);
		}
		 finally {
			try {
				inputStream.close();
				out.close();
			} catch (Exception ex) {
			}
		}
		return (null!=pdffile.getAbsolutePath()) ? pdffile.getAbsolutePath() : null;
	}
	
	
//	   public static void testApacheFO(){
//			// the XSL FO file
////			File xsltFile = new File(RESOURCES_DIR + "//confirmation_letter_French.xsl");
////			// the XML file which provides the input
////			StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//apacheCanada.xml"));
////			// create an instance of fop factory
////			FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
////			// a user agent is needed for transformation
////			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
//			
//			try {
//				PdfGenerator.generateDocument(readFile(RESOURCES_DIR + "//apacheCanada.xml",Charset.defaultCharset()), RESOURCES_DIR + "//confirmation_letter_French.xsl", MimeConstants.MIME_PDF, OUTPUT_DIR, "confirmation_letter_French.pdf", ".pdf");
//			} catch (SAXException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (TransformerException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// Setup output
////			File pdfFolder = new File(OUTPUT_DIR);
////			pdfFolder.mkdirs();
////			OutputStream out = null;
////			try {
////				out = new java.io.FileOutputStream(OUTPUT_DIR + "//confirmation_letter_English.pdf");
////			} catch (FileNotFoundException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//	//
////			try {
////				// Construct fop with desired output format
////				Fop fop = null;
////				try {
////					fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
////				} catch (FOPException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//	//
////				// Setup XSLT
////				TransformerFactory factory = TransformerFactory.newInstance();
////				Transformer transformer = null;
////				try {
////					transformer = factory.newTransformer(new StreamSource(xsltFile));
////				} catch (TransformerConfigurationException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//	//
////				// Resulting SAX events (the generated FO) must be piped through to
////				// FOP
////				Result res = null;
////				try {
////					res = new SAXResult(fop.getDefaultHandler());
////				} catch (FOPException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//	//
////				// Start XSLT transformation and FOP processing
////				// That's where the XML is first transformed to XSL-FO and then
////				// PDF is created
////				transformer.transform(xmlSource, res);
////			} catch (TransformerException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			} finally {
////				try {
////					out.close();
////				} catch (IOException e) {
////					// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////				}
//	}
}
