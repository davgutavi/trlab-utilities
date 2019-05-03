package input.laboratory;

/**
 * It represents an wrong value for a parameter or an option of <b>Laboratory</b> application.
 * 
 * @author David Gutiérrez Avilés
 *
 */
@SuppressWarnings("serial")
public class WrongOptionsException extends Exception {
	
	/**
	 * It builds the exception.
	 * @param message Error message.
	 */
	public WrongOptionsException(String message){
		super(message);
	}
	

}