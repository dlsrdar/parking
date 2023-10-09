package park;

public class mobilepayment implements paymentstrategy {

	@Override
	public void collectpaymentdetails() {
		MobilePayGUI db = new MobilePayGUI();
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
