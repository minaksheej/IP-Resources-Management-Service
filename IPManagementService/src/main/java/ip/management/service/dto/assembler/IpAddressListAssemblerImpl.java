package ip.management.service.dto.assembler;

import java.util.List;

import org.springframework.stereotype.Component;

import ip.management.service.dto.IpAddressReponse;
import ip.management.service.model.IpAddressResource;
@Component
public class IpAddressListAssemblerImpl implements  IpAddressListAssembler{

	private IpAddresAssembler ipAddresAssemblerl;
	
	public IpAddressListAssemblerImpl(IpAddresAssembler ipAddresAssemblerl) {
		this.ipAddresAssemblerl = ipAddresAssemblerl;
	}

	@Override
	public List<IpAddressReponse> of(List<IpAddressResource> entities) {
		return ipAddresAssemblerl.of(entities);
	}

}
