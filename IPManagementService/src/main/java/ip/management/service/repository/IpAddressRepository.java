package ip.management.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ip.management.service.model.IpAddressResource;
import ip.management.service.model.IpPoolResource;

@Transactional(readOnly = true)
public interface IpAddressRepository extends JpaRepository<IpAddressResource, Long> {

	@Query("SELECT r FROM IpAddressResource r WHERE r.value = ?1 AND r.ipPool=?2")
	Optional<IpAddressResource> findByValueAndIpPool(String value,IpPoolResource ipPoolId);	

}
