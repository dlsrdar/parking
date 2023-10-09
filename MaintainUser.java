package park;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class MaintainUser {
	static ArrayList<client> users = new ArrayList<client>();
	

	public String path;

	
	
	// initial values 
	
	static {
		users.add(UserFactory.getInstance("Student", "kulp45@gmail.com","Ks@123456"));
		users.add(UserFactory.getInstance("Visitor","pawan54@gmail.com","Pk@123456"));
		users.add(UserFactory.getInstance("Faculty Member","kaman54@gmail.com","Kk@123456"));
		
	}
	

	// check if the person is there in the system 
	
	public static boolean check(String type,String email,String password){
		
		
		
		for(int i = 0;i<users.size();i++) {
			if(type.equals(users.get(i).type()) && email.equals(users.get(i).getemail()) && password.equals(users.get(i).getPassword()) ) {
				
				return true;
				
			}
			break;
		}
		
		
		return false;
		
	}
	
	
	
	public static void add(String type,String email,String password){
		
		
		
		// factory pattern	
		users.add(UserFactory.getInstance(type, email, password));

		}
	
	
//	public void update(String path) throws Exception{
//
//	}

}
