package ip.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ip.management.service.model.IpPoolResource;
@Transactional(readOnly = true)
public interface IpPoolRepository extends JpaRepository<IpPoolResource, Long> {

}
