package ip.management.service.dto;

import ip.management.service.enums.IpResourceState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpAddressReponse {
	
	private String ipAddress;
	
	private Long ipPoolId;
	
	private IpResourceState status;

}
