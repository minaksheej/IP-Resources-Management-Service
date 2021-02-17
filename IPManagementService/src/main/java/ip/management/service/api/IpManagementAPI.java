package ip.management.service.api;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ip.management.service.dto.GeneratedIPDto;
import ip.management.service.dto.IpAddressRequest;
import ip.management.service.model.IpAddressResource;

public interface IpManagementAPI {

	@Operation(summary = "generate IP Addresses", description = "Provide number of IP Address Required", tags = {
			"IP-Management" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GeneratedIPDto.class)))) })
	@RequestMapping(value = "/generate", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<String>> generateIpAddresses(
			@Min(1L) @io.swagger.v3.oas.annotations.Parameter(in = ParameterIn.QUERY) @RequestParam Long ipPoolId,
			@io.swagger.v3.oas.annotations.Parameter(in = ParameterIn.QUERY) @RequestParam Integer noOfIpAddress);

	@Operation(summary = "save IP Addresses based on status ", description = "select action  as 'reserve', 'blacklist'  or  'free'" , tags = {
	"IP-Management" })
	@RequestMapping(value = "/ipaddres", method = RequestMethod.POST)
	ResponseEntity<List<IpAddressResource>> saveIpAddress(
			@io.swagger.v3.oas.annotations.Parameter(in = ParameterIn.QUERY) @RequestParam String action,
			@RequestBody IpAddressRequest ipAddressRequest);
	

	@Operation(summary = "find IP Addresses", description = "provide valid ip Address and IP Pool ID", tags = {
			"IP-Management" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GeneratedIPDto.class)))) })
	@RequestMapping(value = "/ipaddress", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<IpAddressResource> findIpAddresses(
			@Min(1L) @io.swagger.v3.oas.annotations.Parameter(in = ParameterIn.QUERY) @RequestParam Long ipPoolId,
			@io.swagger.v3.oas.annotations.Parameter(in = ParameterIn.QUERY) @RequestParam String ipAddress);
}
