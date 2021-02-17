package ip.management.service.exception;

/**
 * throw this Exception ipPool record not present 
 * @author mxk64196
 *
 */
public class IpPoolNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8334681041271153535L;

	public IpPoolNotFoundException(String message) {
		super(message);
	}

}
