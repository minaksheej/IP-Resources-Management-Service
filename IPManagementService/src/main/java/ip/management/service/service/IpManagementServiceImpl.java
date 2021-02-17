package ip.management.service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ip.management.service.dto.IpAddressRequest;
import ip.management.service.enums.IpResourceState;
import ip.management.service.exception.IpAddressNotFoundException;
import ip.management.service.exception.IpAddressUniqueViolationException;
import ip.management.service.exception.IpPoolNotFoundException;
import ip.management.service.model.IpAddressResource;
import ip.management.service.model.IpPoolResource;
import ip.management.service.repository.IpAddressRepository;
import ip.management.service.repository.IpPoolRepository;

@Service
public class IpManagementServiceImpl implements IpManagementService {

	private IpPoolRepository ipPoolRepository;
	private IpAddressRepository ipAddressRepository;

	public IpManagementServiceImpl(IpPoolRepository ipPoolRepository, IpAddressRepository ipAddressRepository) {
		this.ipPoolRepository = ipPoolRepository;
		this.ipAddressRepository = ipAddressRepository;
	}

	@Override
	public List<String> generateIpAddresses(Long ipPoolId, Integer noOfIpAddress) {
		final IpPoolResource ipPool = findIpPool(ipPoolId);
		return ipPool.generateIps(noOfIpAddress);
	}

	@Override
	public IpAddressResource findIpAddress(Long ipPoolId, String ipAddress) {
		final IpPoolResource ipPool = findIpPool(ipPoolId);

		return this.ipAddressRepository.findByValueAndIpPool(new String(ipAddress), ipPool)
				.orElseThrow(() -> new IpAddressNotFoundException(
						String.format("ipAddress with value '%d' doesn't exist", ipAddress)));
	}

	@Override
	public List<IpAddressResource> saveIpAddress(String action, IpAddressRequest ipAddressRequest) {
		final IpPoolResource ipPool = this.ipPoolRepository.findById(ipAddressRequest.getIpPoolId())
				.orElseThrow(() -> new IpPoolNotFoundException(
						String.format("IPPool with id '%d' does not exist", ipAddressRequest.getIpPoolId())));
		return saveIpAddress(action, ipAddressRequest, ipPool);
	}

	
	private List<IpAddressResource> saveIpAddress(String action, IpAddressRequest ipAddressRequest,
			final IpPoolResource ipPool) {
		List<IpAddressResource> savedList = new ArrayList<>();
		if (IpResourceState.RESERVED.getValue().equals(action)) {			
			ipAddressRequest.getIpAddresses().forEach(ipAddress -> {				
				isIpAddressAvailabe(ipPool, ipAddress);				
				IpAddressResource ipAddressEntity = reserverIpAddress(ipPool, ipAddress);
				
				ipAddressRepository.save(ipAddressEntity);
				savedList.add(ipAddressEntity);
			});

		} else if (IpResourceState.BLACKLISTED.getValue().equals(action)) {
			ipAddressRequest.getIpAddresses().forEach(ipAddress -> {
				isIpAddressAvailabe(ipPool, ipAddress);
				
				IpAddressResource ipAddressEntity = blacklistIpAddress(ipPool, ipAddress);
				
				ipAddressRepository.save(ipAddressEntity);
				savedList.add(ipAddressEntity);
			});
		} else if (IpResourceState.FREE.getValue().equals(action)) {
			ipAddressRequest.getIpAddresses().forEach(ipAddress -> {
				IpAddressResource address = this.ipAddressRepository.findByValueAndIpPool(ipAddress, ipPool)
						.orElseThrow(() -> new IpAddressNotFoundException(
								String.format("ipAddress with value '%d' already free", ipAddress)));

				address.returnIpAddress();
				ipAddressRepository.delete(address);
				savedList.add(address);
			});
		}
		return savedList;
	}

	private IpAddressResource blacklistIpAddress(final IpPoolResource ipPool, String ipAddress) {
		return new IpAddressResource(ipPool, ipAddress,
				IpResourceState.BLACKLISTED);
	}

	
	private IpAddressResource reserverIpAddress(final IpPoolResource ipPool, String ipAddress) {
		return new IpAddressResource(ipPool, ipAddress, IpResourceState.RESERVED);
	}

	private void isIpAddressAvailabe(final IpPoolResource ipPool, String ipAddress) {
		this.ipAddressRepository.findByValueAndIpPool(ipAddress, ipPool).stream().findAny().ifPresent(f -> {
			throw new IpAddressUniqueViolationException(
					String.format("IpAddress with value '%s' already Reserved", ipAddress));
		});
	}

	private IpPoolResource findIpPool(Long ipPoolId) {
		final IpPoolResource ipPool = this.ipPoolRepository.findById(ipPoolId).orElseThrow(
				() -> new IpPoolNotFoundException(String.format("IPPool with id '%d' does not exist", ipPoolId)));
		return ipPool;
	}

}
