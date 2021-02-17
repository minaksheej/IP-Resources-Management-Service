package ip.management.service.exception;

public class IpAddressUniqueViolationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6219406179816286075L;

	public IpAddressUniqueViolationException(String message) {
		super(message);
	}
}
