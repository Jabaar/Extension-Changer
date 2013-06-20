package me.jabaar.exspoofer.gui;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String dir;
		String ext;
		String contains;
		
		System.out.println("Hello, welcome to Ivan's extension file name changer.");
		System.out.println("Please tell us the directory where the files are");
		System.out.print("Dir: ");
		dir = scan.nextLine();
		System.out.println("Please write the extension you want");
		System.out.print("Ext: ");
		ext = scan.nextLine();
		System.out.println("What does the file name must contain (Write '' if nothing)?");
		System.out.print("Must contain: ");
		contains = scan.nextLine();
		
		changeExt(dir,ext,contains);
	}
	
	public static void changeExt(String dir, String newExt, String contains){
		System.out.println(dir);
		System.out.println(newExt);
		System.out.println(contains);
		
		long time = System.currentTimeMillis();
		
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles(); 
		 
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File file1 = listOfFiles[i];
				if(!file1.getName().equals("") && file1.getName().contains(contains)){
					String withoutExt = removeExtension(file1.getName());
					File file2 = new File(dir + "/" + withoutExt + "." + newExt);
					
					System.out.println("Renaming: " + file1.getAbsolutePath());
					boolean success = file1.renameTo(file2);
					if (success) {
						System.out.println(file1.getAbsolutePath() + " successfully renamed");
					}else{
						System.out.println(file1.getAbsolutePath() + " failed!");
					} 
				}
			}
		}
		
		time = System.currentTimeMillis() - time;
		System.out.println("Done!");
		System.out.println("The process took: " + time + " milliseconds");
	}
	
	
	public static String removeExtension(String s) {
	    String separator = System.getProperty("file.separator");
	    String filename;

	    int lastSeparatorIndex = s.lastIndexOf(separator);
	    if (lastSeparatorIndex == -1) {
	        filename = s;
	    } else {
	        filename = s.substring(lastSeparatorIndex + 1);
	    }

	    // Remove the extension.
	    int extensionIndex = filename.lastIndexOf(".");
	    if (extensionIndex == -1)
	        return filename;

	    return filename.substring(0, extensionIndex);
	}
}
