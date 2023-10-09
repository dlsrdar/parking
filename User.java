package park;

public class User {
	public String name;
	public int id;
	public String email;
	public String password;
	public String liscensePlate;
	public double deposit;
	public int costPerHour;
	public boolean validationStatus;
	
	public User(String name, int id, String email, String password, int costPerHour) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
		this.password = password;
		this.deposit = 0.0;
		this.costPerHour = costPerHour;
	}
	
	public User(){
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLiscensePlate() {
		return liscensePlate;
	}

	public void setLiscensePlate(String liscensePlate) {
		this.liscensePlate = liscensePlate;
	}

	public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

	public boolean getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}
	
	public void extendTime(ParkingSpace ps, int durationHours) {
	    if(ps.isAvailable() == false) {
	    	double deposit = durationHours * this.costPerHour;
            this.setDeposit(deposit + getDeposit());
	    }
	}

	public boolean isNoShow(int durationHours) {
        // Check if the client is a no-show (i.e., did not show up within the first hour of the booking)
        if (this.deposit == 0.0 && durationHours >= 1) {
            return true;
        } else {
            return false;
        }
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
}
