package park;

public interface paymentstrategy {
	void collectpaymentdetails();
	boolean validate();
	void pay();

}
