package com.main.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dtos.AuthResponse;
import com.main.dtos.LoginRequest;
import com.main.security.CurrentUser;
import com.main.security.UserPrincipal;
import com.main.services.IAuthService;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  
	Logger logger=LoggerFactory.getLogger(AuthController.class);

    private  IAuthService authService;
    
    public AuthController(@Autowired IAuthService authService) {
    	this.authService=authService;
    }
   
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
    	 logger.info("Login Attempt. Creds: "+loginRequest.getPassword()+" at: "+LocalDateTime.now().toString());
         AuthResponse response=authService.authenticate(loginRequest);
         return new ResponseEntity<>(response,HttpStatus.OK);           
    }
    
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@CurrentUser UserPrincipal currentUser) {
   	 logger.info("Login Attempt. Creds: "+currentUser.getUsername()+" at: "+LocalDateTime.now().toString());
        String response=authService.logout(currentUser.getId());
        return new ResponseEntity<>(response,HttpStatus.OK);           
   }
    
}
