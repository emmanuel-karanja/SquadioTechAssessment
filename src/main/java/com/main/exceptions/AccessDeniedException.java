package com.main.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.main.dtos.ApiResponse;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ApiResponse apiResponse;

    private String message;

    public AccessDeniedException(ApiResponse apiResponse){
        super();
        this.apiResponse=apiResponse;
    }

    public AccessDeniedException(String message){
        super(message);
        this.message=message;
    }

    public AccessDeniedException(String message, Throwable cause){
        super(message,cause);
    }

    public ApiResponse getApiResponse(){
        return apiResponse;
    }
    
    public void setApiResponse(ApiResponse apiResponse) {
    	this.apiResponse=apiResponse;
    }
    
}