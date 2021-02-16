package ip.management.service.enums;

import java.net.InetAddress;
import java.util.Comparator;

public enum IpAddressComparator implements Comparator<InetAddress> {
	INSTANCE;

	@Override
	public int compare(InetAddress firstAddress, InetAddress secondAddress) {
		byte[] firstBytes = firstAddress.getAddress();
		byte[] secondBytes = secondAddress.getAddress();
		if (firstBytes.length != secondBytes.length) {
			throw new IllegalArgumentException("IP Address Format is not Valid");
		}
		for (int i = firstBytes.length - 1; i >= 0; i--) {
			int a = firstBytes[i] & 0xff;
			int b = secondBytes[i] & 0xff;
			if (a < b) {
				return -1;
			} else if (a > b) {
				return 1;
			}
		}
		return 0;
	}

}
