package ip.management.service.enums;

public enum IpResourceState {

	FREE("free"), RESERVED("reserve"),BLACKLISTED("blacklist");

	private final String value;
	
	IpResourceState(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	
	
	
}
