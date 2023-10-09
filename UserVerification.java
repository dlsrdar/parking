package park;

public class UserVerification extends User{
	public void verifyUser(User u) {
		if(u.getValidationStatus() == true) {
			System.out.println("User already verified");
		} else {
			u.validationStatus = true;
		}
	}
}
