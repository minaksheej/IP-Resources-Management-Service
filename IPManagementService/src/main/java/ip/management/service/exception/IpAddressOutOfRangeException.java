package ip.management.service.exception;

public class IpAddressOutOfRangeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8253959288076817394L;

	public IpAddressOutOfRangeException(String message) {
		super(message);
	}
}
