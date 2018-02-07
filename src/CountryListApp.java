/*
 * Nicholas Soule
 * Lab15 - CountryListApp
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountryListApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int userNum;
		String dirString = "countries";
		String fileString = "countrylist.txt";
		String userCountry;
		// creates directory and string
		createDirectory(dirString);
		createFile(dirString, fileString);

		// program starts
		System.out.println("Welcome to the Countries Maintenance Application!");
		System.out.println("1 - See the list of countries");
		System.out.println("2 - Add a country");
		System.out.println("3 - Exit");
		
		// do while loop gets user input and loops while they choose 1 or 2
		do {
			// validator ensures the user only enters a number 1-3
			userNum = Validator.getInt(scan, "Enter a menu number: ", 1, 3);
			// reads/prints out the country list
			if (userNum == 1) {
				readFromFile(dirString, fileString);

			}
			// adds a new country to the list
			if (userNum == 2) {
				System.out.println("Please enter a new country: ");
				userCountry = scan.next();
				writeToFile(dirString, fileString, userCountry);

			}
		} while (userNum == 1 || userNum == 2);
		// ends program
		System.out.println("Goodbye!");

		scan.close();
	}
	// creates directory
	public static void createDirectory(String dirString) {
		Path dirPath = Paths.get(dirString);
		System.out.println(dirPath.toAbsolutePath());

		if (Files.notExists(dirPath)) {
			try {
				Files.createDirectory(dirPath);
			} catch (IOException e) {
				System.out.println("Error 7");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("I'm not sure what happened, contact customer service.");
			}
		}
	}
	// creates file
	public static void createFile(String dirString, String fileString) {

		Path filePath = Paths.get(dirString, fileString);

		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);

			} catch (IOException e) {
				System.out.println("Error 77");
				e.printStackTrace();
			}
		}

	}
	// writes to file
	public static void writeToFile(String dirString, String fileString, String userCountry) {

		Path writeFile = Paths.get(dirString, fileString);

		File file = writeFile.toFile();

		try {

			PrintWriter printOut = new PrintWriter(new FileOutputStream(file, true));

			printOut.println(userCountry);
			printOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error 777");
			e.printStackTrace();
		}

	}
	// reads/prints from file
	public static void readFromFile(String dirString, String filePath) {
		Path readFile = Paths.get(dirString, filePath);

		File file = readFile.toFile();
		try {
			FileReader fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error 7777");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error 77777");
			e.printStackTrace();
		}
	}

}
