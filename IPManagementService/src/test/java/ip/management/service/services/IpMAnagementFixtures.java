package ip.management.service.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ip.management.service.enums.IpResourceState;
import ip.management.service.model.IpAddressResource;
import ip.management.service.model.IpPoolResource;

public class IpMAnagementFixtures {

	public static List<String> getIpList() {
		return java.util.Arrays.asList("10.70.26.1", "10.70.26.1");

	}

	public static Optional<IpPoolResource> getIPPool() {
		Optional<IpPoolResource> ipPool = Optional
				.of(new IpPoolResource("Static - IP Block 1", "10.70.26.1", "10.70.26.100"));
		return ipPool;
	}

	public static List<IpAddressResource> getIpResourceList() {
		return Arrays.asList(getIpAddress());
	}

	public static IpPoolResource getIpPool() {
		IpPoolResource pool = new IpPoolResource("Static - IP Block 1", "10.70.26.1", "10.70.26.100");
		return pool;
	}

	public static IpAddressResource getIpAddress() {
		IpPoolResource pool = getIpPool();
		return new IpAddressResource(pool, "10.70.26.1", IpResourceState.RESERVED);
	}

	public static String returnJson() {
		return "{\r\n" + "  \"10.70.26.1\": [\r\n" + "    \"string\"\r\n" + "  ],\r\n" + "  \"ipPoolId\": 1\r\n" + "}";
	}

}
