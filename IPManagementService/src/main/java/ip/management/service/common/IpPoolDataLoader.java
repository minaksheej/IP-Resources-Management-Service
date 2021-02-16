package ip.management.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ip.management.service.model.IpPoolResource;
import ip.management.service.repository.IpPoolRepository;

@Component
public class IpPoolDataLoader {
	
	private IpPoolRepository ipPoolRepository;

	@Autowired
	public IpPoolDataLoader(IpPoolRepository ipPoolRepository) {
		this.ipPoolRepository = ipPoolRepository;
		loadPoolData();
	}

	private void loadPoolData() {
		IpPoolResource firstResource=new IpPoolResource("Static - IP Block 1","10.70.26.1","10.70.26.100");
		IpPoolResource secondResource=new IpPoolResource("Static - IP Block 2","10.70.25.0","10.70.25.255");
		IpPoolResource thirdResource=new IpPoolResource("Static - IP Block 3","10.50.0.0","10.50.255.255");
		ipPoolRepository.save(firstResource);
		ipPoolRepository.save(secondResource);
		ipPoolRepository.save(thirdResource);

	}
	
}
