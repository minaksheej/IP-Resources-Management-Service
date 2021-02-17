package ip.management.service.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneratedIPDto {
	
	private List<String> generatedIpList;
	private Long  ipPoolId;
}
