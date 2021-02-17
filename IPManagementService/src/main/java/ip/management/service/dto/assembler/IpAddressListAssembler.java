package ip.management.service.dto.assembler;

import java.util.List;

import ip.management.service.dto.IpAddressReponse;
import ip.management.service.model.IpAddressResource;

public interface IpAddressListAssembler
		extends GenericResponseAssembler<List<IpAddressResource>, List<IpAddressReponse>> {

}
