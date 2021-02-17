package ip.management.service.api;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ip.management.service.common.GeneratedIpAssembler;
import ip.management.service.dto.IpAddressRequest;
import ip.management.service.model.IpAddressResource;
import ip.management.service.service.IpManagementService;

@RestController
public class IpManagementController implements IpManagementAPI {

	private IpManagementService ipManagementService;

	@Autowired
	private GeneratedIpAssembler generatedIpAssembler;

	@Autowired
	public IpManagementController(IpManagementService ipManagementService) {
		this.ipManagementService = ipManagementService;
	}

	@Override
	public ResponseEntity<List<String>> generateIpAddresses(@Min(1) Long ipPoolId, Integer noOfIpAddress) {
		return new ResponseEntity<List<String>>(ipManagementService.generateIpAddresses(ipPoolId, noOfIpAddress),
				HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<IpAddressResource>> saveIpAddress(String action, IpAddressRequest ipAddressRequest) {
		return new ResponseEntity<List<IpAddressResource>>(ipManagementService.saveIpAddress(action, ipAddressRequest),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<IpAddressResource> findIpAddresses(@Min(1) Long ipPoolId, String ipAddress) {			
		return new ResponseEntity<IpAddressResource>(ipManagementService.findIpAddress(ipPoolId, ipAddress),
				HttpStatus.OK);
	}
	
	

}