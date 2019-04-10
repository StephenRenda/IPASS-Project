import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phone_Tracker {
	
	private static Scanner scanner;
	
	public static void main(String[] args) {
        BufferedReader reader;
        List<String> records = new ArrayList<String>();
        try {
        	reader = new BufferedReader(new FileReader("/Users/Public/Phone_DataBase.txt"));
        	// File has format like this
        	// Stephen 1 N street
        	// John    0 Address 
        	
        	String line = reader.readLine();
        	records.add(line);
        	while (line != null) {
        		line = reader.readLine();
        		records.add(line);
        	}
        	for (int i= 0; i < records.size()-1; i++)
        	{
        		System.out.println(records.get(i)); 
        	}
        	reader.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        phoneIsLost(); // call to phoneIsLost()  
    }
	
	/*
	 * This method checks to see if the phone is lost. If the phone is lost, then the user enters 
	 * the last location of the phone.
	 */
	private static void phoneIsLost() {

		scanner = new Scanner(System.in);
		System.out.println("Is your phone lost? (Yes/No)");

		if (scanner.nextLine().equals("Yes")) {
			System.out.print("Enter your name: ");
			String name = scanner.next();
			System.out.print("Enter last location: ");
			String address = scanner.next();
			updatePhoneIsLost(name, address);
			System.out.println("Thank You. Phone Status Update Success.");
		} 
	}
	
	/* @param name
	 * @param address
	 * Write the user name and last location of phone to Phone_DataBase.txt
	 */
	private static void updatePhoneIsLost(String name, String address) {
        try (FileWriter writer = new FileWriter("/Users/Public/Phone_DataBase.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
        	System. out. print(System.lineSeparator());
            bw.write(name + "\t\t" + address);
            bw.newLine();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
}
