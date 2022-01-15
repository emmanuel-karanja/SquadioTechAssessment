package com.main.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonPropertyOrder({
		"success",
		"message"
})
public class ApiResponse implements Serializable {

	/**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("success")
	private Boolean success;

	@JsonProperty("message")
	private String message;

	@JsonIgnore
	private HttpStatus status;

	public ApiResponse() {

	}

	public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public ApiResponse(Boolean success, String message, HttpStatus httpStatus) {
		this.success = success;
		this.message = message;
		this.status = httpStatus;
	}
	
	public void setSuccess(boolean success) {
		this.success=success;
	}
	
   public void setMessage(String message) {
	   this.message=message;
   }
   
   public boolean getSuccess() {
	   return success;
   }
   
   public String getMessage() {
	   return message;
   }
}

