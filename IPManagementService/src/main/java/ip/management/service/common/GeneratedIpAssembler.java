package ip.management.service.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ip.management.service.dto.GeneratedIPDto;

@Component
public class GeneratedIpAssembler {

	public List<GeneratedIPDto> of(List<String> entity) {
		List<GeneratedIPDto> list=new ArrayList<>();
		GeneratedIPDto generatedIPDto=new GeneratedIPDto();
		generatedIPDto.setGeneratedIpList(entity);
		return list;
	}

}
