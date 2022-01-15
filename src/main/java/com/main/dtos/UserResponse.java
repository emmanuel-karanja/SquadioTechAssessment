package com.main.dtos;

import java.util.Collection;

public class UserResponse {

	private long id;
	private String username;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	
	public void setUserName(String username) {
		this.username=username;
	}
	
	
	
	public String getUserName() {
		return username;
	}
	
	
	
}
