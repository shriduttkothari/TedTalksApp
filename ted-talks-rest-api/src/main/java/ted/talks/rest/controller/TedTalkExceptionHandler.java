package ted.talks.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ted.talks.rest.dto.ErrorResponseDTO;
import ted.talks.rest.exception.NoTedTalkFoundException;

@ControllerAdvice
public class TedTalkExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
		ex.printStackTrace();
		ErrorResponseDTO error = new ErrorResponseDTO("Internal Server Error");
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoTedTalkFoundException.class)
	public final ResponseEntity<ErrorResponseDTO> handleNoTedTalkFoundException(NoTedTalkFoundException ex) {
		ex.printStackTrace();
		ErrorResponseDTO error = new ErrorResponseDTO("No Ted Talk Found");
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
		ex.printStackTrace();
		ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage());
		return new ResponseEntity<ErrorResponseDTO>(error, HttpStatus.BAD_REQUEST);
	}
}
