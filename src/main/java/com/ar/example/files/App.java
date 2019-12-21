package com.ar.example.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {

	public static void main(String[] args) {
		final String dir = "D:\\AnilRam\\projects\\java_examples\\DynamodbToCSV4j";
		final String textSuffix = ".txt";
		final List<String> filesInDir = FileUtils.filesInDir(dir);
		final List<String> foldersInDir = FileUtils.foldersInDir(dir);
		final List<String> textFiles = FileUtils.filesInDir(dir,textSuffix);
		try {
			@SuppressWarnings("unused")
			final List<File> files = FileUtils.listFiles(new File(dir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		printList(filesInDir);
		printSeperator();
		printList(foldersInDir);
		printSeperator();
		printList(textFiles);
	}

	private static void printList(List<String> list) {
		list.forEach(System.out::println);
	}
	
	private static void printSeperator() {
		System.out.println("----------------------***----------------------");
	}

}
