package park;

import java.util.ArrayList;
import java.util.List;

interface ParkingSystemSubject {
    void registerObserver(ParkingSpaceObserver observer);
    void removeObserver(ParkingSpaceObserver observer);
    void notifyObservers();
}

/*
 * Req3: Any registered client can book a valid parking space (i.e., currently not occupied or booked by other
 * users) after login to the system, the parking rates for different types of users are different, i.e., 5$, 8$, 10$, and
 * 15$ for students, faculty members, non-faculty staffs, and visitors per hour respectively.
 * **/

// Concrete subject class
class ParkingSystem implements ParkingSystemSubject {
    public List<ParkingSpaceObserver> observers;
    public List<ParkingSpace> parkingSpaces;
    public List<User> users;

    public ParkingSystem() {
        observers = new ArrayList<>();
        parkingSpaces = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaces.add(parkingSpace);
        notifyObservers();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean login(String liscensePlate) {
        for (User user : users) {
            if (user.getLiscensePlate().equalsIgnoreCase(liscensePlate)) {
                return true;
            }
        }
        return false;
    }

    public boolean bookParkingSpace(User user, int spaceNumber) {
        // Check if client is logged in
        if (!users.contains(user)) {
            System.out.println("Please log in to book a parking space.");
            return false;
        }

        // Find parking space with given number
        ParkingSpace space = null;
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if (parkingSpace.getSpaceNumber() == spaceNumber) {
                space = parkingSpace;
                break;
            }
        }

        // Check if parking space exists
        if (space == null) {
            System.out.println("Parking space " + spaceNumber + " does not exist.");
            return false;
        }

        // Check if parking space is available
        if (!space.isAvailable()) {
            System.out.println("Parking space " + spaceNumber + " is not available.");
            return false;
        }

        // Book parking space
        space.book(user);
        System.out.println("Parking space " + spaceNumber + " booked by " + user.getName() + ".");
        notifyObservers();
        return true;
    }
    
    public void removeBookedParking(User user, int spaceNumber) {
    	// Check if client is logged in
        if (users.contains(user)) {
        
        // Find parking space with given number
        ParkingSpace space = null;
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if (parkingSpace.getSpaceNumber() == spaceNumber) {
                space = parkingSpace;
                break;
            }
        }

        // Check if parking space exists
        if (space == null) {
            System.out.println("Parking space " + spaceNumber + " does not exist.");
        }

        // Check if parking space is available
        if (space.isAvailable()) {
            System.out.println("Parking space " + spaceNumber + " cannot be removed as the parking space has not been booked");
        }

        // Remove booking of parking space
        space.release(0);
        System.out.println("Parking space " + spaceNumber + " unbooked by " + user.getName() + ".");
        notifyObservers();
        } else {
        	System.out.println("removeBookedParking: FAILED, user dne in the data base");
        }
    }

    // Observer methods
    public void registerObserver(ParkingSpaceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ParkingSpaceObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (ParkingSpaceObserver observer : observers) {
            observer.update(parkingSpaces);
        }
    }
}

// Observer interface
interface ParkingSpaceObserver {
    void update(List<ParkingSpace> parkingSpaces);
}

// Concrete observer class
class ParkingSpaceMonitor implements ParkingSpaceObserver {
    private List<ParkingSpace> availableSpaces;

    public ParkingSpaceMonitor() {
        availableSpaces = new ArrayList<>();
    }

    public void update(List<ParkingSpace> parkingSpaces) {
        availableSpaces.clear();

        for (ParkingSpace space : parkingSpaces) {
            if (space.isAvailable()) {
                availableSpaces.add(space);
            }
        }

        System.out.println("Available parking spaces: " + availableSpaces.size());
        for (ParkingSpace space : availableSpaces) {
            System.out.println("  - " + space.getSpaceNumber());
        }
    }
}

/*
Req5: Suppose each parking space has a sensor to detect if a car is using the parking space or not. In addition,
the sensor can also scan the basic info of cars, and further send the essential information to the system.
 * **/

/*
Req7: Each parking space will have a unique identification number and other details including its location
and its parting lot, which will help with the navigation for clients.
 * **/
class ParkingSpace {
    public int spaceNumber;
    public boolean available;
    public User bookedBy;
    public double costPerHour;
    public int id; 
    public String location; 
    //private ParkingLot parkingLot;
	

    public ParkingSpace(int spaceNumber, String Location){
         this.spaceNumber = spaceNumber;
         this.location = location;
    }
    
    public ParkingSpace(int spaceNumber) {
        this.spaceNumber = spaceNumber;
        this.available = true;
        this.bookedBy = null;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
    	this.costPerHour = costPerHour;
    }
    
    public void book(User user) {
        this.bookedBy = user;
        this.available = false;
    }
    
    public void booking(User user, int durationHours){
         if (this.available) {
            double deposit = durationHours * this.costPerHour;
            user.setDeposit(deposit);
        }
    }
    
    public String getUserLicence() {
    	return bookedBy.getLiscensePlate();
    }
    

   public void release(int durationHours) {
        if (!this.available) {
            if (this.bookedBy.isNoShow(durationHours)) {
                System.out.println("No-show detected. Deposit of " + this.bookedBy.getDeposit() + " will not be refunded.");
            } else {
                double cost = durationHours * this.costPerHour;
                double refund = this.bookedBy.getDeposit() - cost;
                if (refund < 0) {
                    System.out.println("Deposit of " + this.bookedBy.getDeposit() + " is not enough to cover the cost of " + cost + ". No refund will be given.");
                } else {
                    System.out.println("Refund of " + refund + " has been issued to " + this.bookedBy.getName() + ".");
                    this.bookedBy.setDeposit(refund);
                }
            }

            this.bookedBy = null;
            this.available = true;
        }
    }
}
