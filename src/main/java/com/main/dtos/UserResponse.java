package com.main.dtos;

import java.util.Collection;

public class UserResponse {

	private long id;
	private String username;
	private boolean isAdmin;
	private String role;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	public void setRole(String role) {
		this.role=role;
	}
	
	public void setUserName(String username) {
		this.username=username;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin=isAdmin;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getRole() {
		return role;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
}
