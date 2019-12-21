package com.ar.example.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

	public static void main(String[] args) {
		List<String> abcde = new ArrayList<String>();
		Collections.addAll(abcde, "A,B".split(","));
		appendStr("A","P",null);
		
	}
	
	public static void appendStr(String ...str) {
		StringBuilder s = new StringBuilder("");
		for (String string : str) {
			s.append(string);
		}
		System.out.println(s.toString());
	}
	
	

}

