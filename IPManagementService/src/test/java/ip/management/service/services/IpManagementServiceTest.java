package ip.management.service.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ip.management.service.exception.IpPoolNotFoundException;
import ip.management.service.model.IpPoolResource;
import ip.management.service.repository.IpAddressRepository;
import ip.management.service.repository.IpPoolRepository;
import ip.management.service.service.IpManagementServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpManagementServiceTest {

	@Mock
	private IpPoolRepository ipPoolRepository;
	@Mock
	private IpAddressRepository ipAddressRepository;

	@InjectMocks
	private IpManagementServiceImpl ipManagementServiceImpl;

	@Test
	public void generateIpAddressesTest() {
		Optional<IpPoolResource> ipPool = IpMAnagementFixtures.getIPPool();
		Mockito.when(ipPoolRepository.findById(Mockito.anyLong())).thenReturn(ipPool);
		List<String> ipList = ipManagementServiceImpl.generateIpAddresses(1L, 4);
		assertNotNull(ipList, "respons will not be null");
	}

	@Test
	public void whenPoolIdNotPresentThenThrowException() {

		Mockito.doReturn(Optional.ofNullable(null)).when(ipPoolRepository).findById(Mockito.anyLong());

		assertThatThrownBy(() -> ipManagementServiceImpl.generateIpAddresses(1L, 4))
				.isInstanceOf(IpPoolNotFoundException.class);

	}

}
