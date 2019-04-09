import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Phone_Tracker {

	public static void main(String[] args) {
        BufferedReader reader;
        List<String> records = new ArrayList<String>();
        try {
        	reader = new BufferedReader(new FileReader("/Users/Public/Phone_DataBase.txt"));
        	// File has format like this
        	// Stephen 1 N street
			// John 0 Address 
        	
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
   }
}
