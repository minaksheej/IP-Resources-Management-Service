package ip.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ip.management.service.model.IpAddressResource;
@Transactional(readOnly = true)
public interface IpAddressRepository extends JpaRepository<IpAddressResource, Long> {

}
