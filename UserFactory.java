package park;


//Factory method here
public class UserFactory{



    public static client getInstance(String type,String email, String password){
        if(type.equalsIgnoreCase("Student")){
        	return new Student(type,email,password);
          
        } else if(type.equalsIgnoreCase("Faculty Member")){
        	return new FacultyMember(type,email,password);
       
        } else if(type.equalsIgnoreCase("Non-Faculty Staff")){
        	return new nonFaculty(type,email,password);
         
        } else if(type.equalsIgnoreCase("Visitor")){
        	return new Visitor(type,email,password);

        }
		return null;
    }
}

class Student implements client{
	
	
	private String email;
	private String password;
	private String type;
    public Student(String type, String email, String password) {
    	this.type=type;
    	this.email=email;
    	this.password=password;
       
    }

	@Override
	public String getemail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
}
class FacultyMember implements client{
	private String email;
	private String password;
	private String type;
    public FacultyMember(String type, String email, String password){
    	this.email=email;;
    	this.password=password;
    	this.type=type;
    
    }

	@Override
	public String getemail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
}

class nonFaculty implements client {
	private String email;
	private String password;
	private String type;
    public nonFaculty(String type, String email, String password){
    	this.email=email;;
    	this.password=password;
    	this.type=type;
     
    }
	@Override
	public String getemail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public String type() {
		// TODO Auto-generated method stub
		return this.type;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
} 

class Visitor implements client {
	private String email;
	private String password;
	private String type;
    public Visitor(String type, String email, String password){
    	this.email=email;;
    	this.password=password;
    	this.type=type;
       
    }

	@Override
	public String getemail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
    
}
