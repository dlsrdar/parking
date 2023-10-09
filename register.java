package park;


public class register {


    private static register st = new register();

    

	private register() {
    	
    }
	
    public static register getInstance() {
    	
    	if(st==null) {
    		register st= new register();
    	}
    	
    	
		return st;

     }
}
