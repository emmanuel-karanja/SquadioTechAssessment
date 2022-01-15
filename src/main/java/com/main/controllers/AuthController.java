//package com.main.controllers;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.main.dtos.AuthResponse;
//import com.main.dtos.LoginRequest;
//import com.main.services.IAuthService;
//
//
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//  
//
//    private  IAuthService authService;
//    
//    public AuthController(@Autowired IAuthService authService) {
//    	this.authService=authService;
//    }
//   
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//         AuthResponse response=authService.authenticate(loginRequest);
//         return new ResponseEntity<>(response,HttpStatus.OK);           
//    }
//
//    
//}
