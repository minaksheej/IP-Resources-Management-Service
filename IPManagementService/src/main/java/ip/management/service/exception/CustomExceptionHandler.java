package ip.management.service.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings("unchecked")
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error("Unkown exception ---> ", ex);
		return new ResponseEntity(
				Map.of("message", "Internal Server Error !", "details",
						"Internal Server Error . Please contact system administrator !"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IpAddressNotFoundException.class)
	public final ResponseEntity<Object> handleIpAddressNotFoundException(IpAddressNotFoundException ex,
			WebRequest request) {
		logger.error("IP Address Not Found exception ---> ", ex);
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		return new ResponseEntity(Map.of("message", "IP Address Not Found !", "details", details.get(0)),
				HttpStatus.NOT_FOUND);

	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IpAddressOutOfRangeException.class)
	public final ResponseEntity<Object> handleIpAddressOutOfRangeException(IpAddressOutOfRangeException ex,
			WebRequest request) {
		logger.error("IP Address Out of Range Exception ---> ", ex);

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		return new ResponseEntity(Map.of("message", "IP Address Out of Range !", "details", details.get(0)),
				HttpStatus.NOT_FOUND);

	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IpAddressUniqueViolationException.class)
	public final ResponseEntity<Object> handleIpAddressUniqueViolationException(IpAddressUniqueViolationException ex,
			WebRequest request) {
		logger.error("Ip Address Unique Violation Exception ---> ", ex);
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		return new ResponseEntity(Map.of("message", "Ip Address Unique Violation ", "details", details.get(0)),
				HttpStatus.BAD_REQUEST);

	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IllegalFormatConversionException.class)
	public final ResponseEntity<Object> handleIllegalFormatConversionException(
			IllegalFormatConversionException ex, WebRequest request) {
		logger.error("IP Address Not Found Exception ---> ", ex);
		List<String> details = new ArrayList<>();
		details.add("Please Check Your Input!!");
		return new ResponseEntity(Map.of("message", "IP Address Not Found.", "details", details.get(0)),
				HttpStatus.NOT_FOUND);

	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IpPoolNotFoundException.class)
	public final ResponseEntity<Object> handleIpPoolNotFoundException(IpPoolNotFoundException ex,
			WebRequest request) {
		logger.error("IP Pool Not Found Exception ---> ", ex);

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		return new ResponseEntity(Map.of("message", "IP Pool Information not Availabe !", "details", details.get(0)),
				HttpStatus.NOT_FOUND);

	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
