package fpt.com.main.Utils;

public class IDOverflowException extends Exception{
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 6918460043334834961L;

	public IDOverflowException() {
	        super("Maximaler Wert von 999999 überschritten!");
	    }

}
