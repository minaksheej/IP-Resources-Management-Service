package ip.management.service.service;

import java.util.List;

import ip.management.service.dto.IpAddressRequest;
import ip.management.service.model.IpAddressResource;

public interface IpManagementService {
	
	public List<String> generateIpAddresses(Long ipPoolId,Integer noOfIpAddress);

	public List<IpAddressResource> saveIpAddress(String action, IpAddressRequest ipAddressRequest);

	public IpAddressResource findIpAddress(Long ipPoolId, String ipAddress);

}
