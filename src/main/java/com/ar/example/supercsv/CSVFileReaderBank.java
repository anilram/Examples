package com.ar.example.supercsv;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.prefs.CsvPreference.Builder;

public class CSVFileReaderBank {

	private static final String CSV_FILENAME = "src/main/resources/CsvFiles/20170710164930_CUST_EXTRACT_BANK.TXT";
	private static CellProcessor[] getProcessors() {
	        
	        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
	        StrRegEx.registerMessage(emailRegex, "must be a valid email address");
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	        		new NotNull(), 
	        		new NotNull(),
	        		new NotNull(),
	        		new NotNull(),
	        		new Optional(), 
	                new Optional(), 
	                new Optional(), 
	                new Optional(), 
	                new Optional(),
	                new Optional()
	        };
	        return processors;
	}
	
	public static void readWithCsvBeanReader() throws Exception {
	    ICsvBeanReader beanReader = null;
	    try {
	    		List<ACHBankAcctExtract> bankAcctExtracts = new ArrayList<ACHBankAcctExtract>();
	    		CsvPreference csvPreference = new Builder('"','\t',"\r\n").build();
	            beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME),csvPreference );
	            // the header elements are used to map the values to the bean (names must match)
	            beanReader.getHeader(true);
	            final String[] header = {"custNumber","ck","bankKey","bankAccount","bankCtrl","bankTypeP","bankRefSpec","bankAchlderName","collectionAuth","updateType"};
	            final CellProcessor[] processors = getProcessors();
	            
	            ACHBankAcctExtract bankAcctExtract;
	            while( (bankAcctExtract = beanReader.read(ACHBankAcctExtract.class, header, processors)) != null ) {
	            	System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
	                            beanReader.getRowNumber(), bankAcctExtract));
	            	bankAcctExtracts.add(bankAcctExtract);
	            }
	            
	            Collections.sort(bankAcctExtracts, new Comparator<ACHBankAcctExtract>() {

					public int compare(ACHBankAcctExtract o1, ACHBankAcctExtract o2) {
						// TODO Auto-generated method stub
						return (o1.getCustNumber()+o1.getBankAccount()).compareTo((o2.getCustNumber()+o2.getBankAccount()));
					}
				});
	            
	            for(ACHBankAcctExtract bankAcct : bankAcctExtracts){
	            	System.out.println(bankAcct.getCustNumber()+bankAcct.getBankAccount());
	            }
	    }

	    finally {
	            if( beanReader != null ) {
	                    beanReader.close();
	            }
	    }
	}
}
