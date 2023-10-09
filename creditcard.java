package park;

public class creditcard implements paymentstrategy {

	@Override
	public void collectpaymentdetails() {
		creditGUI db = new creditGUI();
		db.setVisible(true);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pay() {
		// TODO Auto-generated method stub

	}

}
