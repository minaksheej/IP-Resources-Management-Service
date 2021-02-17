package ip.management.service.dto.assembler;

import org.springframework.stereotype.Component;

import ip.management.service.dto.IpAddressReponse;
import ip.management.service.model.IpAddressResource;
@Component
public class IpAddresAssemblerImpl implements IpAddresAssembler{

	@Override
	public IpAddressReponse of(IpAddressResource entity) {		
		return new IpAddressReponse(entity.getValue(), entity.getIpPool().getId(), entity.getStatus());
	}

}
