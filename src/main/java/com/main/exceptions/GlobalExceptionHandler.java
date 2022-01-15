package com.main.exceptions;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.main.dtos.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String ACCESS_DENIED="Access Denied!";
    public static final String INVALID_REQUEST="Invalid Request";
    public static final String ERROR_MESSAGE_TEMPLATE="messager: %s %n requested uri:%s";
    public static final String LIST_JOIN_DELIMETER=",";
    public static final String FIELD_ERROR_SEPARATOR=": ";
    public static final Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERRORS_FOR_PATH="errors {} for path {}";
    private static final String PATH="path";
    private static final String ERRORS="errors";
    private static final String STATUS="status";
    private static final String MESSAGE="message";
    private static final String TIMESTAMP="timestamp";
    private static final String TYPE="type";

 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,WebRequest request
    ){
        List<String> validationErrors=exception.getBindingResult()
                                      .getFieldErrors()
                                      .stream()
                                      .map(error-> error.getField()+FIELD_ERROR_SEPARATOR+error.getDefaultMessage())
                                      .collect(Collectors.toList());
        return getExceptionResponseEntity(exception,HttpStatus.BAD_REQUEST, request,validationErrors);
    }

     @Override
     protected ResponseEntity<Object> handleHttpMessageNotReadable(
         HttpMessageNotReadableException exception,
         HttpHeaders headers,
         HttpStatus status,WebRequest request
     ){
         return getExceptionResponseEntity(exception, status, request,
         Collections.singletonList(exception.getLocalizedMessage()));
     }

     @ExceptionHandler({ConstraintViolationException.class})
     public ResponseEntity<Object> handleConstraintViolation(
         ConstraintViolationException exception,
         WebRequest request ){
              final List<String> validationErrors=exception.getConstraintViolations()
                                                           .stream()
                                                           .map(violation-> 
                                                                  violation.getPropertyPath()+ FIELD_ERROR_SEPARATOR+ violation.getMessage())
                                                           .collect(Collectors.toList());
                                                        
                                                    return getExceptionResponseEntity(exception,HttpStatus.BAD_REQUEST, request,validationErrors);
     }

    @ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ApiResponse> resolveException(UnauthorizedException exception) {

		ApiResponse apiResponse = exception.getApiResponse();
		this.logException(apiResponse.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	public ResponseEntity<ApiResponse> resolveException(BadRequestException exception) {
		ApiResponse apiResponse = exception.getApiResponse();
		this.logException(apiResponse.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public ResponseEntity<ApiResponse> resolveException(ResourceNotFoundException exception) {
		ApiResponse apiResponse = exception.getApiResponse();
		this.logException(apiResponse.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public ResponseEntity<ApiResponse> resolveException(AccessDeniedException exception) {
		ApiResponse apiResponse = exception.getApiResponse();

		this.logException(apiResponse.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }
	
	//the catch all finally

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request){
        ResponseStatus responseStatus=exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status=responseStatus !=null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage=exception.getLocalizedMessage();
        String message=(StringUtils.isNotEmpty(localizedMessage) ? localizedMessage:status.getReasonPhrase());
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE,message,PATH),exception);
      //  this.logException(localizedMessage);
        return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));

    }

    private ResponseEntity<Object> getExceptionResponseEntity(
        final Exception exception,
        final HttpStatus status,
        final WebRequest request,
        final List<String> errors){
            final Map<String,Object> body=new LinkedHashMap<>();

            final String path=request.getDescription(false);
            body.put(TIMESTAMP,Instant.now());
            body.put(STATUS, status.value());
            body.put(ERRORS,errors);
            body.put(TYPE, exception.getClass().getSimpleName());
            body.put(PATH,path);
            body.put(MESSAGE, getMessageForStatus(status));

            final String errorsMessage=CollectionUtils.isNotEmpty(errors) ?
                           errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining(LIST_JOIN_DELIMETER))
                           : status.getReasonPhrase();

                          logger.error(ERRORS_FOR_PATH,errorsMessage,path);
                           return new ResponseEntity<>(body,status);
        }

        private String getMessageForStatus(HttpStatus status){
            switch(status){
                case UNAUTHORIZED:
                   return ACCESS_DENIED;
                case BAD_REQUEST:
                   return INVALID_REQUEST;
                default:
                 return status.getReasonPhrase();
            }
        }
        
        private void logException(String loadedMessage) {
        	logger.error(loadedMessage);
        }

}

