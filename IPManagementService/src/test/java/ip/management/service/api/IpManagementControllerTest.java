package ip.management.service.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ip.management.service.dto.assembler.IpAddresAssembler;
import ip.management.service.dto.assembler.IpAddressListAssembler;
import ip.management.service.service.IpManagementService;
import ip.management.service.services.IpMAnagementFixtures;

@RunWith(SpringRunner.class)
@WebMvcTest(value = IpManagementController.class)
public class IpManagementControllerTest {

	@MockBean
	private IpManagementService ipManagementService;

	@MockBean
	private IpAddresAssembler ipAddresAssembler;

	@MockBean
	private IpAddressListAssembler ipAddressListAssembler;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenGenerateIpAddressesThenReturnSuccess() throws Exception {
		given(ipManagementService.generateIpAddresses(Mockito.anyLong(), Mockito.any()))
				.willReturn(IpMAnagementFixtures.getIpList());
		final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/generate?ipPoolId=1&noOfIpAddress=2");

		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void whenSaveIpAddressesThenReturnSuccess() throws Exception {
		given(ipManagementService.saveIpAddress(Mockito.any(), Mockito.any()))
				.willReturn(IpMAnagementFixtures.getIpResourceList());
		final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ipaddres?action=reserve")
				.content(IpMAnagementFixtures.returnJson()).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void whenFindAddressesThenReturnSuccess() throws Exception {
		given(ipManagementService.findIpAddress(Mockito.anyLong(), Mockito.any()))
				.willReturn(IpMAnagementFixtures.getIpAddress());
		final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/ipaddress?ipPoolId=1&ipAddress=10.70.26.1");

		mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}

}
