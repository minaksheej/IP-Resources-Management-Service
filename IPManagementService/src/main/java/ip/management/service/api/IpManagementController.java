package ip.management.service.api;

import java.util.List;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ip.management.service.dto.IpAddressReponse;
import ip.management.service.dto.IpAddressRequest;
import ip.management.service.dto.assembler.IpAddresAssembler;
import ip.management.service.dto.assembler.IpAddressListAssembler;
import ip.management.service.service.IpManagementService;

@RestController
public class IpManagementController implements IpManagementAPI {
	private static final Logger logger = LoggerFactory.getLogger(IpManagementController.class);
	
	private IpManagementService ipManagementService;

	private IpAddresAssembler ipAddresAssembler;

	private IpAddressListAssembler ipAddressListAssembler;

	@Autowired
	public IpManagementController(IpManagementService ipManagementService, IpAddresAssembler ipAddresAssembler,
			IpAddressListAssembler ipAddressListAssembler) {
		this.ipManagementService = ipManagementService;
		this.ipAddresAssembler = ipAddresAssembler;
		this.ipAddressListAssembler = ipAddressListAssembler;
	}

	@Override
	public ResponseEntity<List<String>> generateIpAddresses(@Min(1) Long ipPoolId, Integer noOfIpAddress) {
		logger.info("genetate {} IP Address form the IP Pool {} ",noOfIpAddress,ipPoolId);
		return new ResponseEntity<List<String>>(ipManagementService.generateIpAddresses(ipPoolId, noOfIpAddress),
				HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<IpAddressReponse>> saveIpAddress(String action, IpAddressRequest ipAddressRequest) {
		logger.info(" {} IP Address form the IP Pool {} for IP Addresses {} ",action,ipAddressRequest.getIpPoolId(),ipAddressRequest.getIpAddresses());
		return new ResponseEntity<List<IpAddressReponse>>(
				ipAddressListAssembler.of(ipManagementService.saveIpAddress(action, ipAddressRequest)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<IpAddressReponse> findIpAddresses(@Min(1) Long ipPoolId, String ipAddress) {
		logger.info("find {} IP Address form the IP Pool {} ",ipAddress,ipPoolId);
		return new ResponseEntity<IpAddressReponse>(
				ipAddresAssembler.of(ipManagementService.findIpAddress(ipPoolId, ipAddress)), HttpStatus.OK);
	}

}
