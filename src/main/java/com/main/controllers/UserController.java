package com.main.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dtos.ApiResponse;
import com.main.dtos.UserResponse;
import com.main.exceptions.AccessDeniedException;
import com.main.security.CurrentUser;
import com.main.security.UserPrincipal;
import com.main.services.IUserService;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	Logger logger=LoggerFactory.getLogger(UserController.class);
	private final IUserService userService;
	
	public UserController(@Autowired IUserService userService) {
		this.userService=userService;
	}
	

	
	
	@GetMapping("/{username}")
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable(value="username") String username){
		UserResponse response=userService.findUserByUserName(username);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/")
	//@PreAuthorize("hasRole('User')")
	public ResponseEntity<List<UserResponse>> getAll(@CurrentUser UserPrincipal currentUser){
		List<String> authorities=new ArrayList<>();
		for(GrantedAuthority auth: currentUser.getAuthorities()) {
		  authorities.add(auth.getAuthority());
		}
		if(!authorities.contains("Admin")) {
			throw new AccessDeniedException(new ApiResponse(false,"You are not authorized to access this resource"));
		}
		List<UserResponse> resp=userService.getAll();
		logger.info("Get All Users Request at: "+LocalDateTime.now().toString());
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	
	
}
