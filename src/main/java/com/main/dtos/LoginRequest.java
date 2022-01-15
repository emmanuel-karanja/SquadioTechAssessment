package com.main.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
	@NotBlank
    @Size(max=40)
    private String username;

    @NotBlank
    @Size(min=6,max=20)

    private String password;
    
    //getters and setters for the sake of marshalling and Swagger doc
    public String getUserName() {
    	return username;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setUserName(String username) {
    	this.username=username;
    }
    
    public void setPassword(String password) {
    	this.password=password;
    }
}
