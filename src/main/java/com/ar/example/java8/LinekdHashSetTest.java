package com.ar.example.java8;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinekdHashSetTest {
	public static void main(String[] args) {
		Set s = new LinkedHashSet();
		s.add("1");
		s.add(1);
		s.add(2);
		s.add(3);
		System.out.println(s);
	}
}
