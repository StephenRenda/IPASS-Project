package PhoneTracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Phone_Tracker {
	
	private static Scanner scanner;
	private static String Server = "/Users/Public/Phone_DataBase.txt";
	private static String Finder = "/Users/Public/locations.txt";
	public static void main(String arg[]) {
		
        ReadFile rf = new ReadFile();
        HashMap<String, String> map = new HashMap<String, String>();
        
        scanner = new Scanner(System.in);

        System.out.println("*************************");
        System.out.println("Welcome to Phone Tracker!");
        System.out.println("************************* \n");

        //Register a new User here.
        System.out.println("Are you a new user?");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
        	System.out.print("Enter First Name: ");
        	String firstName = scanner.next();
        	System.out.print("Enter Last Name: ");
        	String lastName = scanner.next();
        	Person person = new Person(firstName, lastName, "0", "blank");
        	registerNewUser(person.toString());
        	System.out.print("Registration Success!");
        } else /* lookup by name */ {
        
	        System.out.print("What is your name: \n");
	        String name = scanner.nextLine();
	   
	        try {//Putting location data from Finder into a hash map.
				BufferedReader reader = new BufferedReader(new FileReader(Finder));
	        	String line;
	        	int index;
				while ((line = reader.readLine()) != null)
	            {//Puts Finder Data into a hash map
	                String[] parts = line.split(",", 2);
	                if (parts.length >= 2)
	                {
	                    String key = parts[0];
	                    String value = parts[1];
	                    map.put(key, value);
	                } else {
	                    System.out.println("ignoring line: " + line);
	                }
	            } 
				reader.close();
				//Putting Server data into an array before person object
	        	String[] lines = rf.readLines(Server);   
	        	
	        	Person[] person = new Person[lines.length];
	        	for (int i = 0; i < lines.length-1; i++)
	        	{//Filling person object array with Server data.
	        		person[i] = new Person(lines[i]); 
	        	}
	        	//Set "phone is lost" by changing lost to 1.
	        	
				
	 
				for (index = 0; index < person.length-1;index++)
				{// Finds index, so we know what line to replace
					if (name.contentEquals(person[index].getFirstName()))
						break; 
				}
				
				//Save old line.
				String tempLine = person[index].toString();
				
				//Update person with location.
				person[index].setLocation(map.get(name));
	
				
		        ModifyFile mf = new ModifyFile();
		        //Copy person data to Server.
	            mf.modifyFile(Server, tempLine, person[index].toString());
	
	        	} catch (IOException e) {
	        	e.printStackTrace();
	        	}
	        phoneIsLost(map,name);
	         // call to phoneIsLost()  
	    }	
	}
	
	private static void phoneIsLost(HashMap<String, String> map, String name) {
        System.out.println("Is you phone lost?:");
        String c;
        Scanner S=new Scanner(System.in);
        c= S.next();
        if (c.contentEquals("yes"))
        	for(int i=0;i<3;i++)
        	{
        		try {
        			Thread.sleep(500);
        	        System.out.println("Locating phone......");
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
        	}

        System.out.println("Phone location:"+ map.get(name));
        S.close();
	}

	private static void registerNewUser(String s) {
        try (FileWriter writer = new FileWriter(Server, true);
        	BufferedWriter bw = new BufferedWriter(writer)) {
        	bw.write(s);
        	bw.newLine();
        } 
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
}
