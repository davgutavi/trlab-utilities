package input.algorithm;

/**
 * It represents an wrong value for a parameter or an option of <b>triGen</b> algorithm.
 * 
 * @author David Gutiérrez Avilés
 *
 */
@SuppressWarnings("serial")
public class WrongContolException extends Exception {
	
	/**
	 * It builds the exception.
	 * @param message Error message.
	 */
	public WrongContolException(String message){
		super(message);
	}
	

}
