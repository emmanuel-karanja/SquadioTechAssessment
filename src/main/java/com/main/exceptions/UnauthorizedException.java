package com.main.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.main.dtos.ApiResponse;

@ResponseStatus(code= HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ApiResponse apiResponse;

    private String message;

    public UnauthorizedException(ApiResponse apiResponse){
        super();
        this.apiResponse=apiResponse;
    }

    public UnauthorizedException(String message){
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause){
        super(message,cause);
    }

    public ApiResponse getApiResponse(){
        return apiResponse;
    }
    
    public void setApiResponse(ApiResponse apiResponse) {
    	this.apiResponse=apiResponse;
    }
    
}
