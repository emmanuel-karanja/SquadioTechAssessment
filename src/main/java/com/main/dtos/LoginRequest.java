package com.main.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
	
    @Size(max=40)
    private String username;

    @NotBlank
    @Size(min=3,max=40)

    private String password;
    
   
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
