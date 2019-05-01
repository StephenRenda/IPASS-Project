package PhoneTracker;
import java.io.Serializable;

public class Person implements Serializable	{
	
		
		private static final long serialVersionUID = 1L;
		
		public String first_name;
		public String last_name;
		public String isLost;
		public String location;
		public Person(String line)
		{
			String[] split = line.split(",");
			first_name = split[0];
			last_name = split[1];
			isLost = split[2];
			location = split[3];
		}
	
		
		public Person(String fname, String lname, String lost, String location)
		{
			this.first_name = fname;
			this.last_name = lname;
			this.isLost = lost;
			this.location = location;
		}


		public void setFirstName(String fname) {
	        this.first_name = fname;
	    }
	 
	    public String getFirstName() {
	        return this.first_name;
	    }
	 
	    public void setLastName(String lname) {
	        this.first_name = lname;
	    }
	 
	    public String getLastName() {
	        return this.last_name;
	    }
	 
	    public void setLost(String lost) {
	        this.isLost = lost;
	    }
	 
	    public String getLost() {
	        return this.isLost;
	    }
	    public void setLocation(String location) {
	        this.location = location;
	    }
	 
	    public String getLocation() {
	        return this.location;
	    }
        @Override
        public String toString() {
            return this.first_name + ", " + this.last_name + ", " +
            	   this.isLost + ", " + this.location;
        }        
	}
