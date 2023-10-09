package park;

import java.util.*;

public class Manager {

	/*
	Req2: Managers of the system can add, enable, or disable a parking lot, a parking lot contains 100 parking
	spaces. Managers can also enable or disable a parking space due to maintenance issues.
	**/

	//Add into management team class
	String username;
	String password;
	UserVerification verifier = new UserVerification();
	
	public Manager() {
		this.username = "manager_" + UUID.randomUUID().toString().substring(0, 8);
        this.password = generatePassword();
		System.out.println("Manager Account created. Username: " + this.username + " Password: " + this.password); 
	}

	public Manager(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getName(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public void addUser(User user) {
		verifier.verifyUser(user);	
	}

	public void removeUser(User user) {
		user.validationStatus = false;
	}
	
	public String generatePassword() {
        // Generate a strong password using a combination of uppercase letters, lowercase letters, numbers, and symbols
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()_-+=<>?,./;:[]{}\\|\"";
        String all = upper + lower + digits + symbols;
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(all.length());
            password.append(all.charAt(index));
        }
        return password.toString();
    }
	
	public boolean validateUser(String email) {
		if((email.contains("@yorku.ca")) || (email.contains("@my.yorku.ca"))) {
			return true;
		}
		
		else {
			return false;
		}
	}
}

class SuperManager extends Manager{
	public static SuperManager instance;
    public String username;
    public String password;


	public SuperManager() {
        // Generate a random username and password for the super manager
        this.username = "supermanager_" + UUID.randomUUID().toString().substring(0, 8);
        this.password = generatePassword();
        System.out.println("Super Manager Account created. Username: " + username + " Password: " + password);
    }

    public SuperManager(String username, String password) {
		super(username, password);
	}

	public static SuperManager getInstance() {
        if (instance == null) {
            instance = new SuperManager();
        }
        return instance;
    }

	public void setPassword(String password){
		this.password = password; 	
	}

}

class ManagementAccountFactory{
	 public static Manager createManagementAccount(String username, String password) {
        return new Manager(username, password);
    }
}

/*
Req6: Managers of the system can add, enable, or disable a parking lot, a parking lot contains 100 parking
spaces. Managers can also enable or disable a parking space due to maintenance issues.
 * **/
class ManagementTeam extends Manager{
	public static ManagementTeam instance;
	static SuperManager superManager;
	ArrayList<Manager> managers; 
	ManagementAccountFactory managerFactory;
	ParkingSystem parkingSystem;

	public ManagementTeam(SuperManager superManager){
		this.superManager = superManager;
		this.managers = new ArrayList<Manager>();
		this.managerFactory = new ManagementAccountFactory();
		this.parkingSystem = new ParkingSystem();
	}

	/*
	 * Req2: The system has an auto account generation subsystem, which can generate management accounts
	 * (unique names and strong passwords), with which the management teams can login to the system and maintain
	 * the parking services. Note that, the auto account generation is only available for the super manager (ONLY
	 * ONE) of the Parking team.
	 * **/
	public void AutoAccountGeneration(){
		if(this.superManager != null){
			String username = "manager_" + UUID.randomUUID().toString().substring(0, 8);
        	String password = generatePassword();
			managers.add(this.managerFactory.createManagementAccount(username, password));
		} else {
			System.out.println("AutoAccountGeneration: FAILED cannot create manager.");
		}
	}
	public void addManager(Manager m) {
		if(managers.contains(m) == false) {
			managers.add(m);
		} else {
			System.out.println("addManager: FAILED, manager is already on the the ManagementTeam.");
		}
	}

	public static Object getInstance() {
		if (instance == null) {
            instance = new ManagementTeam(superManager);
        }
        return instance;
	}

	public void setSuperManager(SuperManager superManager) {
		this.superManager = superManager;
	}
	
	/*
	 * Req6: Managers of the system can add, enable, or disable a parking lot, a parking lot contains 100 parking
	 * spaces. Managers can also enable or disable a parking space due to maintenance issues.
	 * **/
	public void addParking(){
		if(parkingSystem.parkingSpaces.size() < 100) {
			int temp = parkingSystem.parkingSpaces.size();
			temp++;
			ParkingSpace x = new ParkingSpace(temp); 
			parkingSystem.addParkingSpace(x);
		} else {
			System.out.println("addParking: FAILED, cannot add parking space as parking space limit is reached");
		}
		
	}
	
	public void removeParking(ParkingSpace ps){
		if(parkingSystem.parkingSpaces.size() <= 0) {
			System.out.println("removeParking: FAILED, cannot remove parking space as parking space is already empty");
		} else {
			if(parkingSystem.parkingSpaces.contains(ps)) {
				parkingSystem.parkingSpaces.remove(ps);
			} else {
				System.out.println("removeParking: FAILED, cannot remove parking space as parking space does not exist in parkingSystem");
			}
		}
	}
}