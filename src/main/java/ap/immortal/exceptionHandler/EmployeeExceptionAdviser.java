package ap.immortal.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ap.immortal.exception.EmployeeNotFoundException;
import ap.immortal.model.error.EmployeeErrorResponse;

@ControllerAdvice
public class EmployeeExceptionAdviser {
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException studentException){
		
		EmployeeErrorResponse errResp = new EmployeeErrorResponse();
		errResp.setStatus(HttpStatus.NOT_FOUND.value());
		errResp.setMessage(studentException.getMessage());
		errResp.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errResp,HttpStatus.NOT_FOUND);
	}
	
	//ExceptionHandler for Generic Exception BAD REQUEST
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleGenricException(Exception exc){
		
		EmployeeErrorResponse errResp = new EmployeeErrorResponse();
		errResp.setStatus(HttpStatus.BAD_REQUEST.value());
		errResp.setMessage(exc.getMessage());
		errResp.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errResp,HttpStatus.BAD_REQUEST);
	}

}
