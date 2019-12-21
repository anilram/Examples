package com.ar.example.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {


	public static List<String> filesInDir(final String dir, final String suffix) {
		List<String> files = filesInDir(dir).stream().filter(x->x.endsWith(suffix)).collect(Collectors.toList());
		return files;
	}
	
	public static List<String> filesInDir(final String dir) {
		try(Stream<Path> dirWalk = Files.walk(Paths.get(dir))) {
			List<String> files = dirWalk.filter(Files::isRegularFile).map(x->x.toString()).collect(Collectors.toList());
			return files;
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
		
	}
	
	public static List<String> foldersInDir(final String dir) {
		try(Stream<Path> dirWalk = Files.walk(Paths.get(dir))) {
			List<String> files = dirWalk.filter(Files::isDirectory).map(x->x.toString()).collect(Collectors.toList());
			return files;
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
	
	public static List<File> listFiles(File dir) throws IOException {
		return Files.walk(Paths.get(dir.getAbsolutePath())).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
	}
	
	public static void writeToFile(String filePath, String content) {
		final Path path = Paths.get(filePath);
		Optional.of(Files.isWritable(path)).ifPresent(v -> {
			try {
				Files.write(path, content.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
	}
	
	public static List<String> readFile(String filePath) throws IOException{
		return Files.readAllLines(Paths.get(filePath));
	}
	
	public static List<String> readFile(String filePath,String contains) throws IOException{
		try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
		     List<String> filteredLines = lines
		                    .filter(s -> s.contains("admin"))
		                    .collect(Collectors.toList());
		     return filteredLines;
		}
	}
	
}
