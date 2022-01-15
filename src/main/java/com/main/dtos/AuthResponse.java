package com.main.dtos;

public class AuthResponse {
	 private String username;
	 private boolean isAdmin;
	 private String jwtToken;
	 
	 public void setUserName(String username) {
		 this.username=username;
	 }
	 
	 public void setIsAdmin(boolean isAdmin) {
		 this.isAdmin=isAdmin;
	 }
	 
	 public void setJwtToken(String token) {
		 this.jwtToken=token;
	 }
	 
	 public String getUserName() {
		 return username;
	 }
	 
	 public boolean getIsAdmin() {
		 return isAdmin;
	 }
	 
	 public String getJwtToken() {
		 return jwtToken;
	 }
}
