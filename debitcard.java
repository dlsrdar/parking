package park;

public class debitcard implements paymentstrategy {

	@Override
	public void collectpaymentdetails() {
		debitGUI db = new debitGUI();
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
