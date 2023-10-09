package park;

import java.util.Calendar;
import java.util.Date;

/*
 * Req3: Any registered client can book a valid parking space (i.e., currently not occupied or booked by other
 * users) after login to the system, the parking rates for different types of users are different, i.e., 5$, 8$, 10$, and
 * 15$ for students, faculty members, non-faculty staffs, and visitors per hour respectively.
 * **/


//Component interface
interface ParkingRate {
    public int calculateRate(int hours);
}

//Concrete component classes
class StudentRate implements ParkingRate {
    public int calculateRate(int hours) {
        return hours * 5;
    }
}

class FacultyRate implements ParkingRate {
    public int calculateRate(int hours) {
        return hours * 8;
    }
}

class StaffRate implements ParkingRate {
    public int calculateRate(int hours) {
        return hours * 10;
    }
}

class VisitorRate implements ParkingRate {
    public int calculateRate(int hours) {
        return hours * 15;
    }
}

//Decorator class
abstract class ParkingRateDecorator{
    protected ParkingRate rate;

    public ParkingRateDecorator(ParkingRate rate) {
        this.rate = rate;
    }

    public int calculateRate(int hours) {
        return rate.calculateRate(hours);
    }
}

//Concrete decorator classes
class EarlyBirdDecorator extends ParkingRateDecorator {
    public EarlyBirdDecorator(ParkingRate rate) {
        super(rate);
    }
 
    public int calculateRate(int hours) {
        return rate.calculateRate(hours);
    }
}

