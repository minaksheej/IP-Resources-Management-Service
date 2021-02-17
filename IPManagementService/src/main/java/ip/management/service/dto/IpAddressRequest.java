package ip.management.service.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IpAddressRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222912196582287034L;

	private List<String> ipAddresses;
	
	private Long ipPoolId;

}
